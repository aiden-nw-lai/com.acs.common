package acs.Tools;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class DateExtracterAndParser {
    private final static Logger logger = Logger.getLogger(DateExtracterAndParser.class
            .getName());
		
		// This is the List of all date formats that we want to parse.
		// Here, We can also add our own format for parsing purpose.

	@SuppressWarnings("serial")
	static List<SimpleDateFormat> dateFormatsLst = new ArrayList<SimpleDateFormat>() {
			{
				 		add(new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.US));
						add(new SimpleDateFormat("dd-MMM-yyyy"));
						add(new SimpleDateFormat("dd.M.yyyy"));
			            add(new SimpleDateFormat("M/dd/yyyy"));
			            add(new SimpleDateFormat("dd.M.yyyy hh:mm:ss a"));
			            add(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a"));
			            add(new SimpleDateFormat("M/dd/yyyy hh:mm:ss a"));
			            add(new SimpleDateFormat("dd.MMM.yyyy"));
			            add(new SimpleDateFormat("dd-MMM-yyyy"));
			            
			}
	};

	/**
	* Convert String with various formats into java.util.Date
	*
	* @param strInput
	*            Date as a string
	* @return java.util.Date object if strInput string which is data can be
	*         parsed successfully otherwise it will returns null
	*/
		public static Date dateConversion(String strInput) {
			
			Date date = null;
			if (null == strInput) {
			      return null;
			}
			
			if (strInput.equals("NOW()")){
				DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
				date = new Date();
				strInput = dateFormat.format(date); 
				//System.out.println(strInput);
			} else {
				
			            for (SimpleDateFormat format : dateFormatsLst) {
	                        try {
	                        //	System.out.println(format.toPattern());
//	                              format.setLenient(false);
	                              date = format.parse(strInput);
	                              break;
	                        } catch (ParseException e) {
	                            /*  logger.info(strInput 
	                            		  + " is not matched with the Expected format of "
	                                      + format.toPattern());*/
	                        }
	                       
			            }
			}
			return date;
		}
		
		public static boolean isValidDate(String dateString) {
			boolean valid = false;
		   // SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		    System.out.println(dateString);
		    for (SimpleDateFormat format : dateFormatsLst) {
		    	//System.out.println(format.toString());
		    	
		    	System.out.println(format.toPattern());
		    	try {
                    format.setLenient(false);
                    Date _date = format.parse(dateString);
                  
                    valid = true;
              } catch (ParseException e) {
                   System.out.println(e.getMessage());
              }
		    }
		    return valid;
		}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
