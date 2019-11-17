package acs.Tools;

import java.io.BufferedReader; 
import java.io.DataInputStream; 
import java.io.FileNotFoundException; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader;
import java.lang.invoke.ConstantCallSite;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.text.Normalizer; 
import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.Calendar; 
import java.util.Date; 
import java.util.Random; 
import java.util.TimeZone; 
 
import org.apache.commons.lang3.StringUtils; 
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.properties.EncryptableProperties;
import org.jasypt.salt.RandomSaltGenerator;
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
 
public class Util { 
  
 private static Logger logger = LoggerFactory.getLogger(Util.class); 
  
 private static EncryptableProperties properties = new EncryptableProperties(new StandardPBEStringEncryptor()); 

 
 public static String encrypted_string(String _str, String _password){
	 /*
	   StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
	   encryptor.setPassword(_password);                     // we HAVE TO set a password
	   encryptor.setAlgorithm("PBEWithMD5AndTripleDES");    // optionally set the algorithm
	   String encryptedText = encryptor.encrypt(_encrypted_str);
	   return encryptedText;
	   */
		 try {
			   StandardPBEStringEncryptor pwEncrypt = new StandardPBEStringEncryptor();
			                    
			   pwEncrypt.setAlgorithm("PBEWithMD5AndTripleDES");    // optionally set the algorithm
			   RandomSaltGenerator salt = new RandomSaltGenerator();
			   salt.includePlainSaltInEncryptionResults();
			   pwEncrypt.setSaltGenerator(salt);	
			   pwEncrypt.setPassword(_password); // we HAVE TO set a password
			   pwEncrypt.setProvider(Security.getProvider("SunJCE"));
			   pwEncrypt.setKeyObtentionIterations(2000);
			   String encryptedText = pwEncrypt.encrypt(_str);
			   return encryptedText;
			 } catch (EncryptionOperationNotPossibleException e) {
				 logger.debug(e.getMessage());
					logger.debug("\tIncorrect String!");
					return "";
			 }	   
			 
	   
 }
 public static String decrypted_string(String _decrypted_string, String _password){
	 try {
	   StandardPBEStringEncryptor pwEncrypt = new StandardPBEStringEncryptor();
	                    
	   pwEncrypt.setAlgorithm("PBEWithMD5AndTripleDES");    // optionally set the algorithm
	   RandomSaltGenerator salt = new RandomSaltGenerator();
	   salt.includePlainSaltInEncryptionResults();
	   pwEncrypt.setSaltGenerator(salt);	
	   pwEncrypt.setPassword(_password); // we HAVE TO set a password
	   pwEncrypt.setProvider(Security.getProvider("SunJCE"));
	   pwEncrypt.setKeyObtentionIterations(2000);
	   String plainText = pwEncrypt.decrypt(_decrypted_string);
	   return plainText;
	 } catch (EncryptionOperationNotPossibleException e) {
			logger.debug("\tIncorrect password!");
			return "";
	 }	   
	 
}

 public static String get_ServerIP(){
	 
	 
	  try {
		  	InetAddress _InetAddress = InetAddress.getLocalHost();
			logger.debug("Current IP address : " + _InetAddress.getLocalHost());
			return _InetAddress.getHostAddress();
	  } catch (UnknownHostException e) {
		  e.printStackTrace();
		  return SYSConstant.get_ServerNotFound();
	

	  }	 
	 
 }
 
  /* Loads properties from file. 
  * @param configurationFileName .properties file name. 
  * @param password Jasypt encription password. 
  */ 
 public static void loadProperties(String configurationFileName, String password) { 
  try { 
   logger.info("Loading properties from " + configurationFileName); 
    
   StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor(); 
   encryptor.setPassword(password); 
   EncryptableProperties newProperties = new EncryptableProperties(encryptor); 
    
   InputStream inputStream = Util.class.getResourceAsStream(configurationFileName); 
    
   if(inputStream == null) { 
    inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(configurationFileName); 
   } 
    
   if(inputStream == null) { 
    logger.error("File not found: " + configurationFileName); 
   } else { 
    newProperties.load(inputStream); 
   } 
    
   if(properties == null) { 
    properties = new EncryptableProperties(encryptor); 
   } 
    
   properties.putAll(newProperties); 
    
  } catch (IOException e) { 
   throw new RuntimeException("Error initializing Utils. ", e); 
  } 
 } 
  
 /**
  * Returns the property value for the especified key. 
  * @param key Property key. 
  * @return Property value. 
  */ 
 public static String getProperty(String key) { 
  return properties.getProperty(key); 
 } 
  
 public static String getProperty(String key, String[] params) { 
  String value = properties.getProperty(key); 
   
  if(value != null && params != null) { 
   for(int i = 0; i < params.length; i++) { 
    value = value.replaceAll("\\{" + i + "\\}", params[i]); 
   } 
  } 
   
  return value; 
 } 
  
 /**
  * Returns the property value for the especified key. 
  * @param key Property key. 
  * @param defaultValue 
  * @return Property value if property key exists, defaultValue otherwise. 
  */ 
 public static String getProperty(String key, String defaultValue) { 
  return properties.getProperty(key, defaultValue); 
 } 
  
 /**
  * Sets a property value. 
  * @param key Property key. 
  * @param value New value. 
  */ 
 public static void setProperty(String key, String value) { 
  properties.setProperty(key, value); 
 } 
  

  
 /**
  * Generates a random String with specified length. 
  * @param length 
  * @return A random String. 
  */ 
 public static String generateRandomString(int length) { 
  String string = ""; 
        Random r = new Random( new java.util.GregorianCalendar().getTimeInMillis()); 
        int i = 0; 
 
        while (i < length) { 
 
            char c = (char) r.nextInt(255); 
 
            if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) { 
                string += c; 
                i++; 
            } 
        } 
 
        return string; 
 } 
  

  
 /**
  * Default date format. 
  * @return 
  */ 
 public static String getDateFormatPattern() { 
  return "yyyy-MM-dd"; 
 } 
  
 /**
  * Default date-time format. 
  * @return 
  */ 
 public static String getDateTimeFormatPattern() { 
  return "yyyy-MM-dd HH:mm"; 
 } 
  
 /**
  * Alternate date-time format. 
  * @return 
  */ 
 public static String getAlternateDateTimeFormatPattern() { 
  return "yyyy-MM-dd HH:mm:ss"; 
 } 
  
 /**
  * Max value for a date. 
  * @return 
  */ 


  


 /**
  * Returns a new String without accent simbols (ascii representation). 
  * @param str String to convert. 
  * @return a new String without accent simbols (ascii representation). 
  */ 
 public static String toAscii(String str) { 
  return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""); 
 } 
 
 /**
  *  
  * @param str 
  * @return MD5 Hash of the specified string. 
  */ 
 public static String getMD5Hash(String str) { 
  MessageDigest algorithm = null; 
   
  try { 
   algorithm = MessageDigest.getInstance("MD5"); 
    
  } catch (NoSuchAlgorithmException e) { 
   logger.error("Error getting MD5 Hash", e); 
  } 
   
  algorithm.reset(); 
  algorithm.update(str.getBytes(), 0, str.length()); 
  str = new BigInteger(1, algorithm.digest()).toString(16); 
   
  while (str.length() < 32) { 
   str = "0" + str; 
  } 
   
  return str; 
 } 
  
    public static void checkSignaure(String k, String fileName, String signature) { 
     String c = ""; 
      
  try { 
   InputStream is = Util.class.getResourceAsStream(fileName); 
    
   if(is == null) { 
    throw new RuntimeException("File not found: " + fileName); 
   } 
    
   DataInputStream dis = new DataInputStream(is); 
   BufferedReader br = new BufferedReader(new InputStreamReader(dis)); 
   String l; 
    
   while((l = br.readLine()) != null) { 
    if(!l.contains(signature)) { 
     c += l; 
    } 
   } 
    
   br.close(); 
   dis.close(); 
   is.close(); 
    
  } catch (FileNotFoundException e) { 
   throw new RuntimeException("Error checking signature: " + fileName + " not found."); 
  } catch (IOException e) { 
   throw new RuntimeException("Error checking signature."); 
  } 
   
  StandardPBEStringEncryptor e = new StandardPBEStringEncryptor(); 
  e.setPassword(k); 
   
  if(signature == null || signature.isEmpty() || !e.decrypt(signature).equals(c)) { 
   //logger.debug("\n" + e.encrypt(c) + "\n"); 
   throw new RuntimeException("Invalid signature."); 
  } 
    } 
 
 /**
  * Shows a terminal error. 
  * @param event 
  * @param application 
  */ 

  
 /**
  *  
  * @param type 
  * @return The simple name of the specified Class in lower case. 
  */ 
 public static String getLowerCaseTypeName(Class<?> type) { 
     String typeName = type.getSimpleName(); 
     return typeName.substring(0, 1).toLowerCase() + typeName.substring(1, typeName.length()); 
 } 
  

 /**
  *  
  * @param type 
  * @return Array with the properties to show in crud forms (from properties file). 
  */ 
    public static Object[] getVisibleFormProperties(Class<?> type) { 
     String visibleFieldsProp = getProperty("ui." + getLowerCaseTypeName(type) + ".form.visibleFields"); 
      
     if(visibleFieldsProp == null) { 
      return null; 
     } 
      
     return visibleFieldsProp.replace(" ", "").split(","); 
    } 
     
    /**
     *  
     * @param type 
     * @return Array with the properties to show in crud tables (from properties file). 
     */ 
    public static Object[] getVisibleTableProperties(Class<?> type) { 
     String visibleFieldsProp = getProperty("ui." + getLowerCaseTypeName(type) + ".table.visibleFields"); 
      
     if(visibleFieldsProp == null) { 
      return null; 
     } 
      
     return visibleFieldsProp.replace(" ", "").split(","); 
    } 
 
    /**
     * @return server log directory (CATALINA_HOME/logs in tomcat). 
     */ 
    public static String getServerLogsDirectory() { 
  return System.getProperty("catalina.base").replace('\\', '/') + "/logs/"; 
 } 
     
    /**
     * @return server directory (CATALINA_HOME in tomcat). 
     */ 
    public static String getServerDirectory() { 
  return System.getProperty("catalina.base").replace('\\', '/'); 
 } 
     
    /**
     * Replaces special characters (like á, é, í, etc.) with the corresponding html code. 
     * @param html html text to parse. 
     * @return parsed html. 
     */ 
    public static String replaceHtmlSpecialCharacters(String html) { 
     return StringUtils.replaceEach( 
      html, 
      new String[] {"á", "é", "í", "ó", "ú", "??", "??", "??", "??", "??", "¡"}, 
      new String[] {"á", "é", "í", "ó", "ú", "??", "??", "??", "??", "??", "¡"} 
     ); 
    } 
     
    /**
     * Returns the content of the given file. 
     * @throws IOException 
     */ 
 public static String readFile(String path) throws IOException { 
  InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path); 
 
  if (is == null) { 
   throw new FileNotFoundException("Can't find \"" + path + "\"."); 
  } 
 
  BufferedReader br = null; 
  StringBuilder sb = new StringBuilder(); 
 
  try { 
   String line; 
   br = new BufferedReader(new InputStreamReader(is)); 
    
   while ((line = br.readLine()) != null) { 
    sb.append(line + "\n"); 
   } 
 
  } finally { 
   if (br != null) { 
    try { 
     br.close(); 
    } catch (IOException e) { 
     throw new RuntimeException(e); 
    } 
   } 
  } 
 
  return sb.toString(); 
 } 
}