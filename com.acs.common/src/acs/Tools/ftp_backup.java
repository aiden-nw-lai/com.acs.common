package acs.Tools;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
public class ftp_backup {


    public static void main(String[] args) {
        String server = "192.168.61.41";
        int port = 21;
        String user = "aiden";
        String pass = "aiden";
        String src_file ="d:/Output/Txtype_statistic216-06-14-11-06-00-60.csv";
        String target_file = "Projectxxx.zip";
        boolean _send_flg = send(server, user, pass, port, src_file, target_file );
    }
    
    
    
    public static boolean send(String _server, String _user, String _pass, int _port,
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
  
             System.out.println("Start uploading file");
             boolean done = ftpClient.storeFile(_target_rtm_file_name, inputStream_source);
             inputStream_source.close();
             if (done) {
                 System.out.println("The file is uploaded successfully.");
             }
  
         
         } catch (IOException ex) {
             System.out.println("Error: " + ex.getMessage());
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

}
