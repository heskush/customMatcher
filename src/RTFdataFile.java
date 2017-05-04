import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RTFdataFile {
	String fileAddress;
	String customerName;
	ArrayList<CustomerMessage> customerMessages=new ArrayList<CustomerMessage>();
	
	public RTFdataFile(String fileAddress) {
		this.fileAddress=fileAddress;
		String rawData = null;
		try {
			rawData = readRawData(fileAddress); //Read entire file in a string
		} catch (IOException e) {
			System.out.println("Coudln't Read Data File");
			e.printStackTrace();
			
			}
		customerName=getCustomerName(rawData);//Extract Customer Name
        Matcher X=Regex.getCustomerMessagePattern(customerName).matcher(rawData); // Matcher for customer messages.
		while(X.find()){
			TimeStamp t=new TimeStamp(X.group(2),X.group(3));  //Extract TimeStamp
			String message=RegexUtility.removeRepeat(X.group(4).trim().toLowerCase()); //Extract customer message
			message=removeReduncancy(message); //Remove words which just can't be known tokens.
			message=RegexUtility.removeRepeat(message);
			message=removeWords(message);
			
			if(!message.isEmpty())
			{

			customerMessages.add(new CustomerMessage(t,message.trim()));
			}
		}
		
	}
	
		
		//For removing useless symbols, single, double and triple characters which wont't have information Except for a few cases.
	public static String removeReduncancy(String input){
		
		String output=RegexUtility.applyRegexSuccessivelyAndReplace(Regex.removePatternArr, input, " ");
	    output=RegexUtility.applyRegexAndReplaceSelected(Regex.twoCharacterPattern, output, "", Regex.twoCharacterExceptions);
	    output=RegexUtility.applyRegexAndReplaceSelected(Regex.threeCharacterPattern, output, "", Regex.threeCharacterExceptions);
	    
	    
	
		
		return output;
		
		
	}
	
	
	
	
	public String getCustomerName(String rawData){
		Matcher Y=Regex.PromptCustomerPattern.matcher(rawData);
		    if(Y.find()){
		    String name=Y.group(1);
			return name;
		    }
		    return "DEFAULT"; //If the matcher is not able to read any name.
		
	}

	
     
	public static String readRawData(String address) throws IOException{
		
		String rawData=FileUtility.readFileAsOneString(address);
		Matcher m= Regex.rtfCommandPattern.matcher(rawData);
		return m.replaceAll(" ");
	}
	
	
	public  void printExtractedData(){
		System.out.println("PRINTING DATA FOR "+this.customerName.toUpperCase());
		
		for(CustomerMessage x:customerMessages){
			
			
			System.out.format("%-20s: %s\n",x.timeStamp.giveTimeStamp(),x.message);
			
		}
		
		
		
	}
	
	
	public static String removeWords(String str)
	{   
		
		String[] Arr=str.trim().split(" ");
		String s="";
		int wordCount=0;
		int fillerCount=0;
		for (String x:Arr){
			wordCount++;
			x=wordStemming.stem(x);
			if(!AuxObjects.extraWords.contains(x)){
				s+=x+" ";
			}
			else{
				s=s+"#"+" ";
				fillerCount++;
			}
		}
		
		
		return (wordCount==fillerCount)? "":s.trim(); // If it only contains filler then return empty string.
		
		
	}
	





}
    	
