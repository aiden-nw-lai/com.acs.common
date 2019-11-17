package acs.Tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;


public class Parameter {
	private final static Logger logger = Logger.getLogger(Parameter.class.getName());
	
	Date _process_date = null;
	
	
	public Date get_process_date() {
		return _process_date;
	}


	public void set_process_date(Date _process_date) {
		this._process_date = _process_date;
	}

	
	public Parameter(){
		_process_date = Calendar.getInstance().getTime();
		
	}

	public String convert_parameter(String _parameter){
		logger.debug("----:" + _parameter);
		String return_parameter =StringEscapeUtils.unescapeHtml(_parameter);
		
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		
		       params = new HashMap<String, List<String>>();
		            for (String param : return_parameter.split("\\[")) {
		                String[] pair = param.split("\\]");
		                String key =  pair[0]; //URLDecoder.decode(pair[0], "UTF-8");
		                String value =convert_value(key);
		        
		                if ((!(key.equals(value))) && (value != null)){
		                	String _replace_key = key;
		                	
		                	if (key.indexOf("$") >=0){
		                	    _replace_key =  _replace_key.replaceAll("\\$", "\\\\\\$");
		                	} 
		                	if (key.indexOf("+") >=0){
		                		_replace_key =  _replace_key.replaceAll("\\+", "\\\\\\+");
		                	}
		                	
		                	return_parameter = return_parameter.replaceAll(_replace_key, value);
		                	
		                }
		            }
		            if (return_parameter.contains("[")){
		            	return_parameter = return_parameter.replaceAll("\\[", "");
		            }
		            if (return_parameter.contains("]")){
		            	return_parameter = return_parameter.replaceAll("\\]", "");
		            }

		            
	//	logger.debug(return_parameter);
		
		
		
		//return_parameter = "?Start_Date=01/18/2016 00:00:00&ED_DATE=01/18/2016 23:59:59";
		
		return return_parameter;
	}

	
	public  String convert_value(String key){
		//logger.debug("key:" + key);
		String _return_value =key;
		String[] element = key.split("\\$");
		
		String _operator = "";
		String _value = "";
		if (element.length >= 2){
			_value="";

			if (element.length == 3){
				
				String[] _formula= element[2].trim().split("(\\s+|(?=\\+)|(?<=\\-))");
				
				_operator = _formula[0];
				if (_formula.length >= 2){
					_value = _formula[1];
				}
			}
			_return_value = get_value(element[1].toUpperCase(), _operator, _value);
			
			     /*
				if (!(element[i].trim().equals(""))){
				 	String[] data = element[i].split("\\+|\\-");
					
					
					if (data.length==1){
						_value = _value + get_value(data[0]);
						logger.debug("cal value:" + _value);
					}
					if (data.length > 2)
					
					
				}
				*/
			//}
		}		

		
		return _return_value ;
	}
	public  String get_value(String _value1, String _operator, String _value2){
		String _result = null;
	
		if (_value1.indexOf("DATE_ST") >= 0) {
			_result = new SimpleDateFormat("dd/MM/YYYY 00:00:00").format(_process_date);
		} else if ((_value1.indexOf("DATE_ED") >= 0)) {
			_result = new SimpleDateFormat("dd/MM/YYYY 23:59:59").format(_process_date);
		} else if ((_value1.indexOf("DATE") >=0)){
				//_result = new SimpleDateFormat("dd/MM/YYYY").format(Calendar.getInstance().getTime());
				_result = new SimpleDateFormat("dd/MM/YYYY").format(_process_date);
		} else if (_value1.equals("MONTH")) {
				_result = new SimpleDateFormat("MM/YYYY").format(_process_date);
		} else if (_value1.equals("MONTH_ST")) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			_result = new SimpleDateFormat("01/MM/YYYY 00:00:00").format(c.getTime());	
		} else if (_value1.equals("MONTH_ED")) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			_result = new SimpleDateFormat("dd/MM/YYYY 23:59:59").format(c.getTime());				
		} else if (_value1.equals("WEEK_ST")) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			_result = new SimpleDateFormat("dd/MM/YYYY 00:00:00").format(c.getTime());
			
			
		} else if (_value1.equals("WEEK_ED")) {
			Calendar c = Calendar.getInstance();
			// Set the calendar to monday of the current week
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			_result = new SimpleDateFormat("dd/MM/YYYY 23:59:59").format(c.getTime());		
		} else if (_value1.equals("YEAR")) {
				_result = new SimpleDateFormat("YYYY").format(_process_date);
		} else if (_value1.indexOf("YEAR_ST")>=0) {
			_result = new SimpleDateFormat("01/01/YYYY 00:00:00").format(_process_date);
		} else if (_value1.indexOf("YEAR_ED")>=0) {
			_result = new SimpleDateFormat("31/12/YYYY 23:59:59").format(_process_date);
	
		}
// General File of Date Parameter
// [$DATE$] -> yyyy-MM-dd
// [$DATE@INT$] ->yyyyMMdd
// [$DATE@MMMM$] ->"dd/MMM/yyyy
// [$DATE@DTYYYYMMDDHISS$] ->yyyyMMddHHmmss
// [$DATE@DTYYYYMMDD$] ->yyyy-MM-dd
// [$WEEK_ST$-1] -> FIRST DAY OF LAST WEEK (SUNDAY)
// [$WEEK_ED$-1] -> LAST DAY OF LAST WEEK (SATURDAY)
		
		
		try {	
			//if ((_value1.indexOf("DATE") >= 0) && (_operator.trim().length() != 0)){
			if (_value1.indexOf("DATE") >= 0){// && (_operator.trim().length() != 0)){
				Calendar cal = Calendar.getInstance();
				DateFormat df2 = null;
				DateFormat result_format= null;
				if (_value1.indexOf("DATE_") >=0){
					df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				} else {
					df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					_result = _result + " 00:00:00";
				}
//				logger.debug("value1:" + _value1 + "---" + _value1.indexOf("@INT"));
				if (_value1.indexOf("@INT") >=0){
					result_format = new SimpleDateFormat("yyyyMMdd");
				} else if (_value1.indexOf("@MMMM") >=0){
					result_format = new SimpleDateFormat("dd/MMM/yyyy");
				} else if (_value1.indexOf("@DTYYYYMMDDHISS") >=0){
						result_format = new SimpleDateFormat("yyyyMMddHHmmss");	
					//	logger.debug("-0-------");					
				} else if (_value1.indexOf("@DTYYYYMMDD") >=0){
					result_format = new SimpleDateFormat("yyyy-MM-dd");
		     	} else {
		     		result_format = new SimpleDateFormat("yyyy-MM-dd");
		     	}
				int _cal_day = 0;
				cal.setTime(df2.parse(_result));
			//	logger.debug("result_format:" + result_format);
				
				if (_operator.trim().length() != 0){
				//	logger.debug("Parameter handler rESULT 1");
					_cal_day = Integer.parseInt(_operator.trim() + _value2.trim());
					cal.add(Calendar.DATE, _cal_day);
					
				}
			//	logger.debug("Parameter handler rESULT 3");
				_result = result_format.format(cal.getTime());
				//logger.debug("Parameter handler rESULT :" + _result);
				
			} else if ((_value1.indexOf("WEEK") >= 0) && (_operator.trim().length() != 0)){
				Calendar cal = Calendar.getInstance();
				DateFormat result_format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				cal.setTime(result_format.parse(_result));
				int _cal_day = 0;
				_cal_day = Integer.parseInt(_operator.trim() + _value2.trim());
				
				cal.add(Calendar.DATE, _cal_day * 7);
				_result = result_format.format(cal.getTime());
			} else if ((_value1.indexOf("MONTH") >= 0) && (_operator.trim().length() != 0)){
				
				Calendar cal = Calendar.getInstance();
				DateFormat df2 = null;
				DateFormat result_format = null;
				if (_value1.equals("MONTH")){
					df2 = new SimpleDateFormat("01/MM/yyyy");
					result_format = new SimpleDateFormat("MM/yyyy");
					_result = "01/" + _result;
				} else {
					df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					result_format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				}
				cal.setTime(df2.parse(_result));
				int _cal_day = 0;
				_cal_day = Integer.parseInt(_operator.trim() + _value2.trim());
				
				cal.add(Calendar.MONTH, _cal_day );
				_result = result_format.format(cal.getTime());
			} else if ((_value1.indexOf("YEAR") >= 0) && (_operator.trim().length() != 0)){
				Calendar cal = Calendar.getInstance();
				DateFormat df2 = null;
				DateFormat result_format = null;
				if (_value1.equals("YEAR")){
					df2 = new SimpleDateFormat("01/01/yyyy");
					result_format = new SimpleDateFormat("yyyy");
					_result = "01/01/" + _result;
				} else {
					df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					result_format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				}
				logger.debug("result: " + _result);
	//	logger.debug("Result:" + df2.parse(_result));
				cal.setTime(df2.parse(_result));
				int _cal_day = 0;
				_cal_day = Integer.parseInt(_operator.trim() + _value2.trim());
				
				cal.add(Calendar.YEAR, _cal_day );
				_result = result_format.format(cal.getTime());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			
			if (logger.isInfoEnabled()){
				logger.debug(e.getMessage());
			}
			e.printStackTrace();
		}
//logger.debug("result:" + _result);
		return _result;
	}
	
	public String getcurrentdate(){
		//logger.debug(new SimpleDateFormat("dd/MM/YYYY").format(Calendar.getInstance().getTime()));
		return new SimpleDateFormat("dd/MM/YYYY").format(Calendar.getInstance().getTime());
	}
	
	
	public static void main(String[] args) throws SchedulerException{
		
	    Parameter param = new Parameter();
	    logger.debug(param.convert_parameter("c:\\OUTPUT\\AF\\DAILY_PARALLEL\\20180806\\BM_TXN_RPT_20180807($SEQ#).csv"));
		//logger.debug(param.convert_parameter("?st_date=[$DATE_ST@INT$-60]&ed_date=[$DATE_ED@INT$-60]"));
		//logger.debug(param.convert_parameter("Card Validation with Problem on  [$DATE@MMMM$-90]"));
		//logger.debug(param.convert_parameter("Attached pls find Validation Card with Problem( 3 month before) [$DATE@MMMM$-90]"));
		//logger.debug(param.convert_parameter("YYYYMMDD [$DATE_ST@DTYYYYMMDDHISS$-120]"));
		//logger.debug(param.convert_parameter("YYYYMMDD [$DATE_ED@DTYYYYMMDDHISS$-120]"));
		
    }
}
