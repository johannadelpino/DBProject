/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Johanna
 */
public class Person{
    private String firstname;
    private String middleinitial;
    private String lastname;
    private Date birthdate;
    private int  personid;
    private String photo;
    private String sex;
    private ArrayList interests;

    public Person() {
       interests=new ArrayList();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddleinitial() {
        return middleinitial;
    }

    public void setMiddleinitial(String middleinitial) {
        this.middleinitial = middleinitial;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public ArrayList getInterests() {
        return interests;
    }

    public void setInterests(ArrayList interests) {
        this.interests = interests;
    }
    
    
    
    @Override
    public String toString(){
        String person="";
        person+="ID : "+this.personid+"\n";
        person+="Name : "+this.firstname+"\n";
        person+="Middle Initial : "+this.middleinitial+"\n";
        person+="Last Name : "+this.lastname+"\n";
        person+="Birthdate : "+this.birthdate+"\n";
        return person;
    }
        

}
