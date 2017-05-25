package general;
import static general.CheckSum.cSum;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public  class AutoDetect {
	static List<File> files = new ArrayList<File>();
	static List<String> cSums = new ArrayList<String>();
	static String dirPath;
	static File f;
	static boolean isKill;
	static int timeInterval = 20;
	static Versioning v;
	
	public AutoDetect(String p, Versioning V) throws InterruptedException, NoSuchAlgorithmException, IOException{
		v=V;
		dirPath = p;
		f = new File(p);
		List<File> temp;
		while(!isKill){
			f = new File(p);
			temp = checkNewFiles();
			if(temp != null){
				for(int i = 0; i < temp.size();i++){
					System.out.println(v.isCurrent(temp.get(i),cSum(temp.get(i).getPath()),0));
					System.out.println(temp.get(i) +" " + cSum(temp.get(i).getPath()));
				}
			}
			//send to VB's code
			TimeUnit.SECONDS.sleep(timeInterval);
		}
	}
	
	public List<File> checkNewFiles() throws NoSuchAlgorithmException, IOException{
		List<File> temp= new ArrayList<File>();
		temp=updateFiles(f,temp);
		for(int i = temp.size()-1;i>=0;i--){
			if(!files.contains(temp.get(i))){
					files.add(temp.get(i));
			}else{
				temp.remove(i);
			}
		}
		return temp.size()==0?null:temp;
		
	}
	
	public List<File> getFiles(){
		return files;		
	}
	
	public static void listFiles(File temp) throws NoSuchAlgorithmException, IOException{
		List<File> F =Arrays.asList(temp.listFiles());
		for(int i = 0; i<F.size();i++){
			if(F.get(i).isFile()){
				System.out.println(v.isCurrent(F.get(i),cSum(F.get(i).getPath()),0));
//				System.out.println(F.get(i) +" " + cSum(F.get(i).getPath()));
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
	
    public static void main(String[] args) throws IOException, InterruptedException, NoSuchAlgorithmException {
    		String s = AutoDetect.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString();
    		String dataFile = s.substring(0,s.length()-5)+"/res/test";
    		Versioning a = new Versioning(new File(s.substring(0,s.length()-5)+"/res/.versioning.ffpv"));
    		AutoDetect testAuto = new AutoDetect(dataFile,a);
		
    	
    	
    }
}
