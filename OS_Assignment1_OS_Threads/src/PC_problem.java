//import java.util.Scanner;


public class PC_problem {

    public static void main(String[] args) {
    	
    	

    	GUI gui_obj = new GUI();
	    gui_obj.start();
	    
		int number =  GUI.number;
		producer_c.setNember(number);
		String fileName = GUI.filename;
		consumer_C.setFileName(fileName);
	
		
    	producer_c producer_obj = new producer_c();  //create thread from class producer 
		producer_obj.start();
		System.out.println("producer_obj thread is created ");
	    consumer_C consumer_obj = new consumer_C(producer_obj); // create thread from class consumer 
	    consumer_obj.start();
	    System.out.println("consumer_obj thread is created ");
	    
	    
    	
		
		
	    
        
    }
}