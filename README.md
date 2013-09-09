awsmailer
=========

Generic e-mailer using Amazon SES SMTP Java Interface and Mustache Templates.

Package Hierarchy:
com.awsmailer.config
--> ConfigFileLoader: Toggles variable assignment of template directory based on environment ("dev", "stg", "prd")

com.awsmailer.dataobjects
--> Email: Stores releavnt email properties, optional reference to dynamic object used to populate template parameters (example refers to SimpleObject)
--> SimpleObject: Example object referenced by template to illustrate use of parameterization using Mustache

com.awsmailer.factory
--> GenericMailerFactory: Worker class responsible for assembling email from information stored in Email object and sending email via AWS SES.

com.awsmailer.templatizer
--> Transformer: Uses template and reference object specified in the email object and returns String Buffer holding merged email content.

com.awsmailer.test
--> TestGenericMailerFactory: Simple email test sending out email, where all data is stored in Email Object
--> TestGMFWithTemplate: Email test using mustache template and properties stored in SimpleObject to populate template parameters

Project Dependencies:
aws-java-sdk-1.4.1.jar
commons-codec-1.6.jar
commons-logging-1.1.1.jar
compiler-0.8.11.jar
guava-12.0.jar
httpclient-4.2.5.jar
httpcore-4.2.4.jar
log4j-1.2.11.jar
mail.jar
