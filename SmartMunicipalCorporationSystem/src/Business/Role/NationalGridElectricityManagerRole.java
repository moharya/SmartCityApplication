/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Municipal.NationalGridElectricityOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userinterface.ElectricityRole.ElectricityWorkAreaJPanel;
import userinterface.ElectricityRole.ElectricityWorkAreaJPanel2;

/**
 *
 * @author Amogh
 */
public class NationalGridElectricityManagerRole extends Role{
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
      
        return new ElectricityWorkAreaJPanel2(userProcessContainer, account, (NationalGridElectricityOrganization)organization,business);
    }
}
