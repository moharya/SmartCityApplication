/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Municipal.EverSourceElectricityOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;

import javax.swing.JPanel;
import userinterface.ElectricityRole.ElectricityWorkAreaJPanel;

/**
 *
 * @author raunak
 */
public class EverSourceElectricityManagerRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, 
            UserAccount account, 
            Organization organization, 
            Enterprise enterprise, 
            EcoSystem business) {
        return new ElectricityWorkAreaJPanel(userProcessContainer, account, (EverSourceElectricityOrganization)organization,business);
    }
    
    
}
