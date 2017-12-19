/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Organization.Municipal.CitizenOrganization;
import Business.Organization.Municipal.ConstructionOrganization;
import Business.Organization.Municipal.EverSourceElectricityOrganization;
import Business.Organization.Municipal.MonitoringOrganization;
import Business.Organization.Municipal.NationalGridElectricityOrganization;
import Business.Organization.Municipal.WaterOrganization;
import java.util.ArrayList;

/**
 *
 * @author prashant
 */
public class OrganizationDirectory {
    
      private ArrayList<Organization> organizationList;

    public OrganizationDirectory() {
        organizationList = new ArrayList<>();
       // createGovtOrganization();
       
    }

   public ArrayList<Organization> getOrganizationList() {
       return organizationList;
   }
   
   public Organization createOrganization(Organization.Type type){
       Organization organization = null;
       if (type.getValue().equals(Organization.Type.EverSource.getValue())){
           organization = new EverSourceElectricityOrganization();
           organizationList.add(organization);
       }else if (type.getValue().equals(Organization.Type.NationalGrid.getValue())){
           organization = new NationalGridElectricityOrganization();
           organizationList.add(organization);
       }else if (type.getValue().equals(Organization.Type.Citizen.getValue())){
           organization = new CitizenOrganization();
           organizationList.add(organization);
       }else if (type.getValue().equals(Organization.Type.Water.getValue())){
            organization = new WaterOrganization();
            organizationList.add(organization);
        }else if (type.getValue().equals(Organization.Type.Construction.getValue())){
            organization = new ConstructionOrganization();
            organizationList.add(organization);
        }else if (type.getValue().equals(Organization.Type.Monitoring.getValue())){
           organization = new MonitoringOrganization();
           organizationList.add(organization);
       }
    
       return organization;
   }

}
