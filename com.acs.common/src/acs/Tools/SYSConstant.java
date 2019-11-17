package acs.Tools;

public class SYSConstant {
	static String  _IPNOTFOUND = "UNKNOW";
	static String _SYSTEMUSER = "SYSTEM";
	static String _STATUS_START_SEND = "START_SEND";
	static String _STATUS_NEW = "NEW";
	static String _STATUS_WAITFOR_RESEND = "RESEND";
	static String _STATUS_WAITFOR_EMAIL = "EMAIL";
	static String _STATUS_WAITFOR_DELIVERY= "EMAIL";
	static String _STATUS_REPORT_IN_PROGRESS = "REPORT_IN_PROGRESS";
	static String _STATUS_EXPORT_IN_PROGRESS = "EXPORT_IN_PROGRESS";
	
	static String _STATUS_IMPORT_START = "IMPORT_START";
	static String _STATUS_EXPORT_START = "EXPORT_START";
	static String _STATUS_REPORT_START = "REPORT_START";
	
	static String _STATUS_JAVACORNEXECUTE_IN_PROGRESS = "JAVACORN_IN_PROGRESS";
	static String _STATUS_BATCHEXECUTE_IN_PROGRESS = "BATCH_IN_PROGRESS";
	static String _STATUS_IMPORT_IN_PROGRESS = "IMPORT_IN_PROGRESS";
	static String _STATUS_IMPORT_IN_DOWNLOADING = "Downloading";
	static String _STATUS_SENDING_EMAIL = "EMAIL_INPROGRESS";
	static String _STATUS_COMPLETE_EMAIL = "SENT";
	static String _STATUS_FAIL_EMAIL = "FAIL_SENT";
	static String _STATUS_REPORT_WAITFORNEXT = "WAITING";
	static String _STATUS_WAITING= "WAITING";
	static  String _STATUS_REPORT_COMPLETE= "COMPLETED";
	static  String _STATUS_EXPORT_COMPLETE= "EXPORT COMPLETED";
	static  String _STATUS_IMPORT_COMPLETE= "IMPORT COMPLETED";
	static  String _STATUS_COMPLETE= "COMPLETED";
	static  String _STATUS_EXPORT_SENDNG= "SENDING(EXPORT)";
	
	static int NOTIFICATION_EMAIL_GROUP = 1;
	
		
	static String _STATUS_ONE_TIME_SCHEDULE = "ONE-TIME";
	
	static String _NATURE_JAVACORNEXECUTE= "JAVA_JOB";
	static String _NATURE_BATCHEXECUTE= "BATCH_EXECUTE";
	static String _NATURE_DBEXPORT= "DBEXPORT";
	static String _NATURE_DBIMPORT= "DBIMPORT";
	static String _NATURE_REPORT= "REPORT";
	static String _STATUS_SUBTASK_STATUS = "SUBTASK_WAITING";
	static Long _NONEXT_SCHEDULE_TIME = (long) 999999;
	
	
	static String _STATUS_EXPORT_FAIL = "Export Fail";
	static String _STATUS_IMPORT_FAIL = "Import Fail";
	static String _STATUS_REPORT_FAIL = "Report Fail";

	
	




	
	public static String get_STATUS_IMPORT_START() {
		return _STATUS_IMPORT_START;
	}

	public static void set_STATUS_IMPORT_START(String _STATUS_IMPORT_START) {
		SYSConstant._STATUS_IMPORT_START = _STATUS_IMPORT_START;
	}

	public static String get_STATUS_EXPORT_START() {
		return _STATUS_EXPORT_START;
	}

	public static void set_STATUS_EXPORT_START(String _STATUS_EXPORT_START) {
		SYSConstant._STATUS_EXPORT_START = _STATUS_EXPORT_START;
	}

	public static String get_STATUS_REPORT_START() {
		return _STATUS_REPORT_START;
	}

	public static void set_STATUS_REPORT_START(String _STATUS_REPORT_START) {
		SYSConstant._STATUS_REPORT_START = _STATUS_REPORT_START;
	}

	public static void set_STATUS_IMPORT_IN_DOWNLOADING(String _STATUS_IMPORT_IN_DOWNLOADING) {
		SYSConstant._STATUS_IMPORT_IN_DOWNLOADING = _STATUS_IMPORT_IN_DOWNLOADING;
	}

	public static String get_STATUS_IMPORT_COMPLETE() {
		return _STATUS_IMPORT_COMPLETE;
	}

	public static void set_STATUS_IMPORT_COMPLETE(String _STATUS_IMPORT_COMPLETE) {
		SYSConstant._STATUS_IMPORT_COMPLETE = _STATUS_IMPORT_COMPLETE;
	}

	public static String get_STATUS_EXPORT_FAIL() {
		return _STATUS_EXPORT_FAIL;
	}

	public static void set_STATUS_EXPORT_FAIL(String _STATUS_EXPORT_FAIL) {
		SYSConstant._STATUS_EXPORT_FAIL = _STATUS_EXPORT_FAIL;
	}

	public static String get_STATUS_IMPORT_FAIL() {
		return _STATUS_IMPORT_FAIL;
	}

	public static void set_STATUS_IMPORT_FAIL(String _STATUS_IMPORT_FAIL) {
		SYSConstant._STATUS_IMPORT_FAIL = _STATUS_IMPORT_FAIL;
	}

	public static String get_STATUS_REPORT_FAIL() {
		return _STATUS_REPORT_FAIL;
	}

	public static void set_STATUS_REPORT_FAIL(String _STATUS_REPORT_FAIL) {
		SYSConstant._STATUS_REPORT_FAIL = _STATUS_REPORT_FAIL;
	}

	public static int getNOTIFICATION_EMAIL_GROUP() {
		return NOTIFICATION_EMAIL_GROUP;
	}

	public static void setNOTIFICATION_EMAIL_GROUP(int nOTIFICATION_EMAIL_GROUP) {
		NOTIFICATION_EMAIL_GROUP = nOTIFICATION_EMAIL_GROUP;
	}

	public static String get_Export_Sending_Status(){
		return _STATUS_EXPORT_SENDNG;
	}

	public static String get_Export_Nature(){
		return _NATURE_DBEXPORT;
	}
	public static String get_JavaCornExecute_Nature(){
		return _NATURE_JAVACORNEXECUTE;
	}
	public static String get_BatchExecute_Nature(){
		return _NATURE_BATCHEXECUTE;
	}
	
	public static String get_Report_Nature(){
		return _NATURE_REPORT;
	}
	public static String get_Import_Nature(){
		return _NATURE_DBIMPORT;
	}
	public static String get_ServerNotFound(){
			
		return _IPNOTFOUND;
	}
	public static String get_SystemUser(){
		
		return _SYSTEMUSER;
	}
	
	public static String get_StartSend_Status(){
		return _STATUS_START_SEND;
	}
	
	public static String get_EmailSending_Status(){
		return _STATUS_SENDING_EMAIL;
	}
	
	public static String get_WaitForEmail_Status(){
		return _STATUS_WAITFOR_EMAIL;
	}
	public static String get_new_Status(){
		return _STATUS_NEW;
	}
	public static String get_WaitForResend_Status(){
		return _STATUS_WAITFOR_RESEND;
	}
	
	
	public static String get_WaitForDelivery_Status(){
		return _STATUS_WAITFOR_DELIVERY;
	}
	
	public static String get_EmailComplete_Status(){
		return _STATUS_COMPLETE_EMAIL;
	}
	
	public static String get_EmailFail_Status(){
		return _STATUS_FAIL_EMAIL;
	}
	public static String get_WaitFor_Status(){
		return _STATUS_WAITING;
	}
	public static String get_ReportWaitFor_Status(){
		return _STATUS_REPORT_WAITFORNEXT;
	}
	public static String get_ReportComplete_Status(){
		return _STATUS_REPORT_COMPLETE;
	}
	public static String get_ExportComplete_Status(){
		return _STATUS_EXPORT_COMPLETE;
	}
	public static String get_Complete_Status(){
		return _STATUS_COMPLETE;
	}

	public static String get_ReportInProgress_Status(){
		return _STATUS_REPORT_IN_PROGRESS;
	}
	
	public static String get_ExportInProgress_Status(){
		return _STATUS_EXPORT_IN_PROGRESS;
	}
	public static String get_ImportInProgress_Status(){
		return _STATUS_IMPORT_IN_PROGRESS;
	}
	
	public static String get_batchInProgress_Status(){
		return _STATUS_BATCHEXECUTE_IN_PROGRESS;
	}
	
	public static String get_javacornInProgress_Status(){
		return _STATUS_JAVACORNEXECUTE_IN_PROGRESS ;
	}
	
	
	public static String get_Schedule_ONETIME_Type(){
		return _STATUS_ONE_TIME_SCHEDULE;
	}

	public static long  get_Onetime_NextTime(){
		return _NONEXT_SCHEDULE_TIME;
	}

	public static String get_STATUS_IMPORT_IN_DOWNLOADING() {
		return _STATUS_IMPORT_IN_DOWNLOADING;
	}
	
	
	
	
}
