package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws Exception{
		String sDate1="31/03/2023";  
	    Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
	    System.out.println(sDate1+"\t"+date1);
	    
	    date1 = new Date();
	    String sDate2 = new SimpleDateFormat("dd/MM/yyyy").format(date1);
	    
	    System.out.println(sDate1+"\t"+date1+"\t" + sDate2);
	    
	    try {
	    	Long.parseLong("cbc");
	    } catch (Exception e) {
	    	System.out.println("Sai roi ban oi!!!!");
		}
	}

}
