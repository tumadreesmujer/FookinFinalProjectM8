package general;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MainMethod {
	private static int pSize=1024*1024;
	private static String ip;
	private static int tPort=4444;
	private static int fPort=4445;
	private static ArrayList<PackerMan> pMan = new ArrayList<PackerMan>();
	public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException, IOException{
		AutoDetect aD = new AutoDetect("res");
		List <File> files = aD.getFiles();
		
		if(files!=null){
			for(int i=0;i<files.size();i++){
				pMan.add(new PackerMan(files.get(i),pSize,0));
				Client.sendText("res/test/elon-musk-recovered.jpg","192.168.1.3",4444);
		        Client.sendFile(new File("res/test/elon-musk-recovered.jpg"),"192.168.1.3",4445);
		    	
				Client.sendText(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().lastIndexOf("."))+".ffmpm",ip, tPort);
				Client.sendFile(new File(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().lastIndexOf("."))+".ffmpm"),ip, fPort);
				for(int j=0;j<pMan.get(i).pNum;j++){
					Client.sendText(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().replaceAll("\\\\","/").lastIndexOf("/"))+"/$temp/"+pMan.get(i).fName+"."+String.format("%07d",j),ip, tPort);
					Client.sendFile(new File(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().replaceAll("\\\\","/").lastIndexOf("/"))+"/$temp/"+pMan.get(i).fName+"."+String.format("%07d",j)),ip, fPort);
					//sendFile(new File(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().replaceAll("\\\\","/").lastIndexOf("/"))+"/$temp/"+pMan.get(i).fName+"."+String.format("%07d",j)));
				}
				//sendFile(new File(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().lastIndexOf("."))+".ffmpm" ));
				/*for(int j=0;j<pMan.get(i).pNum;j++){
					sendFile(new File(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().replaceAll("\\\\","/").lastIndexOf("/"))+"/$temp/"+pMan.get(i).fName+"."+String.format("%07d",j)));
				}*/
			}
			System.out.println(pMan.size());
		
		}
	}
}
