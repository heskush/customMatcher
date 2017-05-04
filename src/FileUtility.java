import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

/**This class provides utility methods for reading(writing) strings from(to) a file.
 * 
 *
 * @author hemant
 *
 */
public class FileUtility {
	
	/////////////////////////////////////////////////FILE-READING/////////////////////////////////////////
	

	/* Available methods can either read a LINE or read  BYTES.
	 *
	 * A LINE is a String between one newline character (or file beginning) and another newline character(or file ending).
	 * Therefore we won't find any new line characters in a LINE String. For that use Files.readAllBytes method which includes the newline as well.
	 * 
	 * A BYTE is the complete string contained inside a file including the newline characters as well.
	 */ 
	 
	
	
	
	/* List<String> which is actually a reference to a ArrayList object. User has to use a List<String> variable to store the returned object.*/
	
	public static List<String> readFileAsStringList(String fileAddress) {
		try{
		List<String> lines = Files.readAllLines(Paths.get(fileAddress),
				Charset.defaultCharset());
		return lines;
		}catch(IOException e){
			e.printStackTrace();
			return new ArrayList<String>(); 
		}
		
		
		
		}

	
	public static List<String> readFileAsStringList(File fileObject){
		
		try{
		List<String> lines = Files.readAllLines(fileObject.toPath(),
				Charset.defaultCharset());
		return lines;
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("ERROR WHILE READING FILE OBJECT "+ fileObject.getName());
			return new ArrayList<String>(); //Returning an arrayList of zero size.
		}

	}
	
	
	
	

	public static String readFileAsOneString(String fileAddress) {
		String s = "";
		List<String> lines = readFileAsStringList(fileAddress);
		for (String x : lines) {
			s += x;
		}
		return s;
	}

	
	public static String readFileAsOneString(File fileObject) {
		String s = "";
		List<String> lines = readFileAsStringList(fileObject);
		for (String x : lines) {
			s += x;
		}
		return s;
	}
	
	
	///////////////////////////////////////FILE-WRITING///////////////////////////////////////////////////////////////////////
	
	
	public static void writeToFile(String content,String fileName,StandardOpenOption standardOpenOption){
		try{
		Files.write(Paths.get(fileName), content.getBytes(), standardOpenOption);
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public static void writeToFile(String content,File fileObject,StandardOpenOption standardOpenOption){
		try{
		Files.write(fileObject.toPath(), content.getBytes(), standardOpenOption);
		}catch(IOException e){
			e.printStackTrace();
			
		}
	}
	
	public static void writeArrayToFile(String[] stringArr,File fileObject,StandardOpenOption standardOpenOption){
		StringBuilder completeString = new StringBuilder();
		for(String s : stringArr) {
			completeString.append(s);
			completeString.append(System.lineSeparator());
		}
		
		String s=completeString.toString().trim();
		writeToFile(s,fileObject,standardOpenOption);
		
		
		
	}
	
	public static void sortElementsInFile(File fileObject){
		
		
		TreeSet<String> treeSet=FileToObject.getGeneralTreeSetFromFile(fileObject.toPath());
		String[] strArr=treeSet.toArray(new String[0]);
		FileUtility.writeArrayToFile(strArr, fileObject, StandardOpenOption.TRUNCATE_EXISTING);
		
	}
	
	
	public static void combineFiles(Path[] pathArr, File resultFile){
		ArrayList<String> combinedList=new ArrayList<String>();
		for(Path path:pathArr){
			List<String> a=FileUtility.readFileAsStringList(path.toFile());
			combinedList.addAll(a);
		}
		TreeSet<String> h=new TreeSet<String>();
		h.addAll(combinedList);
		FileUtility.writeArrayToFile(h.toArray(new String[0]), resultFile, StandardOpenOption.TRUNCATE_EXISTING);
		
	}
	
	
	
	
	
	
	
	
	

}
