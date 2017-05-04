import java.util.regex.Pattern;
import java.util.*;




public class ExtractOrders {


	public static void generateConversationsAndProcess(RTFdataFile a){
	/*This functions clubs all the messages together into a single conversation.(messages that sequentially fall within a window of ten minutes)
	 * And then call a function to extract orders from the individual conversations.
	 * */
	int n=a.customerMessages.size();
	System.out.format("PRINTING ORDER FOR: %s\n",a.customerName);
	 Iterator<CustomerMessage> i=a.customerMessages.iterator();
	
	 if(i.hasNext()){
				CustomerMessage currentCM=i.next();
				String completeString=currentCM.message;
				TimeStamp previousTime=currentCM.timeStamp;
				while(i.hasNext()){
					//System.out.println("THe Complete String "+completeString);
					currentCM=i.next();
					String currentMsg=currentCM.message;
					TimeStamp currentTime=currentCM.timeStamp;
					if(TimeStamp.sameConversation(previousTime, currentTime, 10)){
						completeString=completeString+" "+currentMsg;
						previousTime=currentTime;
					}
					else{
						evaluateConversation(completeString);
						completeString=currentMsg;
						previousTime=currentTime;
                    }
				
					
					
				}
				if(!completeString.isEmpty()){
					evaluateConversation(completeString);
					
					
				}
	}

		
		
	}
	
	

	
	public static void evaluateConversation(String str){
		/*Runs through the string array sequentially consuming each token and when the tokens collected so far match the general pattern of 
		 * a order : (Units+Quantity+Item)  it prints them, in some cases e.g. two units encountered in a row  without any item or quantity the function reverts back to
		 * the previous stable state.*/
		//System.out.println("----SESSION BEGINS----");
		//System.out.println("COMPLETE CHAT:"+str);
		Order o=new Order(0);
		int orderState=1;
		String token;
		String [] b=str.split(" ");
		int i=0;
		while(i<b.length){
			if(orderState==0){ //Need to initiate a new order.
				o=new Order(i);
			
			}
			else if(orderState==2){  //Need to start at a new point.
				o=new Order(o.currentIndx);
				i=o.currentIndx;
			}
			token=b[i];
			o.currentIndx=i;
			orderState=o.processToken(token);
			i++;
			
			
		}
		
		
		System.out.println();
		
	//System.out.println("----SESSION ENDS----");
	}

}
