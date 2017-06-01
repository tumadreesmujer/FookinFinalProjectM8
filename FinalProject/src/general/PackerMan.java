package general;

import java.io.File;
import static general.CheckSum.cSum;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class PackerMan {
	private File fLoc;
	private int pSize;
	private int vNum;
	private String fName;
	private TextFileReader fr;
	public PackerMan(File fileLocation, int packetSize, int versionNumber) throws IOException, NoSuchAlgorithmException{
		fLoc = fileLocation;
		pSize=packetSize;
		vNum=versionNumber;
		fName=fileLocation.getName();
		createPackerManFile();
	}
	private void createPackerManFile() throws IOException, NoSuchAlgorithmException{
		fr = new TextFileReader(new File(fLoc.getAbsolutePath().substring(0, fLoc.getAbsolutePath().lastIndexOf("."))+".ffmpm" ));
		fr.clear();
		fr.addLine(fName);
		fr.addLine(""+vNum);
		fr.addLine(""+(Math.ceil(fLoc.length()/pSize)));
		fr.addLine(""+cSum(fLoc.getAbsolutePath()));
		fr.saveText();
	}
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException{
		PackerMan pm = new PackerMan(new File("C:/Users/jordanpk18/Documents/git/FookinFinalProjectM8/FinalProject/res/test/dank-memes_o_6669985.jpg"),1000,0);
	}
}
