/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization.Municipal;

import Business.Organization.Organization;
import Business.Role.Role;
import Business.Role.TransportationManagerRole;
import Business.Role.WaterManagerRole;
import java.util.ArrayList;

/**
 *
 * @author prashant
 */
public class WaterOrganization extends Organization{

    public WaterOrganization() {
        super(Organization.Type.Water.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new WaterManagerRole());
        return roles;
    }
    
}
