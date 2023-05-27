import java.util.ArrayList;
import java.util.List;

public class producer_c extends Thread {

    private static int Queue_size  ;
    static int counter_nums = 0;
    static List<String> Queue = new ArrayList<>();
    int lower_bound = 0;
    int higher_bound = Queue_size; 
    
    static void setNember(int num) {
    	 Queue_size = num;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
            	produce();
            }
        } catch (Exception exp) {
        }
    }

    
    
    
    private synchronized void produce()  throws Exception { 
        while (Queue.size() == Queue_size) {
            // The queue is full and No more can be produced
				wait();		
        }
        
        
        // for determine the prime numbers        
        while (lower_bound < higher_bound & !(Queue.size() == Queue_size))
        {
            boolean flag = false;
            for(int i = 2; i <= lower_bound/2; ++i) { // condition for non-prime number
                   if(lower_bound % i == 0) {
                    flag = true;
                    break;
                }
            }

            if (!flag && lower_bound != 0 && lower_bound != 1) {
            	Integer y = new Integer(lower_bound);
            	String new_num = y.toString();
            	Queue.add(new_num); 
            	counter_nums++;
            }
            ++lower_bound;
           }
        notify();
    }
    

    
    public synchronized String consume() throws Exception {
        notify();
        while (Queue.isEmpty()) {
            wait();
        }
        String num = Queue.get(0);  
        System.out.println("In function consume and return value from  Queue.get(0) = " + num);
        Queue.remove(num);
        return num;
    }
}