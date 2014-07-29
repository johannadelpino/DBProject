/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import DBManager.DBManager;
import Models.Person;
import java.util.Date;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Johanna
 */
@WebServlet(name = "Application", urlPatterns = {"/Application"})
public class Application extends HttpServlet {

    
    public static String goBackinTime(int years){
                Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -years*365);
		Date finalDate = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
               
		return  dateFormat.format(finalDate);
    }
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String action="";
            action=request.getParameter("action");
            if(action.equals("search-users")){
                String gender=request.getParameter("gender");
                String min_age=request.getParameter("minage");
                String max_age=request.getParameter("maxage");
                String interest=request.getParameter("interest");
               
                
                List<Person> persons=new ArrayList();
                Date date=new Date();
                DBManager dbmanager = new DBManager();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try{
                    dbmanager.openConnection();
                    String query="SELECT  DISTINCT p.*\n" ;
                        query+="FROM person p, personinterests i\n" ;
                        query+="WHERE p.personid=i.personid\n" ;
                        if(!interest.equals("-2")){
                             query+="AND i.interest='"+interest+"'\n" ;
                        }
                        query+="AND p.gender='"+gender+"'\n";
                        if(!min_age.equals("-1")){
                            min_age=goBackinTime(Integer.parseInt(min_age));
                            query+="AND p.birthdate <= '"+min_age+"'\n";
                        }
                        if(!max_age.equals("-1")){
                             max_age=goBackinTime(Integer.parseInt(max_age)+1);
                             query+="AND p.birthdate > '"+max_age+"'\n";
                        }
                        
                    
                    ResultSet resultSet = dbmanager.statement.executeQuery(query);
                    while (resultSet.next()) {
                       
                        Person person =new Person();
                        person.setFirstname(resultSet.getString("firstname"));
                        person.setLastname(resultSet.getString("lastname"));
                        person.setMiddleinitial(resultSet.getString("middleinitial"));
                        Date date_tmp=new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("birthdate"));
                        
                        person.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("birthdate")));
                        person.setPersonid(Integer.parseInt(resultSet.getString("personid")));
                       
                        person.setSex(resultSet.getString("gender"));
                        person.setPhoto(resultSet.getString("url"));
                        persons.add(person);
                   }
                    resultSet.close();
                    
                    for(Person person:persons){
                        ArrayList<String> interests=new ArrayList();
                        ResultSet innerresultSet = dbmanager.statement.executeQuery("SELECT interest FROM personinterests WHERE personid="+person.getPersonid());
                         while (innerresultSet.next()) {
                            String person_interest=innerresultSet.getString("interest");
                            interests.add(person_interest);
                        }
                        person.setInterests(interests);
                        innerresultSet.close();
                    }
               
                }catch(Exception e){

                }finally{
                    dbmanager.closeConnection();
                }
                if(persons.size()>0){
                    request.setAttribute("persons", persons);
                    RequestDispatcher rd=getServletContext().getRequestDispatcher("/results.jsp");
                    rd.forward(request, response);
                }else{
                    RequestDispatcher rd=getServletContext().getRequestDispatcher("/no_results.jsp");
                    rd.forward(request, response);
                
                }                      
                

            }
            else if(action.equals("getInfo")){
               
                String xml="<?xml version=\"1.0\"?><interests>";
                
                DBManager dbmanager = new DBManager();
               
                try{
                    dbmanager.openConnection();
                    String query="SELECT  DISTINCT interest\n" ;
                    query+="FROM personinterests i ORDER BY interest asc;" ;
                   
                    ResultSet resultSet = dbmanager.statement.executeQuery(query);
                    while (resultSet.next()) {
                      xml+="<interest>"+resultSet.getString("interest")+"</interest>";
                       
                    }
                    resultSet.close();
                    
                    
                }catch(Exception e){

                }finally{
                    dbmanager.closeConnection();
                }
                xml+="</interests>";
                response.setContentType("text/xml;charset=UTF-8");
                out.println(xml);
                
                
            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
