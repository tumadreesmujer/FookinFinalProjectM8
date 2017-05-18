package general;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class AutoDetect {
	static ArrayList<File> Files = new ArrayList<File>();
	static String dirPath;
	static File f;
	static boolean isKill;
	
	public AutoDetect(String p) throws InterruptedException{
		dirPath = p;
		f = new File(p);
		ArrayList<File> temp;
		while(!isKill){
			temp = checkNewFiles();
			if(temp != null){
				for(int i = 0; i < temp.size();i++){
					System.out.println(temp.get(i));
				}
			}
			//send to VB's code
			TimeUnit.SECONDS.sleep(10);
		}
	}
	
	public ArrayList<File> checkNewFiles(){
		ArrayList<File> temp = new ArrayList<File>();
		
		return null;
	}
	
	public ArrayList<File> getFiles(){
		return Files;		
	}
	
	public static void listFiles(File temp){
		ArrayList<File> F =new ArrayList<File>(Arrays.asList(temp.listFiles()));
		for(int i = 0; i<F.size();i++){
			if(F.get(i).isFile()){
				System.out.println(F.get(i));
			}
			if(F.get(i).isDirectory()){
				listFiles(F.get(i));				
			}
			
		}
		
	}
	
	public void killEverything(){
		isKill=true;
	}
}
