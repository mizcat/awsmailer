package com.awsmailer.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.mail.internet.InternetAddress;

import com.awsmailer.config.ConfigFileLoader;
import com.awsmailer.dataobjects.Email;

import org.apache.log4j.Logger;
import com.amazonaws.services.simpleemail.*;
import com.amazonaws.services.simpleemail.model.*;
import com.amazonaws.auth.PropertiesCredentials;

public class GenericMailerFactory {
	
	private static final Logger LOGGER = Logger.getLogger(GenericMailerFactory.class);

	public static Boolean send(Email email, String format) {
	    ConfigFileLoader cfl = new ConfigFileLoader();
        PropertiesCredentials credentials = cfl.getAWSCredentails();
        
        /* Retrieve the AWS Access Key ID and Secret Key from AwsCredentials.properties. */
        credentials.getAWSAccessKeyId();
        credentials.getAWSSecretKey();
    
        Destination destination = new Destination().withToAddresses(ListToStringArray(email.getRecipients()));
        
        /* add cc addresses to destination if applicable */
        if(email.getCcRecipients() != null && email.getCcRecipients().size() > 0) {
        	List<String>cc = new ArrayList();
        	Collections.addAll(cc, ListToStringArray(email.getCcRecipients()));
        	destination.setCcAddresses(cc);
        }
        
        /* add bcc addresses to destination if applicable */
        if(email.getBccRecipients() != null && email.getBccRecipients().size() > 0) {
        	List<String> bcc = new ArrayList();
        	Collections.addAll(bcc, ListToStringArray(email.getBccRecipients()));
        	destination.setBccAddresses(bcc);
        }        	        
        
        /* Create the subject and body of the message. */
        Content subject = new Content().withData(email.getSubject());
        Content contents = new Content().withData(email.getContents().toString());         
        Body body;
        
        /* specify mime type of email (HTML, plaintext, or if unspecified: both) */       
        if("HTML".equalsIgnoreCase(format)) 
        	body = new Body().withHtml(contents);
        else if("TEXT".equalsIgnoreCase(format))
        	body = new Body().withText(contents);
        else
        	body = new Body().withHtml(contents).withText(contents);
        
        /* Create a message with the specified subject and body. */
        Message message = new Message().withSubject(subject).withBody(body);
        
        /* Assemble the email. */
        SendEmailRequest request = new SendEmailRequest().withSource(email.getSender().getAddress()).withDestination(destination).withMessage(message);       
        
        try {    
            LOGGER.debug("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");
        
            /* Instantiate an Amazon SES client, which will make the service call with the supplied AWS credentials. */
            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);
       
            /* Send the email */
            LOGGER.debug("Attempting to send email");
            client.sendEmail(request);  
            LOGGER.debug("Email sent!");
            return true;
        } catch (Exception ex) {         
           LOGGER.error("GenericMailerFactory Error message: " + ex.getMessage());
        }
        return false;
	} /* send */
		
	
	/* Internal "helper" method to convert list to string array */
	private static String[] ListToStringArray(List<InternetAddress> input) {
        // Construct an object to contain the recipient address.
        String[] stringArray = new String[input.size()];
        int i = 0;
        for (InternetAddress ia : input) {
        	stringArray[i++] = ia.getAddress();
        }
        return stringArray;
	}
} /* GenericMailerFactory */
