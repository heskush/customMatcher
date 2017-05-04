import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum type {
	quantity, unit, buffer, none;
}

/*The class always maintains an ORDER format and accepts new tokens while maintaining the format
 * The format in an order consists of 
 * 1.UNIT
 * 2.QUANTITY
 * 3. BUFFER:SOME ITEM DESCRIPTION WHICH CANT BE MORE THAN TWO STRINGS.  e.g. elaichi banana
 * FORMAT RULES:
 * 1. UNIT AND QUANITITY ARE ALWAYS TOGETHER.  
 * 2. ITS NOT POSSIBLE TO HAVE MORE THAN ONE UNIT OR QUANTITY IN ONE ORDER. : FORMAT CLASH
 * 3. ITS NOT POSSIBLE TO HAVE A BUFFER OF MORE THAN 2 WORDS, :FORMAT CLASH.
 * 4. TWO BUFFERS CANT BE SEPARATED. :FORMAT CLASH.
 * 
 * The functions for inserting any of the components e.g. insertUnit(). insertQuantity() provide the functionality similar to a LookAhead
 * in regular expressions in the sense that if the token currently being processed breaks a rule then either the entire order is printed 
 * and the old values are flushed out making way for the new token 
 * OR
 * The entire order is simply flushed and the matcher is set to begin at 1 + the begin position of the previous match.
 * 
 * 
 * This misses the situation when the above format is not necessary e.g. "5 bananas, 7 lemons". 
 * And would give faulty answer in text like, "6 banana tomato 1 kg"
 * POSSIBLE IMPROVEMENT:
 * Maintain a dictionary of item names and add another rule for not having two item names in the same buffer.
 * 
 * 
 * 
 * */




public class Order {
	
	public static Pattern digitPattern = Pattern.compile("(\\d+)");
	public static Pattern fractionPattern = Pattern.compile("(\\d+/\\d+)");
	public static Pattern floatPattern = Pattern.compile("(\\d*\\.\\d+)");

	
	
	
	String quantity;
	String unit;
	String buffer;
	boolean hasQuantity;
	boolean hasUnit;
	boolean hasBuffer;
	boolean completeOrder;
	boolean orderSense;

	int bufferSize;
	int startIndex;
	int currentIndx;
	type lastProcessed;

	{
		hasQuantity = false;
		hasUnit = false;
		hasBuffer = false;
		completeOrder = false;
		orderSense = true;

		bufferSize = 0;
		buffer = "";

	}

	Order(int i) {
		this.startIndex = i;
		this.currentIndx = i;
		lastProcessed = type.none;
	}


	public int processToken(String token) {

		token = getQuantity(token);

		if (!token.isEmpty()) {
			token = getUnit(token);
		}
		if (!token.isEmpty()) {
			insertBuffer(token);
		}
		if (this.orderSense) {
			return 1;

		} else {
			return 2;
		}

	}

	public String getQuantity(String token) {
		Matcher quantityMatcher;
		Matcher fractionMatcher = fractionPattern.matcher(token);
		Matcher floatMatcher = floatPattern.matcher(token);
		Matcher digitMatcher = digitPattern.matcher(token);
		quantityMatcher = digitMatcher;
		if (quantityMatcher.find()) {
			if (fractionMatcher.find()) {
				quantityMatcher = fractionMatcher;
			}
			if (floatMatcher.find()) {
				quantityMatcher = floatMatcher;
			}

			if (quantityMatcher.start() == 0
					| quantityMatcher.end() == token.length()) { // e.g. 1/2kg
																	// kg1/2 1kg
																	// kg1
				insertQuantity(quantityMatcher.group());
				token = quantityMatcher.replaceAll("");
				return token;
			} else { // TOBE DONE:digits surrounded by words on either side.
           
				return token;

			}

		} else {
			if (AuxObjects.cardinalMap.containsKey(token)) {
				insertQuantity(token);
				token = "";
				return token;
			}

		}

		return token;

	}

	public String getUnit(String token) {
		if (AuxObjects.Units.contains(token)) {
			insertUnit(token);
			return "";

		} else {
			return token;
		}

	}

	public void insertQuantity(String quantity) {
		if (this.orderSense) {

			if (this.hasQuantity) { // A quantity already exists.
				encounteredFormatClash();
				return;

			} else {
				if (this.hasUnit & this.lastProcessed != type.unit) { // quantity and unit shouldn't be seperated by a buffer.
                    encounteredFormatClash();
					return;

				} else {

					this.lastProcessed = type.quantity;
					if (quantity == "dozen" | quantity == "dzn"
							| quantity == "dozn" | quantity == "dzen") {
						this.quantity = "1";
						this.hasQuantity = true;
						insertUnit("dozen");
						this.lastProcessed = type.quantity;
						update();
						return;
					} else {
						this.quantity = quantity;
						this.hasQuantity = true;
						this.lastProcessed = type.quantity;
						update();
						return;

					}
				}

			}
		}

	}

	public void insertUnit(String unit) {
		if (this.orderSense) {
			if (this.hasUnit) {
				encounteredFormatClash();
				return;

			} else {
				if (this.hasQuantity & this.lastProcessed != type.quantity) {  // quantity and unit shouldn't be seperated by a buffer.
                   encounteredFormatClash();
                   return;
				} else {
					this.unit = unit;
					this.hasUnit = true;
					this.lastProcessed = type.unit;
					update();
				}
			}
		}
	}

	public void insertBuffer(String buffer) {
		if (this.orderSense) {

			if (buffer.equals("#")) {
				encounteredFiller();
				return;

			}
			if (this.bufferSize == 2) {
				encounteredFormatClash();
				return;
			} else {

				if (this.lastProcessed != type.buffer & this.bufferSize == 1) {
					encounteredFormatClash();
					return;
				}

				else {
					
					this.buffer += " " + buffer;
					this.bufferSize++;
					this.hasBuffer = true;
					this.lastProcessed = type.buffer;
					update();
				}
			}
		}

	}



	public void evaluateOrder() {

		if (hasQuantity & hasUnit & hasBuffer & bufferSize == 2) {
			System.out.println(printEV());
			this.orderSense = false;
			this.currentIndx = this.currentIndx + 1;
		}

	}

	public void update() {
		if (hasQuantity & hasUnit & hasBuffer) {
			completeOrder = true;
		}
		evaluateOrder();

	}

	public String printEV() {
		return ("ORDER->" + buffer + ":" + quantity + ":" + unit);

	}

	
	public void encounteredFormatClash(){
		if (this.completeOrder) { 
			System.out.println(printEV());
			this.orderSense = false;
			
		} else {
			this.orderSense = false;
			this.currentIndx = this.startIndex + 1;
			return;
		}
		
		
	}
	
	public void encounteredFiller(){
		
		if (this.completeOrder) {
			System.out.println(printEV());
			this.orderSense = false;
			this.currentIndx = this.currentIndx + 1;
			return;
		} else {
			this.orderSense = false;
			this.currentIndx = this.startIndex + 1;
			return;
		}
		
	}
	
}
