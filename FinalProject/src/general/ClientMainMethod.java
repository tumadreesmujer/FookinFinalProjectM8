package general;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientMainMethod {

	public static void main(String[] args) throws IOException {
		boolean tempBool = true;
		while(tempBool){
			String temp =Server.getText(4444);
			if(temp.equals("!!done")){
				tempBool=false;
			}else{
		        Server.getFile(new File(temp),4445);
		        if(!temp.contains(".versioning"))
		        EncryptOrDecrypt.decrypt("default_key", new File(temp), new File(temp));
			}
	    }
		System.out.println("done!");
		List<File> temp = (Arrays.asList(new File("res/test").listFiles()));
		for(int i=0;i<temp.size();i++){
			System.out.println(temp.get(i).getAbsolutePath());
			if(temp.get(i).getAbsolutePath().contains(".ffmpm")){
				TextFileReader fr=new TextFileReader(temp.get(i));
				ArrayList<File> tempFiles = new ArrayList<File>();
				String tempName = fr.getLine(0);
				for(int j=0;j<Integer.parseInt(fr.getLine(2));j++){
					tempFiles.add(new File("res/test/$temp/"+tempName  + "." + String.format("%07d", j)));
				}
				FileMerge.mergeFiles(tempFiles,new File("res/test/" +tempName));
			}
		}
		/*try{
			while(true){
		        Client.getFile("192.168.1.3",9090);
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
		}*/
	}

}
