/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization.Municipal;

import Business.Organization.Organization;
import Business.Role.EverSourceElectricityManagerRole;
import Business.Role.NationalGridElectricityManagerRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Amogh
 */
public class NationalGridElectricityOrganization extends Organization{
    public NationalGridElectricityOrganization() {
        super(Organization.Type.NationalGrid.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new NationalGridElectricityManagerRole());
        return roles;
    }
}
