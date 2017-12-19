/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Amogh
 */
public class ElectricityEnterprise extends Enterprise{

    public ElectricityEnterprise(String name) {
        super(name, EnterpriseType.Electricity);
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        return null;
    }
    
    
    
}
