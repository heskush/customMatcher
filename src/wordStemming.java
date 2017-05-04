import java.util.Arrays;
import java.util.HashSet;

public class wordStemming {

	public static Character[] vowels = { 'a', 'e', 'i', 'o', 'u' };
	public static HashSet<Character> vowelSet=new HashSet<Character>(5);
	static{
		for(Character c: vowels){
			vowelSet.add(c);
		}
		
	}

	public static String stem(String str) {

		try {
			if (str.endsWith("oes")) { // potatoes->potato
				return str.substring(0, str.length() - 2);

			} else if (str.endsWith("ies")) { // chillies->chilly
				return str.substring(0, str.length() - 3) + "y";
			} else if (str.endsWith("es") | str.endsWith("ys")| str.endsWith("os")) {
				return str.substring(0, str.length() - 1);
			} else if (str.endsWith("s")) { // Beans->bean
				//System.out.println("TRYING "+str);
					if (!vowelSet.contains(str.charAt(str.length() - 2)) ) {
						
						return str.substring(0, str.length() - 1);
					}
				

			}
		} catch (IndexOutOfBoundsException e) {
			return str;
		}
		return str;

	}
	

}
