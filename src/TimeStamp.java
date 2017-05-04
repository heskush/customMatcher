
    public class TimeStamp {
    	
	public static enum Meridiem {AM,PM}
	
	

	int year,month,day,hour,minute;
	Meridiem meridiem;
	boolean date=false,time=false; //To avoid doing any calculation unless the object has been initialized.
	
	public TimeStamp(String date,String time){
		updateTimeFromString(time);
		updateDateFromString(date);
		
		
		
	}
	
//	The regex checker ensures that the format and the content are valid.There shouldn't be any error in this function. If any look at the regex pattern in regexData.java
	void updateTimeFromString(String time){ 
		time=time.trim();                  
		String[] a=time.split(" ");        
		this.meridiem=(a[1].equals("AM"))?Meridiem.AM:Meridiem.PM;
		String[] b=a[0].split(":");
		this.hour=new Integer(b[0]);
		this.minute=new Integer(b[1]);
		this.time=true;
		
		
		
	}
	void updateDateFromString(String date){
		date=date.trim();
		String[]a=date.split("/");
		int l=a.length;
		this.year=new Integer(a[l-1]);
		this.day=new Integer(a[l-2]);
		this.month=00; //Flag for value not present
		if(!a[l-3].isEmpty()){
			this.month=new Integer(a[l-3]);
			}
		this.date=true;
	}
	void printTimeStamp(){
		if(date & time){
			String s=String.format("%d/%d/%d %d:%d %s",month,day,year,hour,minute,meridiem.toString());
			System.out.println(s);
			
		}
		
		
	}
	String  giveTimeStamp(){
		return String.format("%d/%d/%d %d:%d %s",month,day,year,hour,minute,meridiem.toString());
	}
	
	
	
	public static boolean sameConversation(TimeStamp t1,TimeStamp t2,int gapInMinutes){ //Assumption: gap is not more than 60 minutes
		if(t1.day==t2.day && t1.meridiem==t2.meridiem){
		if(t1.date & t2.time & t2.date & t2.time){
			if(t1.hour==t2.hour){
				if(Math.abs(t1.minute-t2.minute)<=gapInMinutes){
					return true;
				}
				
			}
		}
		}
		return false;
	}
	
	
	
	
	

}
