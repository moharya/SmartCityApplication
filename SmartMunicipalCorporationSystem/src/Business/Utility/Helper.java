/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Utility;

import Business.EcoSystem;
import Business.Enterprise.CitizenEnterprise;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Municipal.CitizenOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkeQueue.WorkRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author prashant
 */
public class Helper {
    
 public static String randomID(){
 char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
Random rnd = new Random();
StringBuilder sb = new StringBuilder((100000 + rnd.nextInt(900000)) + "-");
for (int i = 0; i < 5; i++)
{
    sb.append(chars[rnd.nextInt(chars.length)]);
}

return sb.toString();
    }
 
 public static void updateCitizenQueue(EcoSystem business,String id) throws IOException {
        for (Network network : business.getNetworkList()) {
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (enterprise instanceof CitizenEnterprise) {
                    for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                        if (organization instanceof CitizenOrganization) {
                            for (UserAccount userAccount : organization.getUserAccountDirectory().getUserAccountList()) {
                                for(WorkRequest workRequest:userAccount.getWorkQueue().getWorkRequestList()){
                                    if(workRequest.getGroupId()!=null){
                                    if(workRequest.getGroupId().equals(id)){
                                        workRequest.setStatus("Completed");
                                        workRequest.setResolveDate(new Date());
                                        if (userAccount.getEmployee().getPhone()!=null)
                                        {
                                            OTPSender.send("Your request" +workRequest.getGroupId()+"has been completed", userAccount.getEmployee().getPhone());
                                        }
                                    }
                                    }

                        }
                    }
                }
            }
        }
    }
            
        }
      }
 public static void updateCitizenQueueProcessing(EcoSystem business,String id) throws IOException {
        for (Network network : business.getNetworkList()) {
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (enterprise instanceof CitizenEnterprise) {
                    for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                        if (organization instanceof CitizenOrganization) {
                            for (UserAccount userAccount : organization.getUserAccountDirectory().getUserAccountList()) {
                                for(WorkRequest workRequest:userAccount.getWorkQueue().getWorkRequestList()){
                                    if(workRequest.getGroupId()!=null){
                                    if(workRequest.getGroupId().equals(id)){
                                        workRequest.setStatus("Processing");
                                    }
                                    }

                        }
                    }
                }
            }
        }
    }
            
        }
      }
 public static Boolean valiDateAssignment(WorkRequest request){
     if(request.getReceiver()!=null){
         return true;
     }
     return false;
 }
  public static Boolean valiDateCompleted(WorkRequest request){
     if(request.getStatus().equalsIgnoreCase("Completed")){
         return true;
     }
     return false;
 }
}
