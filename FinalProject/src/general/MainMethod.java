package general;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MainMethod {
	private static int pSize=1024*64;
	private static ArrayList<PackerMan> pMan = new ArrayList<PackerMan>();
	public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException, IOException{
		AutoDetect aD = new AutoDetect("res");
		List <File> files = aD.getFiles();
		
		if(files!=null){
			for(int i=0;i<files.size();i++){
				pMan.add(new PackerMan(files.get(i),pSize,0));
			}
			System.out.println(pMan.size());
		
		}
	}
}
