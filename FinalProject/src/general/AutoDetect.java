package general;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public  class AutoDetect {
	static List<File> files = new ArrayList<File>();
	static String dirPath;
	static File f;
	static boolean isKill;
	static int timeInterval = 20;
	
	public AutoDetect(String p) throws InterruptedException{
		dirPath = p;
		f = new File(p);
		List<File> temp;
		while(!isKill){
			f = new File(p);
			temp = checkNewFiles();
			if(temp != null){
				for(int i = 0; i < temp.size();i++){
					System.out.println(temp.get(i));
				}
			}
			//send to VB's code
			TimeUnit.SECONDS.sleep(timeInterval);
		}
	}
	
	public List<File> checkNewFiles(){
		List<File> temp= new ArrayList<File>();
		temp=updateFiles(f,temp);
		for(int i = temp.size()-1;i>=0;i--){
			if(!files.contains(temp.get(i)))
				files.add(temp.get(i));
			else
				temp.remove(i);
		}
		return temp.size()==0?null:temp;
	}
	
	public List<File> getFiles(){
		return files;		
	}
	
	public static void listFiles(File temp){
		List<File> F =Arrays.asList(temp.listFiles());
		for(int i = 0; i<F.size();i++){
			if(F.get(i).isFile()){
				System.out.println(F.get(i));
			}
			if(F.get(i).isDirectory()){
				listFiles(F.get(i));				
			}
			
		}
		
	}
	
	public List<File> updateFiles(File F, List<File> AL){
		List<File> temp = (Arrays.asList(F.listFiles()));
		for(int i = 0; i < temp.size();i++){
			//System.out.println(temp.get(i).getPath().replaceAll("\\\\","/"));
			if(temp.get(i).isFile()){
				File tempF =new File( temp.get(i).getPath().replaceAll("\\\\","/"));
				AL.add(tempF);
			}
			if(temp.get(i).isDirectory()){
				AL.addAll(updateFiles(new File( temp.get(i).getPath().replaceAll("\\\\","/")),AL));
			}
			
		}
		return AL;
	}
	
	public void killEverything(){
		isKill=true;
	}
	
    public static void mainAutoDetect(String[] args) throws IOException, InterruptedException {
    		String s = AutoDetect.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString();
    		String dataFile = s.substring(0,s.length()-5)+"/res/test";
    		AutoDetect testAuto = new AutoDetect(dataFile);
		
    	
    	
    }
}
