/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Utility;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Person.CitizenOrganizationEmployee;
import Business.UserAccount.UserAccount;
import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.codemonkey.simplejavamail.Email;
import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;

/**
 *
 * @author prashant
 */
public class Validation 
{
    public static boolean validateTableSelection(JTable table, int noOfRows)
    {
        if(table.getSelectedRowCount()==noOfRows)
        return true;
        else return false;
    }
    
    public static Timestamp getTimeStamp()
    {
         Date date = new Date();
    Timestamp timeStamp = new Timestamp(date.getTime());

        
        return timeStamp;
    }
    
      public static void sendMoneyWithdrawSms(CitizenOrganizationEmployee r, float donation)
    {
        String phoneNo = r.getPhone();
        String name = r.getFirstName();
               Email email = new Email();

email.setFromAddress("Direct Aid", "praneet.solanki@gmail.com");
email.setSubject("Money Withdrawn");
email.addRecipient(name, phoneNo+"@tmomail.net", Message.RecipientType.TO);
//email.addRecipient("C. Bo", "chocobo@candyshop.org", RecipientType.BCC);
email.setText("Hello "+ name +"    Amount " + "$ "+ donation + " is withdrawn from your account. If you have not made this transaction, call +1800800800");
//email.setTextHTML("<img src='cid:wink1'><b>We should meet up!</b><img src='cid:wink2'>");

// embed images and include downloadable attachments


//new Mailer("smtp.gmail.com", 465, "praneetsolankineu@gmail.com", "praneet@22").sendMail(email);
new Mailer("smtp.gmail.com", 465, "directaidneu@gmail.com", "praneet22", TransportStrategy.SMTP_SSL).sendMail(email);

    }
    
    public static void sendEmailSms(CitizenOrganizationEmployee r, float donation)
    {
        String phoneNo = r.getPhone();
        String name = r.getFirstName();
               Email email = new Email();

email.setFromAddress("Direct Aid", "praneet.solanki@gmail.com");
email.setSubject("Money Deposited");
email.addRecipient(name, phoneNo+"@tmomail.net", Message.RecipientType.TO);
//email.addRecipient("C. Bo", "chocobo@candyshop.org", RecipientType.BCC);
email.setText("Hello "+ name +"    Amount " + "$ "+ donation + " Deposited to your Account");
//email.setTextHTML("<img src='cid:wink1'><b>We should meet up!</b><img src='cid:wink2'>");

// embed images and include downloadable attachments


//new Mailer("smtp.gmail.com", 465, "praneetsolankineu@gmail.com", "praneet@22").sendMail(email);
new Mailer("smtp.gmail.com", 465, "directaidneu@gmail.com", "praneet22", TransportStrategy.SMTP_SSL).sendMail(email);

    }
    
    public static boolean validateTextFieldsForAmount(JTextField textField)
    {
        try
        {
            Double value=Double.parseDouble(textField.getText().trim());
            if(value<=0)
            {
                return false;
            }
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }
    
//    public static float donatedAmountTotalMonth(EcoSystem business)
//    {
//        float sum =0;
//     
//        
//       
//        
//          for(Network net : business.getNetworkList())
//          {
//        for(Enterprise ent : net.getEnterpriseDirectory().getEnterpriseList())
//        {
//            if(ent instanceof DirectAidEnterprise)
//            {           
//                
//            DirectAidEnterprise entda = (DirectAidEnterprise)ent;    
//            
//       for (Transaction t : entda.getTransactionList().gettransactionList())
//       {
//               
//           
//           
//           sum = sum + t.getDonationAmount();
//       }
//        
//        
//        
//       
//                
//    }
//        } 
//          }
//          return sum;
//    }
    
    public static boolean validateTextFieldForPercentage(JTextField textField)
    {
        try
        {
            Double percentage=Double.parseDouble(textField.getText().trim());
            if(percentage<=100.0 &&percentage>=0.0)
            {
                return true;
            }
            else
             return false;
            
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    public static boolean validateTextAreaForNonEmpty(JTextArea txtArea )
    {
        if(txtArea.getText().trim().equalsIgnoreCase(""))
        {
            return false;
        }
        else return true;
    }
    
    public static boolean validateTextFieldsForNonEmpty(JTextField textField)
    {
        if(textField.getText().trim().equalsIgnoreCase(""))
        {
            return false;
        }
        else return true;
    }
    
    public static boolean validateTextFieldsForString(JTextField textField)
    {
        return true;
    }
   
    public static boolean validateTextFieldsForInteger(JTextField textField)
    {
        try
        {
            int a=Integer.parseInt(textField.getText().trim());
            if(a<0)
            {
                return false;
            }
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }
    
    public static float calculateDAPercentage(float amount)
    {
        
        float percentValue  = (float) (0.04*amount);
        
        
        return percentValue;
    }
    
     public static float calculateMPPercentage(float amount)
    {
        float percentValue=0;
        percentValue  = (float) (0.0018*amount);
        
        
        return percentValue;
    }
     
      public static float calculateVendorPercentage(float amount)
    {
        float percentValue=0;
        percentValue  = (float) (0.0010*amount);
        
        
        return percentValue;
    }
     
     
     
    public static boolean validateTextFieldsForNumber(JTextField textField)
    {
        return true;
    }
    
    public static boolean validateTextFieldsForPhoneNumber(JTextField textField)
    {
        Pattern patternMobileNumber = Pattern.compile("\\d{10}");
        Matcher matcher = patternMobileNumber.matcher(textField.getText().trim());
        return matcher.matches();
    }
    
    public static boolean validateTextFieldsForEmailId(JTextField textField)
    {
        try
        {
        String patternEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Boolean flag = textField.getText().trim().matches(patternEmail);
        return flag;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    public static boolean validateTextFieldsForUserName(JTextField txtUserName)
    {
        return true;
    }
    
    
    
    public static boolean validateComboBoxForSelection(JComboBox comboBox)
    {
        if(comboBox.getSelectedIndex()!=-1)
        return true;
        else 
        return false;
        
    }
    
    public static boolean validateTextFieldForPassword(JTextField txtPassowrd)
    {
       return true;
    }
    
     public static boolean checkUniquenessOfUserName(String userName, EcoSystem system) {
        boolean flag = false;
        
        for(UserAccount ua:system.getUserAccountDirectory().getUserAccountList()){
        
        if (ua.getUsername().equals(userName)) {
                        flag = true;
                        break;
                    }
        
        }
        
        
        
        for (Network n : system.getNetworkList()) {
 
            for (Enterprise e : n.getEnterpriseDirectory().getEnterpriseList()) {
                for (UserAccount ue : e.getUserAccountDirectory().getUserAccountList()) {
                    if (ue.getUsername().equals(userName)) {
                        flag = true;
                        break;
                    }
                }
                for (Organization o : e.getOrganizationDirectory().getOrganizationList()) {
                    for (UserAccount u : o.getUserAccountDirectory().getUserAccountList()) {
                        if (u.getUsername().equals(userName)) {
                            flag = true;
                        }
                    }
                }
            }
        }
        return flag;
    }
     
     public static boolean checkUniquenessOfPhoneNumber(String phone, EcoSystem system) {
        
        boolean flag = false;
        
        for(UserAccount ua:system.getUserAccountDirectory().getUserAccountList()){
        if(ua.getEmployee().getPhone()!=null){
        if (ua.getEmployee().getPhone().equals(phone)) {
                        flag = true;
                        break;
                    }
        
        }
        }
        for (Network n : system.getNetworkList()) {
 
            for (Enterprise e : n.getEnterpriseDirectory().getEnterpriseList()) {
                for (UserAccount ue : e.getUserAccountDirectory().getUserAccountList()) {
                    if(ue.getEmployee().getPhone()!=null){
                    if (ue.getEmployee().getPhone().equals(phone)) {
                        flag = true;
                        break;
                    }
                    }
                }
                 
                for (Organization o : e.getOrganizationDirectory().getOrganizationList()) {
                    for (UserAccount u : o.getUserAccountDirectory().getUserAccountList()) {
                        if(u.getEmployee().getPhone()!=null){
                        if (u.getEmployee().getPhone().equals(phone)) {
                            flag = true;
                        }
                        }
                    }
                }
            }
        }
        return flag;
    }
}

