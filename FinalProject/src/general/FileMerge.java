package general;

import java.io.File;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.Files;


public class FileMerge {
	static File f = new File("C:/Users/VytautasB18/Documents/fookinAIDS/FookinFinalProjectM8/FinalProject/res/img");
	
	public static void mainFileMerge(String[] args) throws IOException{
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
		mergeFiles(files, new File("C:/Users/VytautasB18/Documents/fookinAIDS/FookinFinalProjectM8/FinalProject/res/test2.jpg"));
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


	