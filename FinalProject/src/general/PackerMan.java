package general;

import java.io.File;
import static general.CheckSum.cSum;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public final class PackerMan {
	public File fLoc;
	public int pNum;
	public int pSize;
	public int vNum;
	public String fName;
	public String[] cSums;
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
		fr.addLine(""+((int)(1+Math.ceil((int)fLoc.length()/pSize))));
		fr.addLine(""+pSize);
		fr.addLine(""+cSum(fLoc.getAbsolutePath()));
		String temp ="";
		FileSplit fs = new FileSplit(fLoc,pSize);
		//System.out.println(fLoc.getAbsolutePath());
		File tempF = new File(fLoc.getAbsolutePath().substring(0, fLoc.getAbsolutePath().replaceAll("\\\\","/").lastIndexOf("/"))+"/$temp");
		//Files.createDirectories(Paths.get(fLoc.getParent()+"/$temp"));
		//System.out.println(tempF.list().length);
		cSums = new String[tempF.list().length];
		pNum= (int)Math.ceil(fLoc.length()/pSize);
		for(int i=0;i<pNum;i++){
			cSums[i]=cSum(tempF.getAbsolutePath()+"/"+fName+"."+String.format("%07d",i));
			temp+=i+":"+cSum(tempF.getAbsolutePath()+"/"+fName+"."+String.format("%07d",i))+"*";
		}
		fr.addLine(temp);
		fr.saveText();
	}
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException{
		PackerMan pm = new PackerMan(new File("C:/Users/jordanpk18/Documents/git/FookinFinalProjectM8/FinalProject/res/test/dank-memes_o_6669985.jpg"),64,0);
	}
}
