package com.awsmailer.factories;


import com.awsmailer.config.ConfigFileLoader;
import org.apache.log4j.Logger;
import com.amazonaws.services.simpleemail.*;
import com.amazonaws.services.simpleemail.model.*;
import com.amazonaws.auth.PropertiesCredentials;

public class GenericMailerFactory {
	
	private static final Logger LOGGER = Logger.getLogger(GenericMailerFactory.class);

	public static Boolean send(String emailTo, String emailFrom, String emailSubject, String content, String environment) {
	    ConfigFileLoader cfl = new ConfigFileLoader();
        PropertiesCredentials credentials = cfl.getAWSCredentails();
        
        // Retrieve the AWS Access Key ID and Secret Key from AwsCredentials.properties.
        credentials.getAWSAccessKeyId();
        credentials.getAWSSecretKey();
    
        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(new String[]{emailTo});
        
        // Create the subject and body of the message.
        Content subject = new Content().withData(emailSubject);
        Content htmlBody = new Content().withData(content); 
        Body body = new Body().withHtml(htmlBody);
        
        // Create a message with the specified subject and body.
        Message message = new Message().withSubject(subject).withBody(body);
        
        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(emailFrom).withDestination(destination).withMessage(message);
        
        try
        {        
            LOGGER.debug("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");
        
            // Instantiate an Amazon SES client, which will make the service call with the supplied AWS credentials.
            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);
       
            // Send the email.
            client.sendEmail(request);  
            LOGGER.debug("Email sent!");
            return true;
        }
        catch (Exception ex) 
        {
           LOGGER.debug("GenericMailerFactory Error message: " + ex.getMessage());
        }
        return false;
	} //send
		
	}
