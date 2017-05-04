import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Regex {
	
	
///////////////////////////////////////////////////REGEX FOR RTF SPECIFIC FORMAT  //////////////////////////////////////////////////////////////
	static String REGEXrtfCommand= "\\\\[a-z]+(-?[0-9])? ?";  
    static Pattern rtfCommandPattern =Pattern.compile(REGEXrtfCommand);
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
//////////////////////////////////////////////REGEX FOR WATSAPP SPECIFIC FORMAT////////////////////////////////////////////
    static String REGEXDate= "(?:[0-9]?[0-9])?/\\d{1,2}/\\d{4}";
    static String REGEXBetweenDateTime=", ";
	static String REGEXTime="\\d{1,2}:\\d{1,2} (?:A|P)M";
	static String REGEXBetweenTimeName=" \\- ";
	
	static String REGEXPromptCustomer=REGEXDate+REGEXBetweenDateTime+REGEXTime+REGEXBetweenTimeName+"(?!Host)(.*?):"; //Reluctant Approach,Name stored in -Group 3
	static Pattern PromptCustomerPattern=Pattern.compile(REGEXPromptCustomer);
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
//////////////////////////////////////////////REGEX FOR THINGS TO REMOVE//////////////////////////////////////////////
	static String REGEXConsecutiveRepeat="([^0-9])\\1+";
	static Pattern repeatPattern=Pattern.compile(REGEXConsecutiveRepeat);

	
	
	static String REGEXSymbol_1="[\\W&&\\S&&[^\\./]]"; //All symbols except . for floats e.g 4.5 kg
	static Pattern Symbol_1Pattern=Pattern.compile(REGEXSymbol_1);
	
	static String REGEXSymbol_2="\\.(?!\\d)"; //Preserve only 4.5 or .5
	static Pattern Symbol_2Pattern=Pattern.compile(REGEXSymbol_2);
	
   static String REGEXOneCharacterWide="(\\b[^kg0-9/]\\b)";
   static Pattern OneCharacterWidePattern=Pattern.compile(REGEXOneCharacterWide);
	
	static Pattern[] removePatternArr= new Pattern[]{Symbol_1Pattern,Symbol_2Pattern,OneCharacterWidePattern};
	
	
	static Pattern twoCharacterPattern=Pattern.compile("\\b[a-z][a-z]\\b");
	static String [] twoCharacterExceptions=new String[]{"kg","gr","gm"}; //Two character instances which are useful.
	
	// The best way to identify exceptions for three and four character patterns is to see if any of the item names in the 
	// dictionary(if already has size greater than 4) have an edit distance version( within 2 units) or Soundex version(similar sounding words) 
	// of lenght 3 or 4. For e.g gourd-> gord (1 edit distance).
	
	
	static Pattern threeCharacterPattern=Pattern.compile("\\b[a-z][a-z][a-z]\\b");
	static String [] threeCharacterExceptions=new String[]{"kgs","grm","gms","cob","red","dzn"};//Three character instances which are useful.
	
	
	
	
	
	static Pattern fourCharacterPattern=Pattern.compile("\\b[a-z][a-z][a-z][a-z]\\b");
	static String [] fourCharacterExceptions=new String[]{"kilo","gram","grms","corn","dozn","dzen"};
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	
////////////////////////////////////////////// REGEX FOR  TOKENS//////////////////////////////////////////////////////////
/*There are 4 Major Tokens that need to be extracted
 * 1. MAIN ITEM NAME:
 *    Since all the possible items available can't be more than a thousand or so in the worst case. 
 *    It's reasonable to create a dictionary for not only the item names but also for all their combinations that are possible
 *    within 2 edit distances which can help in quick identification of this token.   
 *     
 * 2. SUB-TYPE OF THE MAIN ITEM:
 *   e.g  "Indian" Apples, "Kashmiri" Apples, "Large" Melon, "Red" Pepper, "Brown" tomatoe. 
 *    
 *    To identify them either create a dictionary for the most common ones. Or simply attach a probality to a word as a 
 *    potential sub-type because of it's proximity to a identified item name or an identified pair of a unit and it's amount.
 *    For high probability cases the token should be passed as a sub-type. 
 *    
 *    
 *    
 * 3. UNIT OF MEASUREMNT:
 *    Main Items could be measured in kilograms, grams, some discrete units like (bunch, pack).
 *    
 *    Due to their limited possibilties, it's easy to create a dictionar for them as well.
 *    
 * 4. QUANTITY OF UNITS
 *    e.g. "1.5" kgs, "3" bunch, "one" large papaya.
 *     
 *    
 *    All cardinal numbers need to be converted to a digit and this can be done in the preprocessing step itself.
 *    
 *    Due to their unique representation, these are the most easy tokens to extract and are the bare minimum in the 
 *    process of identifying the order.
 *    
 *    
 *    
 *    
 *    
 *    Depending on how smart one wants the program to be one can add more and more filters to the code.
 *    For e.g  identifying a "1" "kg" would be enough to know that some main item is also present in the string between two other identified tokens
 *    of either quantity or units. 
 *    e.g.  1 kg[X1] Aloo 2kg[X2] Gourd 150 gms[X3] karela. 
 *    For X2, in all probable cases the corresponding item would be 
 *    between the positions of  X1 and X3 but for X1 the corresponding item has to be X1 and X2, this way we can resolve such conflicts and simply 
 *    print whatever is present in the calculate region and let the final user determine what the item is without having to use intensive
 *    string matching functions.
 *    
 *   
 *    
 *  
 *  
 *  */
	
	
	
	
	
	
	
	static Pattern cardinalNumberPattern;
	static{
		cardinalNumberPattern=RegexUtility.getRegexToMatchElement(AuxObjects.cardinalArr,false);
				
	}
	
	
	
	
	
	
    static String[] KG=new String[]{"k","kg","kgs","kilo","kilos","kilogram","kilograms"};  
    
    static String[]G=new String[]{"g","gm","grm","grms","gram","grams"};
    
    static String REGEX_G="g|(?:gm)|(?:grm)|(?:grms)|(?:gram)|(?:grams)";
    static String REGEX_KG="(?:kg)|(?:kgs)|(?:kilo)|(?:kilos)|(?:kilogram)|(?:kilograms)";
    static Pattern KGpattern=Pattern.compile(REGEX_KG);
    static Pattern Gpattern=Pattern.compile(REGEX_G);
	
	
	
	
	
	public static Pattern getCustomerMessagePattern(String customerName) {
		String REGEXcustomerName=RegexUtility.getRegexVersion(customerName);
		
		//String CustomerPre="(?<="+REGEXDate+REGEXBetween1+REGEXTime+REGEXBetween2+REGEXcustomerName+":)";
		
		
		
		String CustomerPre="(("+REGEXDate+")"+REGEXBetweenDateTime+"("+REGEXTime+")"+REGEXBetweenTimeName+REGEXcustomerName+":)";
		String CustomerPost="(?="+REGEXDate+REGEXBetweenDateTime+REGEXTime+REGEXBetweenTimeName+"("+REGEXcustomerName+"|"+"Host"+"):)";
		
		String REGEXCustomerMessage=CustomerPre+"(.*?)"+CustomerPost; //Reluctant Approach
        Pattern customerMessagePattern=Pattern.compile(REGEXCustomerMessage);
        return customerMessagePattern;

	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	

	
	
	
}
