package general;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CFileReader {
	public static String[] s;
	public CFileReader(File f) throws IOException{
		readFile(f.getAbsolutePath());
	}
	private static void readFile(String path) throws IOException{
		FileReader fr;
		try {
			fr = new FileReader(path);
		} catch (FileNotFoundException e) {
			PrintWriter pr = new PrintWriter(path, "UTF-8");
			pr.close();
			fr = new FileReader(path);
		}
		BufferedReader tr = new BufferedReader(fr);
		ArrayList<String> f = new ArrayList<String>();
		String temp;
		while((temp = tr.readLine())!=null){
			f.add(temp);
		}
		s = new String[f.size()];
		tr.close();
	}
	public static String getLine(int i){
		return s[i];
	}
	public static String[] getText(){
		return s;
	}
	public static void updateLine(int i, String S){
		s[i]=S;
	}
	public static void updateText(String[] S){
		s=S;
	}
	public static void main(String[] args) throws IOException{
		CFileReader f1 = new CFileReader(new File("C:/Users/Jordan/Documents/GitHub/FookinFinalProjectM8/ezpz.md"));
	}
}
