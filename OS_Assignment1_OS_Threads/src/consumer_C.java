
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//consumer_C
public class consumer_C extends Thread {

    private producer_c producer;
    static String fileName;
	static int largeNumber = 0;
	String s_num;
	int counterForWriting = 0;
	int beforeLast = 0;

    public consumer_C(producer_c producer ) {
        this.producer = producer;
    }

    public static void setFileName(String name) {
	      fileName = name;
	}
    
    @Override
    public void run() // it writing in the file 
    {
        try { 
              
              while (!producer_c.Queue.isEmpty()) {
              	  
            	  String data = producer.consume(); //returned value from function consume
            	  int number =  Integer.parseInt(data); //for convert from string to int 
                  System.out.println("number = "+number + "beforeLast = " + beforeLast);
            	  
            	 
                  //for calculating the largest number 
                  if (number>largeNumber) {largeNumber = number; }
                  
                  
                  //converting number to string 
                  s_num = String.valueOf(number);
                
                if (counterForWriting ==0 )
                {
            	{try {
          	      FileWriter myWriter = new FileWriter(fileName);
          	      myWriter.write(s_num);
          	      myWriter.close();
          	     System.out.println("Successfully wrote to the file.");
          	    } catch (IOException e) {
          	      System.out.println("An error occurred.");
          	      e.printStackTrace();
          	    }
          	}
               counterForWriting = 1;
                }
                
                else {
                	System.out.println("IN else conidtion ");
                	if (beforeLast < number) {
                	try { 
                	
                        BufferedWriter out = new BufferedWriter(
                            new FileWriter(fileName, true));
                    
                        out.write("  ,  ");
                        out.write(s_num);
                        out.close();
                    }
             
                    catch (IOException e) {
                        System.out.println("exception occurred" + e);
                    }
                }
                
                } 
                Thread.sleep(500);
            }
        } catch (Exception exp) { System.out.print("in catchh");
        }
    }
}