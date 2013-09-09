package com.awsmailer.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;

import com.awsmailer.config.ConfigFileLoader;
import com.awsmailer.dataobjects.Email;
import com.awsmailer.dataobjects.SimpleObject;
import com.awsmailer.factory.GenericMailerFactory;
import com.awsmailer.templatizer.Transformer;

public class TestGMFWithTransformer {
	
	public final static String ENVIRONMENT = ConfigFileLoader.DEVELOPMENT;
	public static final String TO = "recipient@test.com";
	public static final String FROM = "sender@test.com"; /* be sure this sender is an approved sender in your aws ses console */
	private static final String SUBJECT = "Testing AWSMailer: Generic Mailer Factory";
	private static final String CC_1 = "cc1@test.com";
	private static final String CC_2 = "cc2@test.com";

    public static final Calendar currentCal = Calendar.getInstance();		
	public final static Date today = currentCal.getTime();


	public static void main (String [] args) throws Exception {
		Email emailObj = new Email();
		emailObj.setSender(new InternetAddress(FROM));
		List<InternetAddress> recipients = new ArrayList();
		recipients.add(new InternetAddress(TO));
		emailObj.setRecipients(recipients);
		emailObj.setSubject(SUBJECT);
		emailObj.setTemplate("example");
		
		// Populate object representing content populating dynamic field specified in template
		SimpleObject obj = new SimpleObject();
		obj.setDynamic1("Tester");
		obj.setDynamic2("You rock!");
		obj.setDate(today);
		
		emailObj.setContentObject(obj);
		
		// Merge the template with the content specified in the simple object		
		emailObj.setContents(Transformer.transform(emailObj, ENVIRONMENT));
				
		List<InternetAddress> bccRecipients = new ArrayList();
		bccRecipients.add(new InternetAddress(CC_1));
		bccRecipients.add(new InternetAddress(CC_2));
		
		emailObj.setBccRecipients(bccRecipients);
 		
		Boolean status = GenericMailerFactory.send(emailObj, "HTML");
		if(status)
			System.out.println("test completed successfully!");
		else
			System.out.println("test failed");
	    
	} // main
    
}

