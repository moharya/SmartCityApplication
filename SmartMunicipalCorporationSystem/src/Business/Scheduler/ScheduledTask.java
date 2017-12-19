/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Scheduler;

import Business.EcoSystem;
import Business.Organization.Municipal.MonitoringOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkeQueue.WorkRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;
import javax.swing.JPanel;
import userinterface.MonitoringRole.MonitoringWorkAreaJPanel;

/**
 *
 * @author prashant
 */


public class ScheduledTask extends TimerTask{
    
    private JPanel userProcessContainer;
    private EcoSystem business;
    private UserAccount userAccount;
    private MonitoringOrganization monitoringOrganization;
           Date now; // to display current time

    public ScheduledTask(JPanel userProcessContainer, UserAccount account, MonitoringOrganization monitoringOrganization, EcoSystem business) {
        this.userProcessContainer = userProcessContainer;
        this.userAccount = account;
        this.business = business;
        this.monitoringOrganization = monitoringOrganization;
        }
         
	// Add your task here
	public void run() {
	MonitoringWorkAreaJPanel monitoringWorkAreaJPanel=new MonitoringWorkAreaJPanel(userProcessContainer, userAccount, monitoringOrganization, business);
//        monitoringWorkAreaJPanel.processPublicRequests();
        monitoringWorkAreaJPanel.populateTable();
       
	}
    
}
