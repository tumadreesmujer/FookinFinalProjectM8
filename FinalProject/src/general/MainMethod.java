package general;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MainMethod {
	private static int pSize=1024*1024;
	private static String ip = "192.168.1.3";
	private static int tPort=4444;
	private static int fPort=4445;
	private static ArrayList<PackerMan> pMan = new ArrayList<PackerMan>();
	public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException, IOException{
		AutoDetect aD = new AutoDetect("res");
		List <File> files = aD.getFiles();
		
		if(files!=null){
			System.out.println(files.size());
			for(int i=0;i<files.size();i++){
				pMan.add(new PackerMan(files.get(i),pSize,0));
		    	
		        String tempStr =pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().lastIndexOf(".")).replaceAll("\\\\","/")+".ffmpm";
		        tempStr = tempStr.substring(tempStr.lastIndexOf("/res")+1);
				Client.sendText(tempStr,ip, tPort);
				EncryptOrDecrypt.encrypt("default_key",new File(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().lastIndexOf("."))+".ffmpm"),new File(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().lastIndexOf("."))+".ffmpm"));
				Client.sendFile(new File(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().lastIndexOf("."))+".ffmpm"),ip, fPort);
				System.out.println(pMan.get(i).pNum);
				for(int j=0;j<pMan.get(i).pNum+1;j++){
					String temp2Str=pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().replaceAll("\\\\","/").lastIndexOf("/"))+"/$temp/"+pMan.get(i).fName+"."+String.format("%07d",j);
					temp2Str = temp2Str.substring(temp2Str.lastIndexOf("res")).replaceAll("\\\\","/");
					for(int k=0;k<temp2Str.length();k++){
						if(temp2Str.substring(k,k+1).equals("\\"))
							temp2Str=temp2Str.substring(0,k)+"/"+temp2Str.substring(k+1);
					}
					System.out.println(temp2Str);
					Client.sendText(temp2Str,ip, tPort);
					EncryptOrDecrypt.encrypt("default_key", new File(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().replaceAll("\\\\","/").lastIndexOf("/"))+"/$temp/"+pMan.get(i).fName+"."+String.format("%07d",j)), new File(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().replaceAll("\\\\","/").lastIndexOf("/"))+"/$temp/"+pMan.get(i).fName+"."+String.format("%07d",j)));
					Client.sendFile(new File(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().replaceAll("\\\\","/").lastIndexOf("/"))+"/$temp/"+pMan.get(i).fName+"."+String.format("%07d",j)),ip, fPort);
					//sendFile(new File(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().replaceAll("\\\\","/").lastIndexOf("/"))+"/$temp/"+pMan.get(i).fName+"."+String.format("%07d",j)));
				}
				//sendFile(new File(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().lastIndexOf("."))+".ffmpm" ));
				/*for(int j=0;j<pMan.get(i).pNum;j++){
					sendFile(new File(pMan.get(i).fLoc.getAbsolutePath().substring(0, pMan.get(i).fLoc.getAbsolutePath().replaceAll("\\\\","/").lastIndexOf("/"))+"/$temp/"+pMan.get(i).fName+"."+String.format("%07d",j)));
				}*/
			}
			Client.sendText("!!done", ip, tPort);
			System.out.println(pMan.size());
		
		}
	}
}
