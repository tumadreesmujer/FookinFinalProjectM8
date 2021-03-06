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
	
	public AutoDetect(String p) throws InterruptedException, NoSuchAlgorithmException, IOException{
		dirPath = p;
		f = new File(p);
		List<File> temp;
		f = new File(p);
		temp = checkNewFiles();
		/*if(temp != null){
			for(int i = 0; i < temp.size();i++){
				System.out.println(temp.get(i) +" " + cSum(temp.get(i).getPath()));
			}
		}*/
			//send to VB's code
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
	
	
	
	public List<File> updateFiles(File F, List<File> AL){
		List<File> temp = (Arrays.asList(F.listFiles()));
		for(int i = 0; i < temp.size();i++){
			//System.out.println(temp.get(i).getPath().replaceAll("\\\\","/"));
			if(temp.get(i).isFile()){
				
				if(!temp.get(i).getAbsolutePath().substring(temp.get(i).getAbsolutePath().lastIndexOf(".")).equals(".ffmpm")){

					System.out.println(temp.get(i).getName());
					File tempF =new File( temp.get(i).getPath().replaceAll("\\\\","/"));
					AL.add(tempF);
				}
			}
			if(temp.get(i).isDirectory()&&!temp.get(i).getName().equals("$temp")){
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
    		AutoDetect testAuto = new AutoDetect(dataFile);
		
    	
    	
    }
}
