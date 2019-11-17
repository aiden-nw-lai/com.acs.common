package acs.Tools;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPHTTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.io.CopyStreamEvent;
import org.apache.commons.net.io.CopyStreamListener;
import org.apache.commons.net.util.TrustManagerUtils;
import org.apache.log4j.Logger;
public class ftp {
	final static org.apache.log4j.Logger logger = Logger.getLogger(ftp.class);
	
    boolean uploadFile = false, binaryTransfer = false, error = false, listFiles = false, listNames = false, hidden = false;
    boolean downloadFile = false;
    public boolean isDownloadFile() {
		return downloadFile;
	}


	public void setDownloadFile(boolean downloadFile) {
		this.downloadFile = downloadFile;
	}



	boolean localActive = false, useEpsvWithIPv4 = false, feat = false, printHash = false;
    boolean mlst = false, mlsd = false, mdtm = false, saveUnparseable = false;
    boolean lenient = false;
    long keepAliveTimeout = -1;
    int controlKeepAliveReplyTimeout = -1;
    int minParams = 5; // listings require 3 params
    String protocol = null; // SSL protocol (true/false)
    String doCommand = null;
    String serverHost ="";
    int port = -1;
    String trustmgr = null; //(all)/(valid)/(none)
    
    String proxyHost = null; // IPAddress
    int proxyPort = 80;
    String proxyUser = null;
    String proxyPassword = null;
    String username = null;
    String password = null;
    String encoding = null;
    String serverTimeZoneId = null;
    String displayTimeZoneId = null;
    String serverType = null;
    String defaultDateFormat = null;
    String recentDateFormat = null;
    
    public static void main(String[] args) {
         String server = "192.168.61.41";
        ftp _ftp = new ftp();
        
        
        String username = "aiden";
        
        _ftp.setUsername("aiden");
        _ftp.setPassword("aiden");
        _ftp.setServerHost("192.168.61.41");
        _ftp.setPassword("aiden");
        _ftp.setPort(21);
         
         String src_file ="Txtype_statistic216-06-14-11-06-00-60.csv";
         String src_path = "d:/Output/";
         String target_file = "Projectxxx.zip";
        
/*         String download_path ="";
        boolean _send_flg = _ftp.send(_ftp.getServerHost(), _ftp.getUsername(), _ftp.getPassword(), 
        						_ftp.getPort(), 
        						src_path + src_file, target_file );
         
         
         */
        
         String _localPath = "D:/OUTPUT/CEBUT/IMPORT/";
         String _localFileName = "SYSTEM_PARAMETER.csv";
         String _remotePath = "/20160705/";
         // _remotePath = "/";
          String _remoteFileName = "SYSTEM_PARAMETER.csv";
         // _remoteFileName="TEST.xls";
          _remoteFileName=_localFileName ; 
         
         _ftp.download(_remotePath, _remoteFileName, _localPath, _localFileName);
      // boolean _status =  _ftp.upload(_remotePath, _remoteFileName, _localPath, _localFileName);
         
    }
    
    
    public ftp(){
    	
    }
    public  boolean send(String _server, String _user, String _pass, int _port,
							   String _source_file_name, 
							   String _target_rtm_file_name ){
    	return upload( _server, _user, _pass, _port,_source_file_name, _target_rtm_file_name );
    }
    
    
    
    public boolean upload(String _server, String _user, String _pass, int _port,
				   String _source_file_name, 
				   String _target_rtm_file_name ){

    	boolean _correct = true;
    	 FTPClient ftpClient = new FTPClient();
         try {
  
             ftpClient.connect(_server, _port);
             ftpClient.login(_user, _pass);
             ftpClient.enterLocalPassiveMode();
  
             ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
  
             // APPROACH #1: uploads first file using an InputStream
             File _source_file = new File(_source_file_name);
  
            
             InputStream inputStream_source = new FileInputStream(_source_file);
  
             logger.debug("Start uploading file");
             boolean done = ftpClient.storeFile(_target_rtm_file_name, inputStream_source);
             inputStream_source.close();
             if (done) {
                 logger.debug("The file is uploaded successfully.");
             }
  
         
         } catch (IOException ex) {
             logger.debug("Error: " + ex.getMessage());
             _correct=false;
             ex.printStackTrace();
         } finally {
             try {
                 if (ftpClient.isConnected()) {
                     ftpClient.logout();
                     ftpClient.disconnect();
                 }
             } catch (IOException ex) {
            	 _correct=false;
            	 ex.printStackTrace();
             }
         }
     	return _correct;
    }
public FTPClient getFTP(){
	FTPClient ftp;
    if (protocol == null ) {
        if(proxyHost !=null) {
            logger.debug("Using HTTP proxy server: " + proxyHost);
            ftp = new FTPHTTPClient(proxyHost, proxyPort, proxyUser, proxyPassword);
        }
        else {
            ftp = new FTPClient();
        }
    } else {
        FTPSClient ftps;
        if (protocol.equals("true")) {
            ftps = new FTPSClient(true);
        } else if (protocol.equals("false")) {
            ftps = new FTPSClient(false);
        } else {
            String prot[] = protocol.split(",");
            if (prot.length == 1) { // Just protocol
                ftps = new FTPSClient(protocol);
            } else { // protocol,true|false
                ftps = new FTPSClient(prot[0], Boolean.parseBoolean(prot[1]));
            }
        }
        ftp = ftps;
        if ("all".equals(trustmgr)) {
            ftps.setTrustManager(TrustManagerUtils.getAcceptAllTrustManager());
        } else if ("valid".equals(trustmgr)) {
            ftps.setTrustManager(TrustManagerUtils.getValidateServerCertificateTrustManager());
        } else if ("none".equals(trustmgr)) {
            ftps.setTrustManager(null);
        }
    }

    if (printHash) {
        ftp.setCopyStreamListener(createListener());
    }
    if (keepAliveTimeout >= 0) {
        ftp.setControlKeepAliveTimeout(keepAliveTimeout);
    }
    if (controlKeepAliveReplyTimeout >= 0) {
        ftp.setControlKeepAliveReplyTimeout(controlKeepAliveReplyTimeout);
    }
    if (encoding != null) {
        ftp.setControlEncoding(encoding);
    }
    ftp.setListHiddenFiles(hidden);

    // suppress login details
    ftp.addProtocolCommandListener((ProtocolCommandListener) new PrintCommandListener(new PrintWriter(System.out), true));

    final FTPClientConfig config;
    if (serverType != null) {
        config = new FTPClientConfig(serverType);
    } else {
        config = new FTPClientConfig();
    }
    config.setUnparseableEntries(saveUnparseable);
    if (defaultDateFormat != null) {
        config.setDefaultDateFormatStr(defaultDateFormat);
    }
    if (recentDateFormat != null) {
        config.setRecentDateFormatStr(recentDateFormat);
    }
    ftp.configure(config);
    return ftp;
}


public boolean connect( FTPClient _ftp){    
    final FTPClient ftp = _ftp;
    boolean _connect = true;

    try
    {
        int reply;
        if (port > 0) {
            ftp.connect(serverHost, port);
        } else {
            ftp.connect(serverHost);
        }
        logger.debug("Connected to " + serverHost + " on " + (port>0 ? port : ftp.getDefaultPort()));

        // After connection attempt, you should check the reply code to verify
        // success.
        reply = ftp.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply))
        {
            ftp.disconnect();
            _connect = false;
            System.err.println("FTP server refused connection.");
            return _connect;
            //System.exit(1);
        }
    }
    catch (IOException e)
    {
        if (ftp.isConnected())
        {
            try
            {
                ftp.disconnect();
            }
            catch (IOException f)
            {
                // do nothing
            }
        }
        System.err.println("Could not connect to server.");
        e.printStackTrace();
        return _connect;
    }
    return _connect;
}

public boolean download(String _remotePath, String _remoteFileName, String _localPath, String _localFileName){
	this.setDownloadFile(true);
	return this.proceed(_remotePath, _remoteFileName, _localPath, _localFileName);
}

public boolean upload(String _remotePath, String _remoteFileName, String _localPath, String _localFileName){
	this.setUploadFile(true);
	return this.proceed(_remotePath, _remoteFileName, _localPath, _localFileName);
}	

public boolean proceed(String _remotePath, String _remoteFileName, String _localPath, String _localFileName){
	
	boolean _download = true;
	
	String _remote_str = _remotePath +  _remoteFileName;
	String _local_file_str = _localPath + _localFileName;
	FTPClient _ftp = getFTP();
	FTPClientConfig config;
	
	if  (!this.connect(_ftp)){
		_download = false;
		return _download;
	}
	
    if (serverType != null) {
        config = new FTPClientConfig(serverType);
    } else {
        config = new FTPClientConfig();
    }
    
    config.setUnparseableEntries(saveUnparseable);
    if (defaultDateFormat != null) {
        config.setDefaultDateFormatStr(defaultDateFormat);
    }
    if (recentDateFormat != null) {
        config.setRecentDateFormatStr(recentDateFormat);
    }
    try
    {
        if (!_ftp.login(username, password))
        {
            _ftp.logout();
            error = true;
            _download = false;
            return _download;
        }

        logger.debug("Remote system is " + _ftp.getSystemType());

        if (binaryTransfer) {
            _ftp.setFileType(FTP.BINARY_FILE_TYPE);
        } else {
            // in theory this should not be necessary as servers should default to ASCII
            // but they don't all do so - see NET-500
            _ftp.setFileType(FTP.ASCII_FILE_TYPE);
        }

        // Use passive mode as default because most of us are
        // behind firewalls these days.
        if (localActive) {
            _ftp.enterLocalActiveMode();
        } else {
            _ftp.enterLocalPassiveMode();
        }

        _ftp.setUseEPSVwithIPv4(useEpsvWithIPv4);

        if (uploadFile)
        {
            InputStream input_filestream;

            input_filestream = new FileInputStream(_localPath+_localFileName);
            _remote_str = _remotePath +  _remoteFileName;
            _ftp.storeFile(_remote_str , input_filestream);

          
            input_filestream.close();
        }
        // Allow multiple list types for single invocation
        else if (listFiles || mlsd || mdtm || mlst || listNames)
        {

        	logger.debug("=-==---------------------");
            if (mlsd) {
            	logger.debug("mlsd");
                for (FTPFile f : _ftp.mlistDir(_remote_str)) {
                	logger.debug("-----" + f.getRawListing());
                    
                	logger.debug(f.toFormattedString());
                }
            }
            if (mdtm) {
                FTPFile f = _ftp.mdtmFile(_remote_str);
                logger.debug(f.getRawListing());
                //System.out.println(f.toFormattedString(displayTimeZoneId));
                logger.debug(f.toFormattedString());
            }
            if (mlst) {
            	logger.debug("=-==-----MLST----------------" + _remote_str);
                FTPFile f = _ftp.mlistFile(_remote_str);
                if (f != null){
                	logger.debug(f.toFormattedString(displayTimeZoneId));
                	logger.debug("--mlst---" + f.toFormattedString());
                } else {
                	logger.debug("Null F");
                }
            }
            if (listNames) {
                for (String s : _ftp.listNames(_remote_str)) {
                	logger.debug(s);
                }
            }
            // Do this last because it changes the client
            ;
            
            if (listFiles) {
                if (lenient || serverTimeZoneId != null) {
                    config.setLenientFutureDates(lenient);
                    if (serverTimeZoneId != null) {
                        config.setServerTimeZoneId(serverTimeZoneId);
                    }
                    _ftp.configure(config );
                }

                for (FTPFile f : _ftp.listFiles(_remote_str)) {
                	logger.debug(f.getRawListing());
                	logger.debug(f.toFormattedString(displayTimeZoneId));
                }
            }
        }
        else if (feat)
        {
            // boolean feature check
            if (_remote_str != null) { // See if the command is present
                if (_ftp.hasFeature(_remote_str)) {
                	logger.debug("Has feature: "+_remote_str);
                } else {
                    if (FTPReply.isPositiveCompletion(_ftp.getReplyCode())) {
                    	logger.debug("FEAT "+_remote_str+" was not detected");
                    } else {
                    	logger.debug("Command failed: "+_ftp.getReplyString());
                    }
                }

                // Strings feature check
                String []features = _ftp.featureValues(_remote_str);
                if (features != null) {
                    for(String f : features) {
                    	logger.debug("FEAT "+_remote_str+"="+f+".");
                    }
                } else {
                    if (FTPReply.isPositiveCompletion(_ftp.getReplyCode())) {
                    	logger.debug("FEAT "+_remote_str+" is not present");
                    } else {
                    	logger.debug("Command failed: "+_ftp.getReplyString());
                    }
                }
            } else {
                if (_ftp.features()) {
//                    Command listener has already printed the output
                } else {
                	logger.debug("Failed: "+_ftp.getReplyString());
                }
            }
        }
        else if (doCommand != null)
        {
            if (_ftp.doCommand(doCommand, _remote_str)) {
//              Command listener has already printed the output
//                for(String s : ftp_backup.getReplyStrings()) {
//                    System.out.println(s);
//                }
            } else {
            	logger.debug("Failed: "+_ftp.getReplyString());
            }
        }
        else  if (downloadFile)
        {
            OutputStream outputStream;

            outputStream = new FileOutputStream(_local_file_str);
            logger.debug(_local_file_str);
            _ftp.retrieveFile(_remote_str, outputStream);
            String _bakup_path = "20160705/backup/";
            String _remote_bakcup_str =  _bakup_path +  _remoteFileName + ".done";
            _ftp.makeDirectory(_bakup_path);
            _ftp.rename(_remote_str, _remote_bakcup_str);
            outputStream.close();
        }

        _ftp.noop(); // check that control connection is working OK

        _ftp.logout();
    }
    catch (FTPConnectionClosedException e)
    {
        error = true;
        System.err.println("Server closed connection.");
        e.printStackTrace();
    }
    catch (IOException e)
    {
        error = true;
        logger.debug(e.getMessage());
        e.printStackTrace();
    }
    finally
    {
        if (_ftp.isConnected())
        {
            try
            {
                _ftp.disconnect();
            }
            catch (IOException f)
            {
                // do nothing
            }
        }
    }

    return true;
} // end main



	public boolean isUploadFile() {
		return uploadFile;
	}



	public void setUploadFile(boolean uploadFile) {
		this.uploadFile = uploadFile;
	}



	public boolean isBinaryTransfer() {
		return binaryTransfer;
	}



	public void setBinaryTransfer(boolean binaryTransfer) {
		this.binaryTransfer = binaryTransfer;
	}



	public boolean isError() {
		return error;
	}



	public void setError(boolean error) {
		this.error = error;
	}



	public boolean isListFiles() {
		return listFiles;
	}



	public void setListFiles(boolean listFiles) {
		this.listFiles = listFiles;
	}



	public boolean isListNames() {
		return listNames;
	}



	public void setListNames(boolean listNames) {
		this.listNames = listNames;
	}



	public boolean isHidden() {
		return hidden;
	}



	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}



	public boolean isLocalActive() {
		return localActive;
	}



	public void setLocalActive(boolean localActive) {
		this.localActive = localActive;
	}



	public boolean isUseEpsvWithIPv4() {
		return useEpsvWithIPv4;
	}



	public void setUseEpsvWithIPv4(boolean useEpsvWithIPv4) {
		this.useEpsvWithIPv4 = useEpsvWithIPv4;
	}



	public boolean isFeat() {
		return feat;
	}



	public void setFeat(boolean feat) {
		this.feat = feat;
	}



	public boolean isPrintHash() {
		return printHash;
	}



	public void setPrintHash(boolean printHash) {
		this.printHash = printHash;
	}



	public boolean isMlst() {
		return mlst;
	}



	public void setMlst(boolean mlst) {
		this.mlst = mlst;
	}



	public boolean isMlsd() {
		return mlsd;
	}



	public void setMlsd(boolean mlsd) {
		this.mlsd = mlsd;
	}



	public boolean isMdtm() {
		return mdtm;
	}



	public void setMdtm(boolean mdtm) {
		this.mdtm = mdtm;
	}



	public boolean isSaveUnparseable() {
		return saveUnparseable;
	}



	public void setSaveUnparseable(boolean saveUnparseable) {
		this.saveUnparseable = saveUnparseable;
	}



	public boolean isLenient() {
		return lenient;
	}



	public void setLenient(boolean lenient) {
		this.lenient = lenient;
	}



	public long getKeepAliveTimeout() {
		return keepAliveTimeout;
	}



	public void setKeepAliveTimeout(long keepAliveTimeout) {
		this.keepAliveTimeout = keepAliveTimeout;
	}



	public int getControlKeepAliveReplyTimeout() {
		return controlKeepAliveReplyTimeout;
	}



	public void setControlKeepAliveReplyTimeout(int controlKeepAliveReplyTimeout) {
		this.controlKeepAliveReplyTimeout = controlKeepAliveReplyTimeout;
	}



	public int getMinParams() {
		return minParams;
	}



	public void setMinParams(int minParams) {
		this.minParams = minParams;
	}



	public String getProtocol() {
		return protocol;
	}



	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}



	public String getDoCommand() {
		return doCommand;
	}



	public void setDoCommand(String doCommand) {
		this.doCommand = doCommand;
	}



	public String getTrustmgr() {
		return trustmgr;
	}



	public void setTrustmgr(String trustmgr) {
		this.trustmgr = trustmgr;
	}



	public String getProxyHost() {
		return proxyHost;
	}



	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}



	public int getProxyPort() {
		return proxyPort;
	}



	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}



	public String getProxyUser() {
		return proxyUser;
	}



	public void setProxyUser(String proxyUser) {
		this.proxyUser = proxyUser;
	}



	public String getProxyPassword() {
		return proxyPassword;
	}



	public void setProxyPassword(String proxyPassword) {
		this.proxyPassword = proxyPassword;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEncoding() {
		return encoding;
	}



	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}



	public String getServerTimeZoneId() {
		return serverTimeZoneId;
	}



	public void setServerTimeZoneId(String serverTimeZoneId) {
		this.serverTimeZoneId = serverTimeZoneId;
	}



	public String getDisplayTimeZoneId() {
		return displayTimeZoneId;
	}



	public void setDisplayTimeZoneId(String displayTimeZoneId) {
		this.displayTimeZoneId = displayTimeZoneId;
	}



	public String getServerType() {
		return serverType;
	}



	public void setServerType(String serverType) {
		this.serverType = serverType;
	}



	public String getDefaultDateFormat() {
		return defaultDateFormat;
	}



	public void setDefaultDateFormat(String defaultDateFormat) {
		this.defaultDateFormat = defaultDateFormat;
	}



	public String getRecentDateFormat() {
		return recentDateFormat;
	}



	public void setRecentDateFormat(String recentDateFormat) {
		this.recentDateFormat = recentDateFormat;
	}


	public String getServerHost() {
		return serverHost;
	}


	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}

	
	
	private static CopyStreamListener createListener(){
	    return new CopyStreamListener(){
	        private long megsTotal = 0;

	        public void bytesTransferred(CopyStreamEvent event) {
	            bytesTransferred(event.getTotalBytesTransferred(), event.getBytesTransferred(), event.getStreamSize());
	        }

	        public void bytesTransferred(long totalBytesTransferred,
	                int bytesTransferred, long streamSize) {
	            long megs = totalBytesTransferred / 1000000;
	            for (long l = megsTotal; l < megs; l++) {
	                System.err.print("#");
	            }
	            megsTotal = megs;
	        }
	    };
	}
}


