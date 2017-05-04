import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Pattern;


public class groceryData {

    
    static String[] KG=new String[]{"k","kg","kgs","kilo","kilos","kilogram","kilograms"};  //The strings would be compared without case sensitivity.
                            					             
    static String[]G=new String[]{"g","gm","grm","grms","gram","grams"};
    
    static String REGEX_G="\\bg\\b|\\b(gm)\\b|\\b(grm)\\b|\\b(grms)\\b|(?:gram)|(?:grams)";
    static String REGEX_KG="(?:kg)|(?:kgs)|(?:kilo)|(?:kilos)|(?:kilogram)|(?:kilograms)";
    static Pattern KGpattern=Pattern.compile(REGEX_KG);
    static Pattern Gpattern=Pattern.compile(REGEX_G);
    
    		
    
    
    
   

}
