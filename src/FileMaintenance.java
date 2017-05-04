import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;




public class FileMaintenance {


	
	
	
	public static void sortAndUniqueAndPreProcessFiles(Path[] pathArr){  //PREPROCESS INCLUDES REMOVING REPEAT AND STEMMING WORDS.
		
	    for(Path p:pathArr){
		
		List<String> a=FileUtility.readFileAsStringList(p.toFile());
		TreeSet<String> h=new TreeSet<String>();
	    for(String x:a){
			x=RegexUtility.removeRepeat(x);
			x=wordStemming.stem(x);
			x=x.toLowerCase().trim();
			if(x.length()>3){
				h.add(x);
			}
		}
		String [] b=h.toArray(new String[0]);
		FileUtility.writeArrayToFile(b, p.toFile(), StandardOpenOption.TRUNCATE_EXISTING);
	}
}
	
	public static void preprocessFile(Path path){ 
	List<String> a=FileUtility.readFileAsStringList(path.toFile());
	String [] b=a.toArray(new String[0]);
	for(int i=0;i<b.length;i++){
		b[i]=RegexUtility.removeRepeat(b[i]);
	}
	FileUtility.writeArrayToFile(b, path.toFile(), StandardOpenOption.TRUNCATE_EXISTING);
		
		
		
	}
	
	

}
