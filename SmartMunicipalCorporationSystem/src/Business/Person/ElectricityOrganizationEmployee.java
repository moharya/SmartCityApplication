/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Person;

/**
 *
 * @author harsh
 */
public class ElectricityOrganizationEmployee extends Person{
    private static int id;

    public ElectricityOrganizationEmployee() {
    ++id;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ElectricityOrganizationEmployee.id = id;
    }
    
   
}
