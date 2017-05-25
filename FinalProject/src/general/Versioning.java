package general;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Versioning {
	TextFileReader v;
	public Versioning(File f) throws IOException{
		v = new TextFileReader(f);
		
	}/*
	public void Main(String[] args) throws IOException{
		AutoDetect()
		
	}*/
	public int isCurrent(File f, String h, int ver) throws FileNotFoundException, UnsupportedEncodingException{
		ArrayList<String> temp = v.getText();
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).substring(0,temp.get(i).indexOf("*")).equals(f.getPath().replaceAll("\\\\","/"))){
				if(Integer.parseInt(temp.get(i).substring(temp.get(i).indexOf(";")+1))==ver){
					return 0; //current
				}
				if(Integer.parseInt(temp.get(i).substring(temp.get(i).indexOf(";")+1))>ver){
					return 1; //old
				}
				if(Integer.parseInt(temp.get(i).substring(temp.get(1).indexOf(";")+1))<ver){
					temp.add(i, f.getPath().replaceAll("\\\\","/")+"*"+h+";"+ver);
					v.updateText(temp);
					v.saveText();
					return 2; //new
				}
			}
			
		}
		temp.add(f.getPath().replaceAll("\\\\","/")+"*"+h+";"+ver);
		v.updateText(temp);
		v.saveText();
		return -3; //DNE
	}
}
