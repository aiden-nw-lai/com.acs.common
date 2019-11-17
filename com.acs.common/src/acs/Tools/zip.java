package acs.Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.util.Zip4jConstants;

public class zip {
	final static Logger logger = Logger.getLogger(zip.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String Source_file = "D:/OUTPUT/Txtype_statistic216-06-15-04-55-00-60.csv";
		String Target_file = Source_file + ".zip";
		zipFile(new File(Source_file), Target_file);
		String Target_pass_file = Source_file + "pass.zip";
		String _password = "testing";
		zipwithPassword(Source_file, Target_pass_file, _password);

	}
	public static void zipFile(String inputFile, String zipFilePath) {
		zipFile(new File(inputFile),zipFilePath );
	}
	public static void zipFile(File inputFile, String zipFilePath) {
        try {

            // Wrap a FileOutputStream around a ZipOutputStream
            // to store the zip stream to a file. Note that this is
            // not absolutely necessary
            FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

            // a ZipEntry represents a file entry in the zip archive
            // We name the ZipEntry after the original file's name
            ZipEntry zipEntry = new ZipEntry(inputFile.getName());
            zipOutputStream.putNextEntry(zipEntry);

            FileInputStream fileInputStream = new FileInputStream(inputFile);
            byte[] buf = new byte[1024];
            int bytesRead;

            // Read the input file by chucks of 1024 bytes
            // and write the read bytes to the zip stream
            while ((bytesRead = fileInputStream.read(buf)) > 0) {
                zipOutputStream.write(buf, 0, bytesRead);
            }

            // close ZipEntry to store the stream to the file
            zipOutputStream.closeEntry();

            zipOutputStream.close();
            fileOutputStream.close();

            logger.debug("Regular file :" + inputFile.getCanonicalPath()+" is zipped to archive :"+zipFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	public static void zipwithPassword(String _srcFile, String _target, String _password){
		
		        try {
		            //This is name and path of zip file to be created
		            ZipFile zipFile = new ZipFile(_target);
		             
		            //Add files to be archived into zip file
		            ArrayList<File> filesToAdd = new ArrayList<File>();
		            filesToAdd.add(new File(_srcFile));
		           // filesToAdd.add(new File("C:/temp/test2.txt"));
		             
		            //Initiate Zip Parameters which define various properties
		            ZipParameters parameters = new ZipParameters();
		            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // set compression method to deflate compression
		             
		            //DEFLATE_LEVEL_FASTEST     - Lowest compression level but higher speed of compression
		            //DEFLATE_LEVEL_FAST        - Low compression level but higher speed of compression
		            //DEFLATE_LEVEL_NORMAL  - Optimal balance between compression level/speed
		            //DEFLATE_LEVEL_MAXIMUM     - High compression level with a compromise of speed
		            //DEFLATE_LEVEL_ULTRA       - Highest compression level but low speed
		            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); 
		             
		            //Set the encryption flag to true
		            parameters.setEncryptFiles(true);
		             
		            //Set the encryption method to AES Zip Encryption
		            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
		             
		            //AES_STRENGTH_128 - For both encryption and decryption
		            //AES_STRENGTH_192 - For decryption only
		            //AES_STRENGTH_256 - For both encryption and decryption
		            //Key strength 192 cannot be used for encryption. But if a zip file already has a
		            //file encrypted with key strength of 192, then Zip4j can decrypt this file
		            parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
		             
		            //Set password
		            parameters.setPassword(_password);
		             
		            //Now add files to the zip file
		            zipFile.addFiles(filesToAdd, parameters);
		        } 
		        catch (ZipException e) 
		        {
		            e.printStackTrace();
		        }
		    }
	
	
}
