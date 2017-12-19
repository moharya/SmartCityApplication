/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization.Municipal;

import Business.Organization.Organization;
import Business.Role.ConstructionManagerRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Amogh
 */
public class ConstructionOrganization extends Organization{
    public ConstructionOrganization() {
        super(Organization.Type.Construction.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new ConstructionManagerRole());
        return roles;
    }
}
