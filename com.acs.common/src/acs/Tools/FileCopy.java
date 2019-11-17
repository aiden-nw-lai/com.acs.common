package acs.Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class FileCopy {
	public static void Copy(String source, String dest)  throws IOException {
		System.out.println(source); 
		File source_file = new File(source);
		System.out.println(dest);
		 File dest_file = new File(dest);
		 Copy(source_file, dest_file);
	}
	private static void Copy(File source, File dest)
			throws IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} finally {
			inputChannel.close();
			outputChannel.close();
		}
	}
}
