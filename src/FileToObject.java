import java.io.File;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;






public class FileToObject {

public static HashMap<String,Float> getCardinalMap(Path[] pathArr){
	    HashMap<String,Float> cardinalMap =new HashMap<String,Float>();
		int nFiles=pathArr.length;
		for(Path p:pathArr){
			List<String> PairList=FileUtility.readFileAsStringList(p.toFile());
			for(String pair:PairList){
				String []elementsArr=pair.split(" ");
				cardinalMap.put(elementsArr[0], new Float(elementsArr[1]));
			}
		}
		return cardinalMap;
	}

public static HashSet<String> getGeneralHashSetFromFile(Path filePath){
	HashSet<String> hashSet=new HashSet<String>();
    List<String> a=FileUtility.readFileAsStringList(filePath.toFile());
    for (String x:a){
    	x=x.trim().toLowerCase();
    	x=RegexUtility.removeRepeat(x);
    	if(x.length()>=3){
    		//System.out.println(x);
    		hashSet.add(x);
    	}
    	else continue;
    }
     return hashSet;
	
}

public static TreeSet<String> getGeneralTreeSetFromFile(Path filePath){
	TreeSet<String> treeSet=new TreeSet<String>();
    List<String> a=FileUtility.readFileAsStringList(filePath.toFile());
    for (String x:a){
    	x=x.trim().toLowerCase();
    	x=RegexUtility.removeRepeat(x);
    	if(x.length()>=3){
    		treeSet.add(x);
    	}
    	else continue;
    }
     return treeSet;
	
}







	

}
