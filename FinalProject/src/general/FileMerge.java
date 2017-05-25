package general;

import java.io.File;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.Files;


public class FileMerge {
	static String s = CheckSum.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString();
	static String dataFile = s.substring(0,s.length()-5)+"/res/img";
	static String dataDirectory = s.substring(0,s.length()-5)+"/res/test2.jpg";
	
	static File f = new File(dataFile);
	
	public static void mainFileMerge(String[] args) throws IOException{
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
		mergeFiles(files, new File(dataDirectory));
	}
	public static void mergeFiles(ArrayList<File> files, File into)
	        throws IOException {
	    try (BufferedOutputStream mergingStream = new BufferedOutputStream(
	            new FileOutputStream(into))) {
	        for (File f : files) {
	            Files.copy(f.toPath(), mergingStream);
	        }
	    }
	}
}


	