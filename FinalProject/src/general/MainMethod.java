package general;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import static general.Server.sendFile;

public class MainMethod {
	private static int pSize=1024*24;
	private static String ip;
	private static ArrayList<PackerMan> pMan = new ArrayList<PackerMan>();
	public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException, IOException{
		AutoDetect aD = new AutoDetect("res");
		List <File> files = aD.getFiles();
		
		if(files!=null){
			for(int i=0;i<files.size();i++){
				pMan.add(new PackerMan(files.get(i),pSize,0));
				sendFile(new File(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().lastIndexOf("."))+".ffmpm" ));
				for(int j=0;j<pMan.get(i).pNum;j++){
					sendFile(new File(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().replaceAll("\\\\","/").lastIndexOf("/"))+"/$temp/"+pMan.get(i).fName+"."+String.format("%07d",j)));
				}
			}
			System.out.println(pMan.size());
		
		}
	}
}
