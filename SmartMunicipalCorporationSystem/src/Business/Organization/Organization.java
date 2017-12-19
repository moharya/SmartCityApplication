/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

//import Business.Employee.EmployeeDirectory;
import Business.Person.PersonDirectory;
import Business.Role.Role;
import Business.UserAccount.UserAccountDirectory;
import Business.WorkeQueue.WorkQueue;
import java.util.ArrayList;

/**
 *
 * @author Amogh
 */
public abstract class Organization {

    private String name;
    private WorkQueue workQueue;
    private UserAccountDirectory userAccountDirectory;
    private int organizationID;
    private static int counter;
    private PersonDirectory personDirectory;
    
    public enum Type{
        Admin("Admin Organization"), Citizen("Citizen Organization"), Construction("Construction Organization"), Water("Water Organziation"), EverSource("EverSource Electricity Organization"), NationalGrid("NationalGrid Electricity Organization"),Monitoring("Montoring");
        private String value;
        private Type(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    public Organization(String name) {
        this.name = name;
        workQueue = new WorkQueue();
        personDirectory = new PersonDirectory();
        userAccountDirectory = new UserAccountDirectory();
        organizationID = counter;
        ++counter;
    }

    public abstract ArrayList<Role> getSupportedRole();
    
    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    public int getOrganizationID() {
        return organizationID;
    }
    
    public String getName() {
        return name;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkQueue(WorkQueue workQueue) {
        this.workQueue = workQueue;
    }

    public PersonDirectory getPersonDirectory() {
        return personDirectory;
    }

    public void setPersonDirectory(PersonDirectory personDirectory) {
        this.personDirectory = personDirectory;
    }
    

    @Override
    public String toString() {
        return name;
    }
    
    
}
