/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.MonitoringRole;

import Business.EcoSystem;
import Business.Enterprise.CitizenEnterprise;
import Business.Enterprise.ConstructionEnterprise;
import Business.Enterprise.ElectricityEnterprise;
import Business.Enterprise.Enterprise;
import Business.Enterprise.WaterEnterprise;
import Business.Network.Network;
import Business.Organization.Municipal.CitizenOrganization;
import Business.Organization.Municipal.MonitoringOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.Utility.Helper;
import Business.Utility.MapPOC;
import Business.Utility.OTPSender;
import Business.WorkeQueue.WorkRequest;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
//import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author prashant
 */
public class MonitoringWorkAreaJPanel extends javax.swing.JPanel {
    
    private JPanel userProcessContainer;
    private EcoSystem business;
    private UserAccount userAccount;
    private MonitoringOrganization monitoringOrganization;
    private ArrayList<WorkRequest> workRequestList=null;
    private Set<String> areaSet=null;
    private static Set<WorkRequest> requestsSecondary =new HashSet<>();
    

    /**
     * Creates new form MonitoringWorkAreaJPanel
     */
    public MonitoringWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, MonitoringOrganization monitoringOrganization, EcoSystem business) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = account;
        this.business = business;
        this.monitoringOrganization = monitoringOrganization;
//       monitoringOrganization.getWorkQueue().getWorkRequestList().clear();
         populateTable();
         
//          processPublicRequests();
//        forwardPublicRequest(workRequestList);
       // processMonitor();
    }
    
    
     
     
    
     public void populateTable(){
// workRequestJTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
//{
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
//    {
//        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//        c.setBackground(row % 2 == 0 ? Color.DARK_GRAY : Color.LIGHT_GRAY);
//        return c;
//    }
//});
         System.err.println("Inside populate first Table");
        DefaultTableModel model = (DefaultTableModel)workRequestJTable1.getModel();
        
        model.setRowCount(0);
        
        for(WorkRequest request : monitoringOrganization.getWorkQueue().getWorkRequestList()){
            Object[] row = new Object[6];
            row[0] = request;
            row[1] = request.getSender().getEmployee().getFirstName();
            row[2] = request.getReceiver() == null ? null : request.getReceiver().getEmployee().getFirstName();
            row[3] = request.getStatus();
            row[4]= request.getRequestType();
            row[5]=request.getCategory();
            model.addRow(row);
        }
    }
     public void populateSecondTable(Set<WorkRequest> workRequests){
         
         
         System.err.println("Inside populate Second Table");
//          workRequestSecondJTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
//{
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
//    {
//        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//        c.setBackground(row % 2 == 0 ? Color.darkGray : Color.WHITE);
//        return c;
//    }
//});
        DefaultTableModel model = (DefaultTableModel)workRequestSecondJTable.getModel();
        
        model.setRowCount(0);
        
        for(WorkRequest request : workRequests){
            Object[] row = new Object[6];
            row[0] = request;
            row[1] = request.getSender().getEmployee().getFirstName();
            row[2] = request.getReceiver() == null ? null : request.getReceiver().getEmployee().getFirstName();
            row[3] = request.getStatus();
            row[4]= request.getRequestType();
            row[5]=request.getCategory();
            model.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        processStartBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        workRequestSecondJTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        workRequestJTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(153, 153, 153));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        processStartBtn.setBackground(new java.awt.Color(51, 51, 51));
        processStartBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        processStartBtn.setForeground(new java.awt.Color(255, 255, 255));
        processStartBtn.setText("Process");
        processStartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processStartBtnActionPerformed(evt);
            }
        });
        add(processStartBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 260, 110, 40));

        workRequestSecondJTable.setBackground(new java.awt.Color(51, 51, 51));
        workRequestSecondJTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        workRequestSecondJTable.setForeground(new java.awt.Color(255, 255, 255));
        workRequestSecondJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Area Name", "Sender", "Receiver", "Status", "Request Type", "Category"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        workRequestSecondJTable.setRowHeight(20);
        jScrollPane1.setViewportView(workRequestSecondJTable);
        if (workRequestSecondJTable.getColumnModel().getColumnCount() > 0) {
            workRequestSecondJTable.getColumnModel().getColumn(0).setResizable(false);
            workRequestSecondJTable.getColumnModel().getColumn(1).setResizable(false);
            workRequestSecondJTable.getColumnModel().getColumn(2).setResizable(false);
            workRequestSecondJTable.getColumnModel().getColumn(3).setResizable(false);
            workRequestSecondJTable.getColumnModel().getColumn(4).setResizable(false);
            workRequestSecondJTable.getColumnModel().getColumn(5).setResizable(false);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 700, 170));

        workRequestJTable1.setBackground(new java.awt.Color(51, 51, 51));
        workRequestJTable1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        workRequestJTable1.setForeground(new java.awt.Color(255, 255, 255));
        workRequestJTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Area Name", "Sender", "Receiver", "Status", "Request Type", "Category"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        workRequestJTable1.setRowHeight(20);
        jScrollPane2.setViewportView(workRequestJTable1);
        if (workRequestJTable1.getColumnModel().getColumnCount() > 0) {
            workRequestJTable1.getColumnModel().getColumn(0).setResizable(false);
            workRequestJTable1.getColumnModel().getColumn(1).setResizable(false);
            workRequestJTable1.getColumnModel().getColumn(2).setResizable(false);
            workRequestJTable1.getColumnModel().getColumn(3).setResizable(false);
            workRequestJTable1.getColumnModel().getColumn(4).setResizable(false);
            workRequestJTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 700, 170));
    }// </editor-fold>//GEN-END:initComponents

    private void processStartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processStartBtnActionPerformed
         Timer timer = new Timer(125, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        processPublicRequests();
                        populateSecondTable(requestsSecondary);
                        populateTable();
                    }
                });
         timer.start();
    }//GEN-LAST:event_processStartBtnActionPerformed

    
    public void processPublicRequests(){
         workRequestList=new ArrayList<>();
         areaSet=new HashSet<>();
         for(int i=0;i<monitoringOrganization.getWorkQueue().getWorkRequestList().size();i++){
             int count=0;
             for(int j=0;j<monitoringOrganization.getWorkQueue().getWorkRequestList().size();j++){
                 if(monitoringOrganization.getWorkQueue().getWorkRequestList().get(i).equals(monitoringOrganization.getWorkQueue().getWorkRequestList().get(j))){
                     count++;
                 }
             }
              if(count>2){
                     workRequestList.add(monitoringOrganization.getWorkQueue().getWorkRequestList().get(i));
                     areaSet.add(monitoringOrganization.getWorkQueue().getWorkRequestList().get(i).getAddress().getAddressLine1());
                 }
         }
         System.out.println(workRequestList);
         forwardPublicRequest(workRequestList);
    }
    public void forwardPublicRequest(ArrayList<WorkRequest> workRequestList) {
         Set<WorkRequest> requests = new HashSet<>();
        if (workRequestList != null && areaSet != null) {

            for (String area : areaSet) {
                String elID = "EL" + Helper.randomID();
                String clID = "Cl" + Helper.randomID();
                String wtID = "WT" + Helper.randomID();
                String trID = "TR" + Helper.randomID();
                for (WorkRequest request : workRequestList) {
                    if (request.getAddress().getAddressLine1().equals(area) && request.getRequestType().equals(WorkRequest.WorkRequestType.ElectricityWorkRequest.getValue())) {
                        request.setGroupId(elID);
                        updateCitizenQueue(request, elID);
                        monitoringOrganization.getWorkQueue().getWorkRequestList().remove(request);
                        requests.add(request);
                    } else if (request.getAddress().getAddressLine1().equals(area) && request.getRequestType().equals(WorkRequest.WorkRequestType.ConstructionWorkRequest.getValue())) {
                        request.setGroupId(clID);
                        updateCitizenQueue(request, clID);
                        monitoringOrganization.getWorkQueue().getWorkRequestList().remove(request);
                        requests.add(request);
                    } else if (request.getAddress().getAddressLine1().equals(area) && request.getRequestType().equals(WorkRequest.WorkRequestType.WaterSupplyWorkRequest.getValue())) {
                        request.setGroupId(trID);
                        updateCitizenQueue(request, trID);
                        monitoringOrganization.getWorkQueue().getWorkRequestList().remove(request);
                        requests.add(request);
                    }
                }
            }
        }
        for (WorkRequest request : requests) {
                    try {
                        try {
                            createRequest(request);
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(MonitoringWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParserConfigurationException ex) {
                            Logger.getLogger(MonitoringWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SAXException ex) {
                            Logger.getLogger(MonitoringWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(MonitoringWorkAreaJPanel.class.getName()).log(Level.SEVERE, "Exception in Creating Request", ex);
                    }
                }
        System.out.println(requests);
        System.out.println(monitoringOrganization.getWorkQueue().getWorkRequestList());
    }
    public void updateCitizenQueue(WorkRequest request, String id) {
        for (Network network : business.getNetworkList()) {
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (enterprise instanceof CitizenEnterprise) {
                    for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                        if (organization instanceof CitizenOrganization) {
                            for (UserAccount userAccount : organization.getUserAccountDirectory().getUserAccountList()) {
                                for(WorkRequest workRequest:userAccount.getWorkQueue().getWorkRequestList()){
                                    if (workRequest == request) {
                                    workRequest.setGroupId(id);
                                } 
                                }
                               
                            }
                        }
                    }
                }
            }
        }
    }
    public void createRequest(WorkRequest request) throws IOException, MalformedURLException, ParserConfigurationException, SAXException {
        for (Network n : business.getNetworkList()) {
            for (Enterprise e : n.getEnterpriseDirectory().getEnterpriseList()) {
                if(request.getRequestType().equals(WorkRequest.WorkRequestType.ElectricityWorkRequest.getValue()) && e instanceof ElectricityEnterprise) { 
                    for (Organization organization : e.getOrganizationDirectory().getOrganizationList()) {
                        if(organization.getName().equalsIgnoreCase(request.getProviderName())) {
                            for (UserAccount userAccount : organization.getUserAccountDirectory().getUserAccountList()) {
                                if (userAccount.getEmployee().getAddress().getZipCode() == request.getAddress().getZipCode()) {
                                    request.setReceiver(userAccount);
                                    request.setStatus("Processing");
                                    userAccount.getWorkQueue().getWorkRequestList().add(request);
                                    if (userAccount.getEmployee().getPhone() != null) {
                                        String[] str=MapPOC.get(request.getAddress().getAddressLine1()+" "+n.getName(), "");
                                        OTPSender.send("You have been assigned a Public Request from " + request.getAddress().getAddressLine1()+" http://maps.google.com/?q="+str[0]+","+str[1], userAccount.getEmployee().getPhone());
                                    }
                                  
                                }
                            }
                        
                      }
                    }
                
            }else if (request.getRequestType().equals(WorkRequest.WorkRequestType.ConstructionWorkRequest.getValue()) && e instanceof ConstructionEnterprise) {

                    for (Organization organization : e.getOrganizationDirectory().getOrganizationList()) {
                        if(organization.getName().equalsIgnoreCase(request.getProviderName())) {
                            for (UserAccount userAccount : organization.getUserAccountDirectory().getUserAccountList()) {
                                if (userAccount.getEmployee().getAddress().getZipCode() == request.getAddress().getZipCode()) {
                                     request.setReceiver(userAccount);
                                     request.setStatus("Processing");
                                    userAccount.getWorkQueue().getWorkRequestList().add(request);
                                    if (userAccount.getEmployee().getPhone() != null) {
                                        String[] str=MapPOC.get(request.getAddress().getAddressLine1(), "MA");
                                        OTPSender.send("You have been assigned a Public Request from " + request.getAddress().getAddressLine1()+" http://maps.google.com/?q="+str[0]+","+str[1], userAccount.getEmployee().getPhone());
                                    }
                                }
                            }
                        }
                    }

                }  else if (request.getRequestType().equals(WorkRequest.WorkRequestType.WaterSupplyWorkRequest.getValue()) && e instanceof WaterEnterprise) {
                    for (Organization organization : e.getOrganizationDirectory().getOrganizationList()) {
                        if(organization.getName().equalsIgnoreCase(request.getProviderName())) {
                            for (UserAccount userAccount : organization.getUserAccountDirectory().getUserAccountList()) {
                                if (userAccount.getEmployee().getAddress().getZipCode() == request.getAddress().getZipCode()) {
                                    request.setReceiver(userAccount);
                                    userAccount.getWorkQueue().getWorkRequestList().add(request);
                                    if (userAccount.getEmployee().getPhone() != null) {
                                        String[] str=MapPOC.get(request.getAddress().getAddressLine1(), "MA");
                                        OTPSender.send("You have been assigned a Public Request from " + request.getAddress().getAddressLine1()+" http://maps.google.com/?q="+str[0]+","+str[1], userAccount.getEmployee().getPhone());
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
        requestsSecondary.add(request);
    }
    public void  writeToCSV(WorkRequest request){
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton processStartBtn;
    private javax.swing.JTable workRequestJTable1;
    private javax.swing.JTable workRequestSecondJTable;
    // End of variables declaration//GEN-END:variables
}
