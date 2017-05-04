import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;


public class AuxObjects {

	
	static HashMap<String,Float>cardinalMap=FileToObject.getCardinalMap(AuxFiles.cardinalMapPathArr);
	static String[] cardinalArr=cardinalMap.keySet().toArray(new String[cardinalMap.size()]);
	
	static{
		final HashMap<String,Float>cardinalMap=FileToObject.getCardinalMap(AuxFiles.cardinalMapPathArr);
		final String[] cardinalArr=cardinalMap.keySet().toArray(new String[cardinalMap.size()]);
	}
	

	
	
	static HashSet<String> extraWords=FileToObject.getGeneralHashSetFromFile(AuxFiles.combinedPath);
	static HashSet<String> Units=new HashSet<String>();

	static{
		
		Units.addAll(Arrays.asList(groceryData.G));
		Units.addAll(Arrays.asList(groceryData.KG));
		Units.add("dozen");
		Units.add("dzn");
		Units.add("dozn");
		Units.add("dzen");
		Units.add("bunch");
		Units.add("bnch");
		Units.add("pack");
		Units.add("packets");
		Units.add("pakets");
		
		
	}
	
	
	//static HashSet<String> 
	
	

}
