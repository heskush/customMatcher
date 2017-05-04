
public class BeginHere {

	
	public static void main(String[] args) {
		
		RTFdataFile a=new RTFdataFile("./watsAppChats/Sanitized2.rtf");
		//a.printExtractedData();
		ExtractOrders.generateConversationsAndProcess(a);
		
		
	}

}
