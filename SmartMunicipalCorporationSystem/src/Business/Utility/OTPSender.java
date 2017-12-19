/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Random;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author prashant
 */
public class OTPSender {
    
    public static void send(String myMessage,String toPhoneNumber) throws IOException{
     String myPasscode = "4719";
        String myUsername = "fundrazing";    
       // String toPhoneNumber = "+16179436761";
        //String myMessage = "12345";
        HttpClient client = new DefaultHttpClient();
        String myMessageEncoded= URLEncoder.encode(myMessage, "UTF-8");
         HttpGet request = new HttpGet("http://cloud.fowiz.com/api/message_http_api.php?username="+myUsername+"&phonenumber="+toPhoneNumber+"&message="+myMessageEncoded+"&passcode="+myPasscode);
         
          HttpResponse response = client.execute(request);
          
           BufferedReader rd = new BufferedReader
          (new InputStreamReader(response.getEntity().getContent()));
    
        String line = "";
        StringBuffer response1 = new StringBuffer();
        while ((line = rd.readLine()) != null) {
                  response1.append(line);
        }   
       
        System.out.println(response1.toString());
          
    }
    
   public static String OTP(int len)
    {
        System.out.println("Generating OTP using random() : ");
        System.out.print("You OTP is : ");
 
        // Using numeric values
        String numbers = "0123456789";
 
        // Using random method
        Random rndm_method = new Random();
 
        char[] otp = new char[len];
 
        for (int i = 0; i < len; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            otp[i] =
             numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        String str=new String(otp);
        return str;
    }
    public static void main(String args[]) throws IOException{
        System.out.println(OTPSender.OTP(4));
    }     
}
