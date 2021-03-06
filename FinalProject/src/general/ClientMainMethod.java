package general;
import static general.Client.getFile;
import static general.FileMerge.mergeFiles;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientMainMethod {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try{
			while(true){
		        getFile("192.168.1.3",9090);
		    }
		}catch(ConnectException e){
			List<File> temp = (Arrays.asList(new File("res/test").listFiles()));
			for(int i=0;i<temp.size();i++){
				System.out.println(temp.get(i).getAbsolutePath());
				if(temp.get(i).getAbsolutePath().contains(".ffmpm")){
					TextFileReader fr=new TextFileReader(temp.get(i));
					ArrayList<File> tempFiles = new ArrayList<File>();
					String tempName = fr.getLine(0);
					for(int j=0;j<Integer.parseInt(fr.getLine(2))-1;j++){
						tempFiles.add(new File("res/test/$temp/"+tempName  + "." + String.format("%07d", j)));
					}
					mergeFiles(tempFiles,new File("res/test/" +tempName));
				}
			}
		}
	}

}
