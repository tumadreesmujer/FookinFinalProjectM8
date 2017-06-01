package general;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class LoadingTracker{
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InterruptedException{
		String s = AutoDetect.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString();
		String dataFile = s.substring(0,s.length()-5)+"/res/test";
		Versioning a = new Versioning(new File(s.substring(0,s.length()-5)+"/res/.versioning.ffpv"));
		AutoDetect testAuto = new AutoDetect(dataFile,a);
		List<File> testautofiles = testAuto.getFiles();
		System.out.println(testautofiles.size());
		
		for (int i = 0; i < testAuto.getFiles().size(); i++){
			System.out.println(testautofiles.get(i));
			
			if (testautofiles.get(i).toString().substring(testautofiles.get(i).toString().length()-7).matches("-?\\d+(\\.\\d+)?")){
				System.out.println("yay it works");
			}
		}
	}
}