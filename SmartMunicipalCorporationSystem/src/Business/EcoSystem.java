package Business;

import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.Role.SystemAdminRole;
import Business.UserAccount.UserAccount;
import java.util.ArrayList;

/**
 *
 * @author Amogh
 */
public class EcoSystem extends Organization {

    private static EcoSystem business;
    private ArrayList<Network> networkList;

    public static EcoSystem getInstance() {
        if (business == null) {
            business = new EcoSystem();
        }
        return business;
    }

    private EcoSystem() {
        super(null);
        networkList = new ArrayList<>();
    }

    public ArrayList<Network> getNetworkList() {
        return networkList;
    }

    public Network createAndAddNetwork() {
        Network network = new Network();
        networkList.add(network);
        return network;
    }

    
    

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new SystemAdminRole());
        return roleList;
    }

    public boolean checkIfUsernameIsUnique(String userName) {
        
         boolean flag = true;
        if (!this.getUserAccountDirectory().checkIfUsernameIsUnique(userName)) {
            flag = false;
        }

        for (Network n : this.getNetworkList()) {
 
            for (Enterprise e : n.getEnterpriseDirectory().getEnterpriseList()) {
                for (UserAccount ue : e.getUserAccountDirectory().getUserAccountList()) {
                    if (ue.getUsername().equals(userName)) {
                        flag = false;
                        break;
                    }
                }
                for (Organization o : e.getOrganizationDirectory().getOrganizationList()) {
                    for (UserAccount u : o.getUserAccountDirectory().getUserAccountList()) {
                        if (u.getUsername().equals(userName)) {
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }
}