package acs.Tools;

import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UserInfo;

public class sftp_backup {
    public static void main(String args[]) {
        
        String _server = "192.168.61.41";
        String _password = "aiden";
        String _source_file = "d:/Output/XYZ__20160613.xls";
        String _target_rtm_file = "test11ing_ftp.txt";
        String _user = "aiden";
        int _port = 22;
        
        if ( send(_server, _user, _password, _port, _source_file, _target_rtm_file)){
        	System.out.println("transfer normally");
        };
    }
    public static boolean send(String _server, String _user, String _password, int _port,
    							String _source_file, 
    							String _target_rtm_file ){
    	boolean _correct = true;
    	JSch jsch = new JSch();
        Session session = null;
        
       
        try {
            session = jsch.getSession(_user, _server, _port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(_password);
            session.connect();

            Channel channel = session.openChannel("sftp_backup");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.put(_source_file, _target_rtm_file);
       //     sftpChannel.get("remotefile.txt", "localfile.txt");
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
        	_correct = false;
        	e.printStackTrace();  
            
        } catch (SftpException e) {
        	_correct = false;
            e.printStackTrace();
        }
    	return _correct;
    	
    }
}
