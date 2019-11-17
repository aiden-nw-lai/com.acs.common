package acs.Tools;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ProgressMonitor;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;
import com.jcraft.jsch.SftpStatVFS;
import com.jcraft.jsch.UserInfo;

public class sftp {
	final static org.apache.log4j.Logger logger = Logger.getLogger(sftp.class);
	
	String host, user, password; 
	int port;
	
    public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public static void main(String args[]) {
        
        String _server = "192.168.61.41";
        String _password = "aiden";
        String _source_file = "d:/Output/XYZ__20160613.xls";
        String _target_rtm_file = "test11ing_ftp.txt";
        String _user = "aiden";        
/*        if ( send(_server, _user, _password, _source_file, _target_rtm_file)){
        	logger.debug("transfer normally");
        };*/
        sftp _sftp = new sftp();
        int port = 22;
        
        String src_file = "/20160705/SYSTEM_PARAMETER.csv";
        String src_path = "TESTING";
        src_path = _sftp.getbackup_path(src_path);
        String fileName = "SYSTEM_PARAMETER.csv";
        
        src_file = src_path + fileName;
        
        String dest_file = src_file;
        String download_file = "SYSTEM_PARAMETER.csv";
        String download_path = "D:/OUTPUT/CEBUT/IMPORT_SFTP";
        download_path = _sftp.getbackup_path(download_path);
        download_file = download_path + download_file;

        logger.debug(download_file);
        //if ( send(_server, _user, _password, local_file , dest_file )){
        //	logger.debug("transfer normally");
        //};
        _sftp.download(_server,_user, _password, port, src_file, download_file);
        
        //	Session session = getSession(host, user, password, port);
       
        
    }
	public static boolean upload(String _server, String _user, String _password, int _port,
			String _source_file, 
			String _target_rtm_file ){
		 return sftp.send(_server, _user, _password, _port, _source_file, _target_rtm_file);
	}
	public static boolean send(String _server, String _user, String _password, int _port,
    							String _source_file, 
    							String _target_rtm_file ){
    	boolean _correct = true;
    	JSch jsch = new JSch();
        Session session = null;
        sftp _sftp = new sftp();
        
        try {
            session = jsch.getSession(_user, _server, _port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(_password);
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            
	        if (_target_rtm_file.lastIndexOf("/") < 0){
	        	//logger.debug(_target_rtm_file.lastIndexOf("\\"));
	        	if (_target_rtm_file.lastIndexOf("\\") >= 0){
	        		_target_rtm_file=_target_rtm_file.replaceAll("\\\\", "/");
	        	//	logger.debug(_BackupPath);
	        	}
	        	
	        }
	        
            _sftp.mkdir(_server, _user, _password, _port, _target_rtm_file);
            logger.debug("target:" + _target_rtm_file);
            logger.debug("source:" + _source_file);
            sftpChannel.put(_source_file, _target_rtm_file);
            
       //     sftpChannel.get("remotefile.txt", "localfile.txt");
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
        	_correct = false;
        	logger.debug(e.getMessage());  
            
        } catch (SftpException e) {
        	_correct = false;
        	logger.debug(e.getMessage());
        }
        session.disconnect();
        
    	return _correct;
    	
    }
    public ChannelSftp getChannel(String host, String user, 
    					String password, int port){
    
    		return getChannel(getSession(host, user, password, port));
    }	
    
    public ChannelSftp getChannel(Session _session){    	
    	ChannelSftp _channel = null;
    	 try{
             Channel channel=_session.openChannel("sftp");
             channel.connect();
             _channel=(ChannelSftp)channel;
    	 } catch(Exception e){
                 logger.debug(e);
         }
    	 return _channel;
    }
    
    public Session  getSession(String host, String user,
    				String _password, int port){
    	Session session = null;
    	try{
            JSch jsch=new JSch();
            
            session=jsch.getSession(user, host, port);
            session = jsch.getSession(user, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(_password);
            session.connect();

   	 } catch(Exception e){
                logger.debug(e);
        }
   	 return session;
    	
    }
    
    public void download(String host,String user, String password, int port, 
    		String srcFileName, String dstFileName){
    	Session session = getSession(host, user, password, port);
        ChannelSftp _channel=getChannel(session);
   
  	  try{
  		
	       SftpProgressMonitor monitor=new MyProgressMonitor();
	        File file = new File(dstFileName);
	        file.getParentFile().mkdirs();
	        
	       int mode=ChannelSftp.OVERWRITE;
	       logger.debug("src file:" + srcFileName);
	       logger.debug("destination file " + dstFileName);
	       _channel.get(srcFileName, dstFileName, monitor);	       
	  }
	  catch(SftpException e){
		  logger.debug(e.getMessage());
	  }
  	  
      _channel.exit();
  	  _channel.disconnect();
  	  session.disconnect();

    }
    public String getbackup_path(String staticPath){
            //int fileNameStartIndex = filePath.lastIndexOf("/") + 1;
            String date = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
            //String fileName = filePath.substring(fileNameStartIndex);
            
            String completeBackupPath = staticPath + "/" + date + "/" ;//+ recObj.getUsername() + "/" + recObj.getStatus() + "/" + recObj.getDisposition() + "/";
            return completeBackupPath ;
    }
    
    public void mkdir(String host,String user, String password, int port, 
    		String _BackupPath){
	    	Session session = getSession(host, user, password, port);
	        ChannelSftp _channel=getChannel(session);
	        int fileNameStartIndex = -1;
	        
	        if (_BackupPath.lastIndexOf("/") < 0){
	        	logger.debug(_BackupPath.lastIndexOf("\\"));
	        	if (_BackupPath.lastIndexOf("\\") >= 0){
	        		_BackupPath=_BackupPath.replaceAll("\\\\", "/");
	        		logger.debug(_BackupPath);
	        	}
	        	
	        }
	        fileNameStartIndex = _BackupPath.lastIndexOf("/") + 1;
	        String _file = _BackupPath.substring(0, fileNameStartIndex);
	        
	        logger.debug("Creating Directory.......[" + _file + "]" );
            
            String[] complPath = _file.split("/");
            try {
	            _channel.cd("/");
	            for (String folder : complPath) {
	                if (folder.length() > 0) {
	                    try {
	                        
	            			if(logger.isDebugEnabled()){
	            			    logger.debug("Current Dir : " + _channel.pwd());
	            			}
	                        _channel.cd(folder);
	                    } catch (SftpException e2) {
	                    	if(logger.isDebugEnabled()){
	            			    logger.debug(e2.getMessage());
	            			}
	                    	if(logger.isDebugEnabled()){
	            			    logger.debug("... Dir : " + folder);
	            			}
	                    	_channel.mkdir(folder);
	                    	_channel.cd(folder);
	                    }
	                }
	            }
	            _channel.cd("/");
	            logger.debug("Current Dir : " + _channel.pwd());
	           // _channel.put(get, completeBackupPath + fileName);
            } catch (SftpException e2) {
            	if(logger.isDebugEnabled()){
    			    logger.debug(e2.getMessage());
    			}
	        }
            _channel.exit();
        	  _channel.disconnect();
        	  session.disconnect();
            
        }


    public void deletefile(String host,String user, String password, int port,String srcPath){
    	Session session = getSession(host, user, password, port);
        ChannelSftp _channel=getChannel(session);
        try {
	        _channel.rm(srcPath);
		} catch (SftpException e) {
			logger.debug(e.getMessage());
		    e.printStackTrace();
		}
        _channel.exit();
    	_channel.disconnect();
    	session.disconnect();
	}
    
	
	public void movefile(String host,String user, String password, int port, String srcPath, String destPath){
		Session session = getSession(host, user, password, port);
        ChannelSftp _channel=getChannel(session);
        try {
        	
	        logger.debug("move file scrPath:" + srcPath);
	        logger.debug("move file destPath:" + destPath);
            //_channel.rename(srcPath, "/MRTSI_EXPORT/TMP.BAK);
	        _channel.rename(srcPath, destPath);
        } catch (SftpException e2) {
        	if(logger.isDebugEnabled()){
			    logger.debug(e2.getMessage());
			}
        }
        _channel.exit();
    	_channel.disconnect();
    	session.disconnect();
	}

	
}
