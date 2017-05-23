package general;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Versioning {
	TextFileReader v;
	public Versioning(File f) throws IOException{
		v = new TextFileReader(f);
	}
	public void Main(String[] args){
		
	}
	public boolean isCurrent(File f, String h){
		ArrayList<String> temp = v.getText();
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).substring(temp.get(i).indexOf(";")+1).equals(f.getPath().replaceAll("\\\\","/")+" "+h)){
				return true;
			}
			
		}
		return false;
	}
}
