import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexUtility {
	public static enum  Boundary{FLOAT,WORDS,ISOLATED}
	
	public static final char[] regexMetaCharacters=new char[]{'<','(','[','{','\\','^','-','=','$','!','|',']','}',')','?','*','+','.','>'};
	///////////////////////////SOME STANDARD REGEX EXPRESSIONS///////////////////////////////////////////
	public static final String FLOAT="\\d?\\.?\\d"; //Includes Integers as well
	public static final String WORDS="\\w?";  //RELUCTANT
	public static final String ISOLATED="\\b";
	
	

	
	
	
	public static boolean hasRegexMetaCharacter(char c){
		for(char x:regexMetaCharacters){
			if(x==c){
				return true;
			}
		}
		return false;
		
		
	}
	
	public static String getRegexVersion(String str){
		String regexVersion="";
		for(int i=0;i<str.length();i++){
			if(hasRegexMetaCharacter(str.charAt(i))){
				regexVersion=regexVersion+"\\"+str.charAt(i);
			}
			else{
				regexVersion+=str.charAt(i);
			}
		}
		return regexVersion;
		
		
		
	}
	public static Pattern getRegexToMatchElement(String[] elementArr, boolean allowBackReference){
	     String regex="\"";
	     String O=allowBackReference?"(":"(?:";
	     String C=")";
	     String OR="|";
	     regex+=O+elementArr[0]+C;
    	 
	     for(int i=1;i<elementArr.length;i++){
	    	 regex+=OR+O+elementArr[i]+C;
	     }
	     regex+="\"";
	     Pattern p=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
	     return p;
	 
	}
	public static Pattern getGeneralRegex(String[] elementArr,Boundary left,Boundary right, boolean allowBackReference){
	     String regex="\"";
	     String O=allowBackReference?"(":"(?:";
	     String C=")";
	     String OR="|";
	     regex+=O+elementArr[0]+C;
   	 
	     for(int i=1;i<elementArr.length;i++){
	    	 regex+=OR+O+elementArr[i]+C;
	     }
	     regex+="\"";
	     Pattern p=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
	     return p;
	 
	}
	
	
	public static String applyRegexSuccessivelyAndReplace(Pattern[] patternArr,String input,String replacement){
	    String output=input;
		Matcher m;
		
		for(Pattern p:patternArr){
		m=p.matcher(output);
		if(m.find()){
			output=m.replaceAll(replacement);
		}
		}
		return output;
	}
	public static String applyRegexAndReplaceSelected(Pattern pattern,String input,String replacement,String[] exceptionsArr){
		HashSet<String> exceptionAllowed=new HashSet<String>();
		for(String x:exceptionsArr){
			exceptionAllowed.add(x);
		}
		String output=input;
		Matcher m;
		m=pattern.matcher(output);
		int start=0;
		//System.out.println("THe pattern is "+pattern.toString());
		while(m.find(start)){
			int j=m.end();
			int i=m.start();
			if(!exceptionAllowed.contains(m.group())){
				
				output=output.substring(0, i)+replacement+output.substring(j);
				//System.out.format("Found :%s: New String is >>%s<<\n",m.group(),output);
				m=pattern.matcher(output);
				start=i+replacement.length();
				continue;
			}
			start=j;
		}
		
		
		return output;
	}
	
	public static String removeRepeat(String input){
		String output=input;
		Matcher m=Regex.repeatPattern.matcher(input);
		while(m.find()){
			output=output.replaceAll(RegexUtility.getRegexVersion(m.group()), m.group(1)); 
			//The function uses some kind of regular expression and thus metacharacters needs to be escaped.	 
			
		}
		return output;
		
	}
	
	
	

}
