import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.TreeSet;


public class isolatedFileEvents {
	

    //PREPOSITIONS
static String prepositionsFileAddress="./dataForDictionary/partsOfSpeech/prepositions";
static Path prepositionsPath=Paths.get(prepositionsFileAddress);

	//VERBS
static String verbsFileAddress="./dataForDictionary/partsOfSpeech/verbs";
static Path verbsPath=Paths.get(verbsFileAddress);



//ADVERBS
static String adverbsFileAddress="./dataForDictionary/partsOfSpeech/adverbs";
static Path adverbsPath=Paths.get(adverbsFileAddress);




	//TIME-BASED

static String timeFileAddress="./dataForDictionary/partsOfSpeech/timeBased";
static Path timePath=Paths.get(timeFileAddress);

     //QUESTIONS

static String questionsFileAddress="./dataForDictionary/partsOfSpeech/question";
static Path questionsPath=Paths.get(questionsFileAddress);

	//GENERAL

static String generalFileAddress="./dataForDictionary/partsOfSpeech/general";
static Path generalPath=Paths.get(generalFileAddress);

		//GREETINGS
static String greetingsFileAddress="./dataForDictionary/partsOfSpeech/greetings";
static Path greetingsPath=Paths.get(greetingsFileAddress);

		//MONEY
static String moneyFileAddress="./dataForDictionary/partsOfSpeech/money";
static Path moneyPath=Paths.get(moneyFileAddress);

    //COMBINED

static Path[] combinedArr=new Path[]{prepositionsPath,verbsPath,adverbsPath,timePath,questionsPath,greetingsPath,moneyPath,generalPath};

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//SORT AND UNIQUE AND PREPROCESS EVERY FILE : PREPROCESS INCLUDES REMOVING REPEAT AND STEMMING WORDS.
	
	
public static void sortAndUniqueAndPreProcessFiles(Path[] pathArr){
		
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
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		
		sortAndUniqueAndPreProcessFiles(combinedArr);
	    FileUtility.combineFiles(combinedArr, new File("./dataForDictionary/partsOfSpeech/combined"));
		
		

	}

}
