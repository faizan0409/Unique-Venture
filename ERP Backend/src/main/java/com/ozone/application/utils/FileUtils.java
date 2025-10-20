package com.ozone.application.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	public static void createDirectoryIfNotExist(String directoryName) {
		
		 File directory = new File(directoryName);
		    if (! directory.exists()){
		        directory.mkdir();
		    }
	}
	
	public static boolean createAndWriteIntoFile(MultipartFile file, String filePath) {
		boolean status = false;
		
		try {
			java.io.File fileTobeUploaded = new java.io.File(filePath);
			FileOutputStream fout = new FileOutputStream(fileTobeUploaded);
			fout.write(file.getBytes());
			fout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

}

