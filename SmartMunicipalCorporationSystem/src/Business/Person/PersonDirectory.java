/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Person;

import Business.Address.Address;
import Business.Organization.Municipal.CitizenOrganization;
import Business.Organization.Municipal.EverSourceElectricityOrganization;
import Business.Organization.Organization;
import java.util.ArrayList;

/**
 *
 * @author prashant
 */
public class PersonDirectory {
    private ArrayList<Person> personList;

    public PersonDirectory() {
        personList = new ArrayList<>();
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }
    
    
    public Person createDirectAidEmp(String fname, String lname,String emailId, String sex,int age, Address address, String phoneNo, int pincode, Organization o){
       
      if (o instanceof EverSourceElectricityOrganization)
          {  ElectricityOrganizationEmployee electricityOrganizationEmployee = new ElectricityOrganizationEmployee();
         
            electricityOrganizationEmployee.setFirstName(fname);
            electricityOrganizationEmployee.setLastName(lname);
            electricityOrganizationEmployee.setEmailID(emailId);
            electricityOrganizationEmployee.setAddress(address);
            electricityOrganizationEmployee.setAge(age);
            electricityOrganizationEmployee.setPhone(phoneNo);
            electricityOrganizationEmployee.setSex(sex);
          personList.add(electricityOrganizationEmployee);
          return electricityOrganizationEmployee;  
    }
      
    if (o instanceof CitizenOrganization)
          {CitizenOrganizationEmployee  citizenOrganizationEmployee = new CitizenOrganizationEmployee();
         
            citizenOrganizationEmployee.setFirstName(fname);
            citizenOrganizationEmployee.setLastName(lname);
            citizenOrganizationEmployee.setEmailID(emailId);
            citizenOrganizationEmployee.setAddress(address);
            citizenOrganizationEmployee.setAge(age);
            citizenOrganizationEmployee.setPhone(phoneNo);
            citizenOrganizationEmployee.setSex(sex);
          personList.add(citizenOrganizationEmployee);
          return citizenOrganizationEmployee;  
    }  
      
      
     return null;
    }
   
     public Person createPerson(String fname, String lname,String emailId, String sex,int age, Address address, String phoneNo, int pincode){
        Person p = new Person();
        p.setFirstName(fname);
          p.setFirstName(fname);
            p.setLastName(lname);
          //  p.setEmailId(emailId);
            p.setAddress(address);
            p.setAge(age);
            p.setPhone(phoneNo);
            p.setSex(sex);
        personList.add(p);
        return p;
    }
    
    public void removePerson(Person p) {
        personList.remove(p);
    }

}
