package general;

import java.io.File;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileSplit {
    public void basicStuff(){
		System.out.println("FileSplit currently does nothing");
	}
    
    public static void splitFile(File f) throws IOException {
        int partCounter = 0;

        int sizeOfFiles = 1024;// 1MB
        byte[] buffer = new byte[sizeOfFiles];

        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(f))) {
            String name = f.getName();

            int tmp = 0;
            while ((tmp = bis.read(buffer)) > 0) {
                //write each chunk of data into separate file with different number in name
                File newFile = new File(f.getParent(), name + "."
                        + String.format("%03d", partCounter++));
                try (FileOutputStream out = new FileOutputStream(newFile)) {
                    out.write(buffer, 0, tmp);//tmp is chunk size
                    System.out.println("this is doing what its supposed to");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
    	String s = TestCheckSum.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString();
		String datafile = s.substring(0,s.length()-5)+"/res/test.jpg";
    	splitFile(new File(datafile));
    }
}