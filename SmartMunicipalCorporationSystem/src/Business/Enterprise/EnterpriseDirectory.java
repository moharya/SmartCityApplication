/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import java.util.ArrayList;

/**
 *
 * @author Amogh
 */
public class EnterpriseDirectory {
    
    private ArrayList<Enterprise> enterpriseList;

    public EnterpriseDirectory() {
        enterpriseList = new ArrayList<>();
    }

    public ArrayList<Enterprise> getEnterpriseList() {
        return enterpriseList;
    }
    
    public Enterprise createAndAddEnterprise(String name, Enterprise.EnterpriseType type){
        Enterprise enterprise = null;
        if (type == Enterprise.EnterpriseType.Electricity){ 
            enterprise = new ElectricityEnterprise(name);
            enterpriseList.add(enterprise);
        }
        if (type == Enterprise.EnterpriseType.Construction){ 
            enterprise = new ConstructionEnterprise(name);
            enterpriseList.add(enterprise);
        }
        if (type == Enterprise.EnterpriseType.Water){ 
            enterprise = new WaterEnterprise(name);
            enterpriseList.add(enterprise);
        }
        if (type == Enterprise.EnterpriseType.Citizen){
            enterprise = new CitizenEnterprise(name);
            enterpriseList.add(enterprise);
        }
        if (type == Enterprise.EnterpriseType.Monitoring){
            enterprise = new MonitoringEnterprise(name);
            enterpriseList.add(enterprise);
        }
        return enterprise;
    }
    
}
