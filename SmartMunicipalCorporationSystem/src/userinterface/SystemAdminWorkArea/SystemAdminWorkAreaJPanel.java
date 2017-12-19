/*
 * AdminWorkAreaJPanel.java
 *
 * Created on October 10, 2008, 8:50 AM
 */
package userinterface.SystemAdminWorkArea;

import Business.EcoSystem;
import Business.Enterprise.CitizenEnterprise;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Municipal.CitizenOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkeQueue.WorkRequest;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Amogh
 */
public class SystemAdminWorkAreaJPanel extends javax.swing.JPanel {

    JPanel userProcessContainer;
    EcoSystem system;
    private  int electricityRequestCount=0;
    private  int waterRequestCount=0;
    private  int constructionRequestCount=0;

    /**
     * Creates new form AdminWorkAreaJPanel
     */
    public SystemAdminWorkAreaJPanel(JPanel userProcessContainer, EcoSystem system) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.system = system;

        populateTree();
    }

     public void populateTree() {
        
        DefaultTreeModel model = (DefaultTreeModel) JTree.getModel();

        
        
        ArrayList<Network> networkList = system.getNetworkList();
        ArrayList<Enterprise> enterpriseList;
        ArrayList<Organization> organizationList;
        Network network;
        Enterprise enterprise;
        Organization organization;

        DefaultMutableTreeNode networks = new DefaultMutableTreeNode("Networks");
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        root.removeAllChildren();
        root.insert(networks, 0);

        DefaultMutableTreeNode networkNode;
        DefaultMutableTreeNode enterpriseNode;
        DefaultMutableTreeNode organizationNode;
        for (int i = 0; i < networkList.size(); i++) {
            network = networkList.get(i);
            networkNode = new DefaultMutableTreeNode(network.getName());
            networks.insert(networkNode, i);

            enterpriseList = network.getEnterpriseDirectory().getEnterpriseList();

            for (int j = 0; j < enterpriseList.size(); j++) {
                enterprise = enterpriseList.get(j);
                enterpriseNode = new DefaultMutableTreeNode(enterprise.getName());
                networkNode.insert(enterpriseNode, j);

                organizationList = enterprise.getOrganizationDirectory().getOrganizationList();
                for (int k = 0; k < organizationList.size(); k++) {
                    organization = organizationList.get(k);
                    organizationNode = new DefaultMutableTreeNode(organization.getName());
                    enterpriseNode.insert(organizationNode, k);
                }
            }
        }

        model.reload();
    }
     
      public void populateTable(JPanel dataJPanel) {
        for (Network network : system.getNetworkList()) {
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                if(enterprise instanceof CitizenEnterprise){
                    for(Organization o:enterprise.getOrganizationDirectory().getOrganizationList()){
                        if(o instanceof CitizenOrganization){
                        for(UserAccount ua:o.getUserAccountDirectory().getUserAccountList()){
                            for(WorkRequest wr:ua.getWorkQueue().getWorkRequestList()){
                                if(wr.getRequestType().equals(WorkRequest.WorkRequestType.ElectricityWorkRequest.getValue())){
                                    electricityRequestCount++;
                                } else if(wr.getRequestType().equals(WorkRequest.WorkRequestType.WaterSupplyWorkRequest.getValue())){
                                    waterRequestCount++;
                                } else if (wr.getRequestType().equals(WorkRequest.WorkRequestType.ConstructionWorkRequest.getValue())) {
                                    constructionRequestCount++;
                                }
                            }
                        }
                    }
                    }
                }
                
            }
        }
        
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue("Electricity Requests", electricityRequestCount);
      dataset.setValue("Water Requests", waterRequestCount );
      dataset.setValue("Construction Requests", constructionRequestCount );

      JFreeChart chart = ChartFactory.createPieChart(
         "Requests Data By Each Enterprise Type Request",   // chart title
         dataset,          // data
         true,             // include legend
         true,
         false);
         
      int width = 250;   /* Width of the image */
      int height = 300;  /* Height of the image */ 
      File pieChart = new File( "PieChart.jpeg" ); 
        try {
            ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
        } catch (IOException ex) {
            Logger.getLogger(ManageRequestDataJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       // Plot pa=chart.getPlot();
        PiePlot pa = (PiePlot) chart.getPlot();
      //  pa.setRangeGridlinePaint(Color.black);
      PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        pa.setLabelGenerator(gen);
//        ChartFrame frame = new ChartFrame("Sales Perfromance Review", chart);
//        frame.setVisible(true);
//        frame.setSize(width, height);
        ChartPanel CP = new ChartPanel(chart);
        //CP.setSize(200, 200);
        BorderLayout absoluteLayout=new BorderLayout();
        JButton b=createBack();
        //b.setLayout(absoluteLayout);
//        b.add(b,BorderLayout.EAST);
        //CP.add(createBack());
        Color c=new Color(33, 33, 33);
        b.setBackground(c);
        Image img=null;
        try {
             img = ImageIO.read(getClass().getResource("/userinterface/Pictures/1417743630_go-previous-24.png"));
        } catch (IOException ex) {
            Logger.getLogger(SystemAdminWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        Icon i=new ImageIcon(img);
        b.setIcon(i);
        CP.setLayout(new BoxLayout(CP, BoxLayout.PAGE_AXIS));
        CP.add(b);
        add(CP,BorderLayout.WEST);
        dataJPanel.setLayout(absoluteLayout);
        dataJPanel.add(CP);
        dataJPanel.validate();
         b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            userProcessContainer.remove(CP);
            userProcessContainer.remove(dataJPanel);
            CardLayout cardLayout = (CardLayout) userProcessContainer.getLayout();
            cardLayout.previous(userProcessContainer);
              electricityRequestCount=0;
              waterRequestCount=0;
              constructionRequestCount=0;

               // using previous to get to previous(left)panel
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        manageNetworkJButton = new javax.swing.JButton();
        requestDataSummaryBtn = new javax.swing.JButton();
        manageEnterpriseJButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        selectedNodeJLabel = new javax.swing.JLabel();
        manageAdminJButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTree = new javax.swing.JTree();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(150);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manageNetworkJButton.setBackground(new java.awt.Color(51, 51, 51));
        manageNetworkJButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        manageNetworkJButton.setForeground(new java.awt.Color(255, 255, 255));
        manageNetworkJButton.setText("Manage Network");
        manageNetworkJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageNetworkJButtonActionPerformed(evt);
            }
        });
        jPanel1.add(manageNetworkJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 290, 40));

        requestDataSummaryBtn.setBackground(new java.awt.Color(51, 51, 51));
        requestDataSummaryBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        requestDataSummaryBtn.setForeground(new java.awt.Color(255, 255, 255));
        requestDataSummaryBtn.setText("Request Data Summary");
        requestDataSummaryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestDataSummaryBtnActionPerformed(evt);
            }
        });
        jPanel1.add(requestDataSummaryBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 290, 40));

        manageEnterpriseJButton.setBackground(new java.awt.Color(51, 51, 51));
        manageEnterpriseJButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        manageEnterpriseJButton.setForeground(new java.awt.Color(255, 255, 255));
        manageEnterpriseJButton.setText("Manage Enterprise");
        manageEnterpriseJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageEnterpriseJButtonActionPerformed(evt);
            }
        });
        jPanel1.add(manageEnterpriseJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 290, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Selected Node:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, -1));

        selectedNodeJLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        selectedNodeJLabel.setText("<view_selected_node>");
        jPanel1.add(selectedNodeJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        manageAdminJButton1.setBackground(new java.awt.Color(51, 51, 51));
        manageAdminJButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        manageAdminJButton1.setForeground(new java.awt.Color(255, 255, 255));
        manageAdminJButton1.setText("Manage Enterprise Admin");
        manageAdminJButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageAdminJButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(manageAdminJButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 290, 40));

        jSplitPane1.setRightComponent(jPanel1);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 332));

        JTree.setBackground(new java.awt.Color(204, 204, 204));
        JTree.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("System");
        JTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        JTree.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JTree.setMaximumSize(new java.awt.Dimension(110, 16));
        JTree.setPreferredSize(new java.awt.Dimension(200, 16));
        JTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                valueChangedAction(evt);
            }
        });
        jScrollPane1.setViewportView(JTree);

        jPanel2.add(jScrollPane1);

        jSplitPane1.setLeftComponent(jPanel2);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void manageEnterpriseJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageEnterpriseJButtonActionPerformed

        ManageEnterpriseJPanel manageEnterpriseJPanel = new ManageEnterpriseJPanel(userProcessContainer, system);
        userProcessContainer.add("manageEnterpriseJPanel", manageEnterpriseJPanel);

        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_manageEnterpriseJButtonActionPerformed

    private void requestDataSummaryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestDataSummaryBtnActionPerformed
        // TODO add your handling code here:
        ManageRequestDataJPanel manageRequestDataJPanel = new ManageRequestDataJPanel(userProcessContainer, system);
        userProcessContainer.add("manageRequestDataJPanel", manageRequestDataJPanel);
        BorderLayout bl=new BorderLayout();
        manageRequestDataJPanel.setLayout(bl);
        //manageRequestDataJPanel.removeAll();
        //manageRequestDataJPanel.add(createZoom());
        populateTable(manageRequestDataJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_requestDataSummaryBtnActionPerformed

    private void manageNetworkJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageNetworkJButtonActionPerformed
        ManageNetworkJPanel manageNetworkJPanel = new ManageNetworkJPanel(userProcessContainer, system);
        userProcessContainer.add("manageNetworkJPanel", manageNetworkJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_manageNetworkJButtonActionPerformed

    private void valueChangedAction(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_valueChangedAction
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) JTree.getLastSelectedPathComponent();
        if (selectedNode != null) {
            selectedNodeJLabel.setText(selectedNode.toString());
        }

    }//GEN-LAST:event_valueChangedAction
 private JButton createBack() {
        final JButton back;
        back = new JButton();
        return back;
    }
    
    private void manageAdminJButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageAdminJButton1ActionPerformed
        // TODO add your handling code here:
        ManageEnterpriseAdminJPanel manageEnterpriseAdminJPanel = new ManageEnterpriseAdminJPanel(userProcessContainer, system);
        userProcessContainer.add("manageEnterpriseAdminJPanel", manageEnterpriseAdminJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_manageAdminJButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree JTree;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton manageAdminJButton1;
    private javax.swing.JButton manageEnterpriseJButton;
    private javax.swing.JButton manageNetworkJButton;
    private javax.swing.JButton requestDataSummaryBtn;
    private javax.swing.JLabel selectedNodeJLabel;
    // End of variables declaration//GEN-END:variables
}
