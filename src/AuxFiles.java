import java.nio.file.Path;
import java.nio.file.Paths;


public class AuxFiles {
	
  /////////////////////////////////////////////////FILTER OUT WORDS///////////////////////////////////////////////
	
	                                            //PREPOSITIONS
	static String prepositionsFileAddress="./dataForDictionary/partsOfSpeech/prepositions";
	static Path prepositionsPath=Paths.get(prepositionsFileAddress);
	
												//VERBS
	static String verbsFileAddress="./dataForDictionary/partsOfSpeech/verbs";
	static Path verbsPath=Paths.get(verbsFileAddress);
	
	
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
	
	static Path[] combinedArr=new Path[]{prepositionsPath,verbsPath,timePath,questionsPath,greetingsPath,moneyPath,generalPath};
	
	
	
	static String combinedFileAddress="./dataForDictionary/partsOfSpeech/combined";
	static Path combinedPath=Paths.get(combinedFileAddress);
	
//	static{
//	
//	FileUtility.combineFiles(combinedArr, combinedPath.toFile());
//	}
//	
	
	
	     
	
	
	
	
	
	
	///////////////////////////////////////////////// FILTER IN WORDS///////////////////////////////////////////
	

                                                  //CARDINAL WORDS
	static String[] cardinalMapFilesAddressArr={"./dataForDictionary/quantity/cardinalFractions",
									            "./dataForDictionary/quantity/cardinalNumbers"};
	
	
	static Path[] cardinalMapPathArr=new Path[cardinalMapFilesAddressArr.length];
	
	
	static{
		
		for(int i=0;i<cardinalMapFilesAddressArr.length;i++){
			cardinalMapPathArr[i]=Paths.get(cardinalMapFilesAddressArr[i]);
		}
	
			
	}
	   
	      								      //SPICES
	
	static String spicesFileAddress="./dataForDictionary/spices";
	static Path spicesPath=Paths.get(spicesFileAddress);
	
	
	static String foodFileAddress="./dataForDictionary/food";
	static Path foodPath=Paths.get(foodFileAddress);
	
	static String newfoodFileAddress="./dataForDictionary/foodNew";
	static Path newfoodPath=Paths.get(newfoodFileAddress);
	
	
	

}
