package general;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TextFileReader {
	public ArrayList<String> s;
	public PrintWriter pr;
	public TextFileReader(File f) throws IOException{
		readFile(f.getAbsolutePath());
	}
	private void readFile(String path) throws IOException{
		FileReader fr;
		try {
			fr = new FileReader(path);
		} catch (FileNotFoundException e) {
			pr = new PrintWriter(path, "UTF-8");
			pr.close();
			fr = new FileReader(path);
		}
		BufferedReader tr = new BufferedReader(fr);
		ArrayList<String> f = new ArrayList<String>();
		String temp;
		while((temp = tr.readLine())!=null){
			f.add(temp);
		}
		tr.close();
	}
	public String getLine(int i){
		return s.get(i);
	}
	public ArrayList<String> getText(){
		return s;
	}
	public void updateLine(int i, String S){
		s.set(i, S);
	}
	public void addLine(int i, String S){
		s.add(i, S);
	}
	public void updateText(ArrayList<String> S){
		s=S;	
	}
	public void saveText(){
		for(int i=0;i<s.size();i++)
			pr.println(s.get(i));
		pr.close();
	}
	/*public static void main(String[] args) throws IOException{
		TextReader f1 = new TextReader(new File("C:/Users/Jordan/Documents/GitHub/FookinFinalProjectM8/ezpz.md"));
	}*/
}
