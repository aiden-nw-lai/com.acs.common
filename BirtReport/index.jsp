<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
  <head>
    <meta  content="text/html; charset=iso-8859-1"  http-equiv="content-type">
    <title>Tap to Pay (Verification Tools)</title>
    <link  href="styles/iv/index.css"  type="text/css"  rel="stylesheet">
    <link  href="http://www.eclipse.org/images/eclipse.ico"  type="image/x-icon"
       rel="shortcut icon">
    <style>
.warningMessage {  
  color: red;
}

#footer_1 {  
  font-size: x-small;  
  font-family: Arial, Helvetica, sans-serif;
}

</style> </head>
  <body  id="footer_1"><img  longdesc="http://www.taptopay.com/"  alt="Logo"  src="webcontent/birt/images/logo-taptopay.png"><br>
    <br>
    <table  style="width: 781px; height: 68px;"  border="1"  cellpadding="0"  cellspacing="0">
      <thead>
        <tr>
          <th  style="width: 100%; background-color: rgba(51, 102, 255, 0.5);"><br>
          </th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td><a  target="_self"  href="%3C%=%20request.getContextPath%28%20%29%20+%20%22/frameset?__report=test.rptdesign&amp;sample=my+parameter%22%%3E"
               frameset?__report="summary.rptdesign&amp;sample=my+parameter&quot;"
               %="">Summary Report</a></td>
        </tr>
        <tr>
          <td><br>
          </td>
        </tr>
      </tbody>
    </table>
    <br>
    <br>
    &lt;% String javaVersion = System.getProperty("java.version"); String
    viewerVersion = "4.5.0"; String engineVersion = "4.5.0"; %&gt;
    <!-- Page banner -->
    <!--
		<TABLE class=banner-area cellSpacing=0 cellPadding=0 width="100%" border=0>			<TBODY>				<TR>					<TD width=115><a href="http://www.eclipse.org/">
						<IMG src="webcontent/birt/images/EclipseBannerPic.jpg" alt="Eclipse Logo" width="115" height="50" border=0></a>					</TD>					<TD>						<IMG src="webcontent/birt/images/gradient.jpg" alt="gradient banner" width="300" height="50" border=0>
					</TD>					<TD vAlign=center align=right width=250>						<a class="birt" href="http://www.eclipse.org/birt">							<!-- Temporary BIRT header -->
    <!--
							<SPAN style="PADDING-RIGHT: 10px; FONT-WEIGHT: bold; FONT-SIZE: 20px; COLOR: #e8e8ff; FONT-FAMILY: arial, sans-serif">								BIRT							</SPAN>						</a> 
					</TD>				</TR>			</TBODY>		</TABLE>
		-->
    <!-- Table with menu & content -->
    <table  cols="2"  border="0"  cellpadding="0"  cellspacing="0"  width="100%">
      <tbody>
        <tr>
          <td  class="content"  style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px">
            <!-- Page title -->
            <!-- Content area -->
            <p><a  href="%3C%=%20request.getContextPath%28%20%29%20+%20"  frameset?__report="VALIDATION1.rptdesign&amp;sample=my+parameter&quot;"
                 %="">" tARGET="_BLANK"&gt;Validation Report</a> </p>
            <p><a  href="%3C%=%20request.getContextPath%28%20%29%20+%20"  frameset?__report="summary_chart.rptdesign&amp;sample=my+parameter&quot;"
                 %="">" tARGET="_BLANK"&gt;Last two month statistic</a> </p>
            <p><a  href="%3C%=%20request.getContextPath%28%20%29%20+%20"  frameset?__report="summary.rptdesign&amp;sample=my+parameter&quot;"
                 %="">" tARGET="_BLANK"&gt;Summary Report</a> </p>
            <p><a  href="%3C%=%20request.getContextPath%28%20%29%20+%20"  frameset?__report="DailyCardCan_statistic.rptdesign&amp;sample=my+parameter&quot;"
                 %="">" tARGET="_BLANK"&gt;Daily Card Statistic </a> </p>
            <p><a  href="%3C%=%20request.getContextPath%28%20%29%20+%20"  frameset?__report="DailyTreminal_statistic.rptdesign&amp;sample=my+parameter&quot;"
                 %="">" tARGET="_BLANK"&gt;Daily Terminal Statistic </a> </p>
            <p><a  href="%3C%=%20request.getContextPath%28%20%29%20+%20"  frameset?__report="carddetail_rpt.rptdesign&amp;sample=my+parameter&quot;"
                 %="">" tARGET="_BLANK"&gt;Card Details</a> </p>
            <p><a  href="%3C%=%20request.getContextPath%28%20%29%20+%20"  frameset?__report="Monthly_card_txtype_statistic.rptdesign&amp;sample=my+parameter&quot;"
                 %="">" tARGET="_BLANK"&gt;Monthly Transaction TYpe Statistic</a>
            </p>
            <p><a  href="%3C%=%20request.getContextPath%28%20%29%20+%20"  frameset?__report="TopupAnalyisi.rptdesign&amp;sample=my+parameter&quot;"
                 %="">" tARGET="_BLANK"&gt;Top up Statistic</a> </p>
            <p><a  href="%3C%=%20request.getContextPath%28%20%29%20+%20"  frameset?__report="samdetail_specific_date_rpt1.rptdesign&amp;sample=my+parameter&quot;"
                 %="">" tARGET="_BLANK"&gt;SAM statistic</a> </p>
          </td>
        </tr>
      </tbody>
    </table>
    <br>
    Viewer Version : &lt;%= viewerVersion %&gt;<br>
    <br>
    Engine Version: &lt;%= engineVersion %&gt;<br>
    &lt;% String javaVersionMessage = javaVersion; // check Java version
    String[] versionParts = javaVersion.split("\\."); int majorVersion = 0; int
    minorVersion = 0; try { majorVersion = Integer.parseInt(versionParts[0]);
    minorVersion = Integer.parseInt(versionParts[1]); if ( majorVersion &lt; 1
    || ( majorVersion == 1 &amp;&amp; minorVersion &lt; 5 ) ) {
    javaVersionMessage = "<span  class="\&quot;warningMessage\&quot;">" +
      javaVersion + " (WARNING: BIRT " + viewerVersion + " only supports JRE
      versions &gt;= 1.5)</span>"; } } catch (NumberFormatException e) { } %&gt;
    <br>
    JRE version: &lt;%= javaVersionMessage %&gt;<br>
  </body>
</html>
