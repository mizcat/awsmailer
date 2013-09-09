package com.awsmailer.templatizer;

import com.awsmailer.config.ConfigFileLoader;
import com.awsmailer.dataobjects.Email;
import com.awsmailer.dataobjects.SimpleObject;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Transformer {
	
	private static DecimalFormat fmtObj = new DecimalFormat("####0.00");
	private static DecimalFormat twoDForm = new DecimalFormat("#.##");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");  
	
	
	public static StringBuffer transform(Email email, String environment) throws IOException {
		
		ConfigFileLoader cfl = new ConfigFileLoader();		
		double decimal1 = 0;				

		Writer writer = null;
	 
	    try{
			String directory = cfl.getFile(environment);
			HashMap<String, Object> scopes = new HashMap<String, Object>();
		    scopes.put("o", email.getContentObject());
		    
		    writer = new StringWriter();
		    MustacheFactory mf = new DefaultMustacheFactory();
		    
		    // Open a stream to read the template passed to the action
		    String appPath = new java.io.File("").getCanonicalPath() + "/root/WEB-INF/lib/views/";
		    
		    InputStreamReader reader = new FileReader(appPath + email.getTemplate() + ".mustache");	
		   
		    Mustache mustache =  mf.compile(reader, "Transformer");
		    mustache.execute(writer, scopes);
		    return new StringBuffer(writer.toString());
		 } catch (Exception e){
		    	e.printStackTrace();
		 } finally {
	    	if(writer != null)
	    		writer.flush();
	    }
	    return null;
	}
}

