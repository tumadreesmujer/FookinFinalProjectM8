package general;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileSplit {
    public FileSplit(File f,int kB) throws IOException {
        int partCounter = 0;

        int sizeOfFiles = kB;
        byte[] buffer = new byte[sizeOfFiles];

        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(f))) {
            String name = f.getName();

            int tmp = 0;
            while ((tmp = bis.read(buffer)) > 0) {
                //write each chunk of data into separate file with different number in name
                File newFile = new File(f.getParent()+"/$temp", name + "." + String.format("%07d", partCounter++));
                try (FileOutputStream out = new FileOutputStream(newFile)) {
                    out.write(buffer, 0, tmp);//tmp is chunk size
                } catch (FileNotFoundException e){
                	Files.createDirectories(Paths.get(f.getParent()+"/$temp"));
                	FileOutputStream out = new FileOutputStream(newFile);
                	out.write(buffer, 0, tmp);
                	out.close();
                }
            }
        }
    }

    public static void mainFileSplit(String[] args) throws IOException {
    	String s = FileSplit.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString();
		String datafile = s.substring(0,s.length()-5)+"/res/test.jpg";
    	//FileSplit(new File(datafile));
    }
}