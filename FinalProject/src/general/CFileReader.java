package general;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CFileReader {
	public CFileReader(File f) throws IOException{
		readFile(f.getAbsolutePath());
	}
	public static String[] readFile(String path) throws IOException{
		FileReader fr;
		try {
			fr = new FileReader(path);
		} catch (FileNotFoundException e) {
			File temp = new File(path);
			fr = new FileReader(temp);
			e.printStackTrace();
		}
		BufferedReader tr = new BufferedReader(fr);
		ArrayList<String> f = new ArrayList<String>();
		String temp;
		while((temp = tr.readLine())!=null){
			f.add(temp);
		}
		String[] tempArr = new String[f.size()];
		tr.close();
		return f.toArray(tempArr);
	}
	public static void main(String[] args) throws IOException{
		CFileReader f1 = new CFileReader(new File("C:/Users/jordanpk18/Documents/git/FookinFinalProjectM8/EZPZ.md"));
	}
}
