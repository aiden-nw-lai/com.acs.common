<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
 <%
        DateFormat fm_date = new SimpleDateFormat("yyyyMMdd");
        DateFormat fm_month = new SimpleDateFormat("YYYYMM");
        Calendar cal = Calendar.getInstance();
        String p_current_date= fm_date.format(cal.getTime());
        String p_current_month = fm_month.format(cal.getTime());
        
        
        
		String p_start_month = fm_date.format(cal.getTime());
        
        cal.add(Calendar.MONTH, -1);
        String p_prev_month= fm_date.format(cal.getTime());
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        String p_yesterday = fm_date.format(cal.getTime());
        String p_yesterday_yyyy_mm_dd = p_yesterday.substring(0, 4) + "-"  +p_yesterday.substring(4, 6) +  "-"  +p_yesterday.substring(6, 8);
%>
<html>
<head>
<title>Tree Menu - Make menus that expand when clicked</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta name="description" content="TreeMenu creates tree menus out of UL/LI tags that pop open when clicked.">
<meta name="author" content="Mack Pexton">
<link rel="stylesheet" href="acmebase.css" type="text/css">

<style type="text/css">
/* Menu container */
.menu	{
	width:300px;
	height:175px;
	border:solid #FF9900 1px;
	padding:10px 5px 10px 5px;
	margin:12px 12px 12px 50px;
	}

/* Menu styles */
.menu ul
	{
	margin:0px;
	padding:0px;
	text-decoration:none;
	}
.menu li
	{
	margin:0px 0px 0px 5px;
	padding:0px;
	list-style-type:none;
	text-align:left;
	font-family:Arial,Helvetica,sans-serif;
	font-size:13px;
	font-weight:normal;
	}

/* Submenu styles */
.menu ul ul 
	{
	background-color:#F6F6F6;
	}
.menu li li
	{
	margin:0px 0px 0px 16px;
	}

/* Symbol styles */
.menu .symbol-item,
.menu .symbol-open,
.menu .symbol-close
	{
	float:left;
	width:16px;
	height:1em;
	background-position:left center;
	background-repeat:no-repeat;
	}
.menu .symbol-item  { background-image:url(icons/page.png); }
.menu .symbol-close { background-image:url(icons/plus.png);}
.menu .symbol-open  { background-image:url(icons/minus.png); }
.menu .symbol-item.last  { }
.menu .symbol-close.last { }
.menu .symbol-open.last  { }

/* Menu line styles */
.menu li.item  { font-weight:normal; }
.menu li.close { font-weight:normal; }
.menu li.open  { font-weight:bold; }
.menu li.item.last  { }
.menu li.close.last { }
.menu li.open.last  { }

a.go:link, a.go:visited, a.go:active
        {
        display:block;
        height:26px;
        width:100px;
        background-color:#FFFFFF;
        color:#333333;
        font-family:Arial,Helvetica,sans-serif;
        font-size:12px;
        font-weight:bold;
        text-align:right;
        text-decoration:none;
        line-height:26px;
        padding-right:30px;
        background-image:url(go.gif);
        background-position:right;
        background-repeat:no-repeat;
        }
a.go:hover
        {
        text-decoration:none;
        color:#488400;
        }
#example3 { width:40%; background-color:#F9F9F9; padding:0px; margin-left:24px; }
#example3 li { list-style:none; margin:1px 0px; }
#example3 li a { display:block; height:16px; padding:0px 4px; background-color:#EEEEFF; }
#example3 li ul { margin:0px; padding:0px; }
#example3 li ul li a { background-color:#F9F9F9; border-bottom: solid #ECECEC 1px; padding-left:20px; }
.auto-style1 {
	margin-right: 0px;
}
</style>

<script src="TreeMenu.js" type="text/javascript"></script>
</head>

<body>
<a name="top"></a>
<div align="center"><div class="page">
<div class="content">

<!-- Header -->

<!-- Table of Contents -->
	<pre>

</pre>

	&nbsp;&nbsp;
</div>

</div></div>

<div class="menu" style="position: absolute; width: 276px; height: 929px; z-index: 2; left: -33px; top: 58px">
<ul id="report">
	<li class="auto-style1">Summary 	  <ul>
	  <li><a href="<%= request.getContextPath( ) + "/frameset?__report=summary.rptdesign&sample=my+parameter" %>" target="main" >
	  Summary</a></li>
	  <li><a href="<%= request.getContextPath( ) + "/frameset?__report=DailyStatistic_specificMonth.rptdesign&Month+For+Query+%28YYYYMM%29="%><%=p_current_month%>" target="main" >
	  Daily Transaction Summary(Month)</a></li>
	  </ul>
	  
	</li>
	<li>Statistic
	  <ul>
	  <li><a href="<%= request.getContextPath( ) + "/frameset?__report=Monthly_card_txtype_statistic.rptdesign&Reporting+month="%><%=p_current_month%>" target="main" >
	  Transaction Type with Chart</a></li>
	  <li><a href="<%= request.getContextPath( ) + "/frameset?__report=Txtype_statistic2.rptdesign&sample=my+parameter" %>" target="main" >
	  Transaction Type</a></li>
	   <li><a href="<%= request.getContextPath( ) + "/frameset?__report=cardStatistic.rptdesign&sample=my+parameter" %>" target="main" >
	   Specific Card</a></li>
	   <li><a href="<%= request.getContextPath( ) + "/frameset?__report=topup_statistic.rptdesign&st_month="%><%=p_current_month%>&ed_month=<%=p_current_month%>" target="main" >
	   Topup</a></li>
	  </ul>
	</li>

	<li>Card Related
	  <ul>
		  <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=card_txdetail.rptdesign&sample=my+parameter" %>" target="main" >
		  All tx Specific Card</a></li>
          <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=carddetail_specific_date_rpt2.rptdesign&sample=my+parameter" %>" target="main" >
		  Card (Date Range)</a></li>	
          <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=carddetail_specific_month_rpt.rptdesign&sample=my+parameter" %>" target="main" >
		  Card (Month)</a></li>		            	  
          <!--
			  <li><a href="http://www.w3.org/">World Wide Web Consortium</a>
				    <ul>
					    <li><a href="http://validator.w3.org/">The W3C Markup Validator</a></li>
					  
				    </ul>
			  </li>
			  -->
	  </ul>
	<li>Top up
	  <ul>
	  	  <li><a href="<%= request.getContextPath( ) + "/frameset?__report=topup_statistic.rptdesign&"%>st_month=<%=p_current_month%>&ed_month=<%=p_current_month%>" target="main" >
		  Topup</a></li>
  	  	  <li><a href="<%= request.getContextPath( ) + "/frameset?__report=TopupAnalyisi.rptdesign&"%>st_Date=<%=p_current_date%>&ed_Date=<%=p_current_date%>" target="main" >
		  Topup Detail(Date Range)</a></li>
	  </ul>
	</li>
<strong></strong>	  
	</li>
<li>Cashier Card and SAM Card Related
	  <ul>
	  	  <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=samDetail_bytxdate.rptdesign" %>" target="main" >
		 SAM Details (Enqury)</a>
		  </li>
		  <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=Cashier_Card_Statistic.rptdesign&sample=my+parameter" %>" target="main" >
		  Cashier Card Details</a></li>
         <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=VALIDATION13_INSCONSISTENCY_SAM.rptdesign&ST+CTC=1" %>" target="main" >
		  Inconsistency SAM CTC </a></li>
         <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=Duplicate_SAMCTC_Summary.rptdesign&Starting_SAMCTC=0&sortby=1" %>" target="main" >
		  SAM CTC Duplication</a></li>
        
	  </ul>
	</li>
	
    <li>Validation
	  <ul>
		  <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=VALIDATION1.rptdesign&sample=my+parameter" %>" target="main" >
		  Card Summary for Validation</a></li>
          <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=VALIDATION2.rptdesign&"%>st_date=<%=p_current_date%>&ed_date=<%=p_current_date%>" target="main" >
		  Discrepancy Report 1 <br>(in-consistency only)</a></li>	
          <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=VALIDATION33.rptdesign&"%>st_date=<%=p_current_date%>&ed_date=<%=p_current_date%>" target="main" >
		  Discrepancy Report 3</a></li>		            	  
			 <li>Validation by Card
			 <ul>

				 
					 <li>
					 
					 <a href="<%= request.getContextPath( ) + "/frameset?__report=VALIDATION51.rptdesign&sample=my+parameter" %>" target="main" >
					 Card Up-to-Date</a></li>		            	  
					 <li>
					 <a href="<%= request.getContextPath( ) + "/frameset?__report=VALIDATION52.rptdesign&sample=my+parameter" %>" target="main" >
					 Specific Card Monthly</a></li>
					 <li>
					 <a href="<%= request.getContextPath( ) + "/frameset?__report=VALIDATION53.rptdesign&" %>ST_DATE=<%=p_current_date%>&ED_DATE=<%=p_current_date%>" target="main" >
					 Card Trasnsaction on Specific Date Range</a></li>	                  	                  
					 			 <li>
								 <a href="<%= request.getContextPath( ) + "/frameset?__report=VALIDATION6.rptdesign&sample=my+parameter" %>" target="main" >
								 All Cards with Opening/closing</a></li>	
					 			  <li>
								  <a href="<%= request.getContextPath( ) + "/frameset?__report=VALIDATION61.rptdesign&sample=my+parameter" %>" target="main" >
								  All Cards with Opening/Closing with summation</a></li>	                  	                                    	                  
		      </ul>
			  <li>Duplication CTC 
				    <ul>
						 <li>
						 <a href="<%= request.getContextPath( ) + "/frameset?__report=DUPLICATION_CTC_SUMMARY.rptdesign&sample=my+parameter" %>" target="main" >
						 Duplication CTC Summary</a></li>	                  	                                    	                  
						 <!--li>
						 <a href="<%= request.getContextPath( ) + "/frameset?__report=DUPLICATION_CTC.rptdesign&Starting+Date="%><%= p_prev_month%>&Ending+Date=<%= p_start_month%>&Duplication+count=2" target="main" >
						 Duplication CTC Detail</a></li-->	                  	                                    	                  						 
					  
				    </ul>
			  </li>
			  &nbsp;</ul>
	</li>
   <li>OD Report
	  <ul>
		  <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=OD_Report.rptdesign&sample=my+parameter" %>" target="main" >
		  OD Report 1</a></li>
          <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=OD_Report2.rptdesign&sample=my+parameter" %>" target="main" >
		  OD Report 2</a></li>	
	
	  </ul>
	</li>

   <li>Database Statistic
	  <ul>
		
		  <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=database_statistic_existingtask.rptdesign&Sorting=1" %>" target="main" >
		  Existing Task</a></li>

		  <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=database_statistic_deadlock.rptdesign&Sorting=1" %>" target="main" >
		  Deadlock </a></li>
          
          <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=database_statistic.rptdesign&Sorting=1" %>" target="main" >
		  Table Realted</a></li>
          
          <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=database_statistic_dbsize1.rptdesign&sample=my+parameter" %>" target="main" >
		  Db Size</a></li>

          <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=database_statistic_index.rptdesign&sample=my+parameter" %>" target="main" >
		  index information</a></li>
          <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=database_statistic_fragementation.rptdesign&sample=my+parameter" %>" target="main" >
		  Fragmentation Analysis</a></li>
	  </ul>
	</li>
   <li>Daily/ Exception
	  <ul>
		  <li>
		       <a href="<%= request.getContextPath( ) + "/frameset?__report=DailyExceptionRpt.rptdesign&UPLOAD_DATE=" + p_yesterday %>" target="main" >
                                Daily Exception Report</a></li>
         <li>
                  <a href="<%= request.getContextPath( ) + "/frameset?__report=DailyUploadStatistic.rptdesign&st_upload_date=" + p_yesterday + "&ed_uploaded_date=" + p_yesterday %>" target="main" >
                        Daily Terminal Upload Statistic</a></li>
  <li>
                  <a href="<%= request.getContextPath( ) + "/frameset?__report=DailyUploadStatistic1.rptdesign&st_upload_date=" + p_yesterday + "&ed_uploaded_date=" + p_yesterday %>" target="main" >
                        Daily Terminal Upload Statistic(TX TYpe) <BR> Matrix</a></li>          
         <li>
                  <a href="<%= request.getContextPath( ) + "/frameset?__report=DailyUploadStatistic_specific_tx_sam.rptdesign?st_upload_date=" + p_yesterday + "&ed_uploaded_date=" + p_yesterday %>" target="main" >
                        Daily Terminal Upload Statisitc by Specific SAMCAN</a></li>    
             
          <li>
                    <a href="<%= request.getContextPath( ) + "/frameset?__report=DailyTerminalUploadSummary.rptdesign&MONTH=" + p_current_month %>&SORTING=1" target="main" >
                  Monthly Terminal upload Summary</a></li>
          <li>
                    <a href="<%= request.getContextPath( ) + "/frameset?__report=DailyTerminalUploadSummary_all.rptdesign&MONTH=" + p_current_month %>&SORTING=1" target="main" >
                  Monthly Terminal upload Summary(ALL)</a></li>       
          <li>
                    <a href="<%= request.getContextPath( ) + "/frameset?__report=TerminalDetail_bytxdate.rptdesign&,ST_DATE=" + p_current_date + "&ED_DATE=" + p_current_date + "&SORTING=1"%>" target="main" >
                  Terminal Upload Transaction Details on specific Date</a></li>     
	  </ul>
	</li>
	
   <li>Transaction Type Statistic
	  <ul>
		  <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=Txtype_statistic2.rptdesign&sample=my+parameter" %>" target="main" >
		  Transaction Type</a></li>
          <li>
		  <a href="<%= request.getContextPath( ) + "/frameset?__report=Txtype_statistic_bymonth.rptdesign&sample=my+parameter" %>" target="main" >
		  Transaction Type by Month</a></li>
	  </ul>
	</li>
    <li>Operational Report
	  <ul>
    		  <li>
		       <a href="<%= request.getContextPath( ) + "/frameset?__report=RouteStatistic.rptdesign&st_date=" + p_yesterday_yyyy_mm_dd + "&Details=Y" %>" target="main" >
                                Daily Exception Report</a></li>          
		 
          
          
	  </ul>
	</li>

	
</ul>
<script type="text/javascript">make_tree_menu('report');</script>
</div>

<div id="button_layer" style="position: absolute; width: 213px; height: 29px; z-index: 1; left: 16px; top: 26px">
<button onclick="TreeMenu.show_all(document.getElementById('report'))">Show All</button>
<button onclick="TreeMenu.hide_all(document.getElementById('report'))">Hide All</button>
<button onclick="TreeMenu.reset(document.getElementById('report'));location.reload();">
Reset</button></div>
</body>
</html>
</html>
