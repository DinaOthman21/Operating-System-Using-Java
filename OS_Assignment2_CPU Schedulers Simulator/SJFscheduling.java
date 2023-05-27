package ProjectPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SJFscheduling //Shortest Job First 
{
	private ArrayList<Process> p; //Array that holds all processes
    private ArrayList<Integer> burstTime = new ArrayList<>(); //Array that holds burst time of all processes
    private ArrayList<Integer> printing = new ArrayList<>();   //AArray that contains information of processes to print 
    private int t; //Current time
    private int contextSwitching; //Context switching time
    private int totalProcesses; //Total number of processes
    private int currentProcess; //Current process index
    private int remaining; //minimum remaining time
    boolean flag = false; //Switch happened or not
    
    public SJFscheduling (ArrayList<Process> p, int contextSwitching) 
    {
        this.p = p;
        this.t = 0; //Current time is initialized to zero
        this.contextSwitching = contextSwitching;
        totalProcesses = p.size();
        Collections.sort(p,Comparator.comparing(Process::getArrivalTime)); //Sorting processes with respect to arrival time
        for (int i = 0; i < totalProcesses; i++)
        {
        	burstTime.add(p.get(i).burstTime);	
        }
        currentProcess = 0;
        remaining = Integer.MAX_VALUE; //Remaining time is initialized to highest value
    }
    
    static void executeSJF()
    {
        System.out.println("Enter number of process:");
        
        int n = main.in.nextInt();
        
        ArrayList<Process> processes = new ArrayList<>();
        
        System.out.print("Enter context switching time: \n");
        
        int contextTime = main.in.nextInt();
        
        for (int i = 0; i < n; i++) 
        {
        	main.in.nextLine(); 
        	
            System.out.print("Enter name of process "+(i+1)+" : \n");
            String name = main.in.nextLine();
            
            System.out.print("Enter burst time of process "+(i+1)+" : \n");
            int burstTime = main.in.nextInt();
            
            System.out.print("Enter arrival time of process "+(i+1)+" : \n");
            int arrivalTime = main.in.nextInt();
            
            processes.add(new Process(name,arrivalTime,burstTime));
        }
        System.out.println();
        
        SJFscheduling sjf = new SJFscheduling(processes,contextTime);
        
        sjf.execute();
        
        System.out.println();
    }
     
    
    public void execute() 
    {
        int counter = 0; //
        
        int prevProcess = 0; //The index of previous process
        
        while (totalProcesses > 0) //While there are processes in the list waiting for execution
        {
            for (int i = 0; i < p.size(); i++) 
            {
            	//If the arrival time less than current time and the process's burst time is greater than zero and less than remaining time
                if (p.get(i).getArrivalTime() <= t && burstTime.get(i) > 0 && burstTime.get(i) < remaining) 
                        
                {
                    remaining = burstTime.get(i);
                    currentProcess = i; 
                    flag = true; //Switching occurred
                }
            }
            if (!flag) //If no switching occurred the time is incremented
            {
                t++;
                continue;
            }
            
            counter++;
            
            if (counter > 1 && prevProcess != currentProcess) //If counter is greater that 1 and the previous process is different from current process
            {                                                 //Context switching ended
                t += contextSwitching; //Current time increases by context switching time
                
                printing.add(prevProcess); //Added to list to be printed
            }
            
            burstTime.set(currentProcess, (burstTime.get(currentProcess)) - 1);
        
            remaining = burstTime.get(currentProcess); //Remaining time is the burst time of current process
            
            if (remaining == 0)
            {
                remaining = Integer.MAX_VALUE;
            }
            
            prevProcess = currentProcess; //Previous process now is replaced bu current process
            
            if (burstTime.get(currentProcess) == 0) 
            {
                totalProcesses--; //Decrementing number of processes
                
                flag = false; //No switch occurred
              
                
                int newWaitingTime = t + 1 - (p.get(currentProcess).burstTime) - (p.get(currentProcess).getArrivalTime()); //calculate new waiting time
                
                if (newWaitingTime > 0)
                {
                    p.get(currentProcess).waitingTime=newWaitingTime; 
                }
                else
                {
                	p.get(currentProcess).waitingTime=0;
                	
                }
                p.get(currentProcess).turnAroundTime=(p.get(currentProcess).waitingTime + p.get(currentProcess).burstTime); //Calculate turn around time
            }
            t++;
        }
        printing.add(prevProcess);
        print(printing);
    }
    
    private void print(ArrayList<Integer> printingOrder) 
    {
    	System.out.println("--------------------------------------------------------------------------------------");
    	System.out.println("SJF sheduling information:");
    	System.out.println("--------------------------------------------------------------------------------------");
 
        int avgWaitingTime = 0;
        int avgTurnAroundTime = 0;
        
        for (int i = 0; i < p.size(); i++) 
        {
            System.out.println(p.get(i));
            
            avgWaitingTime += p.get(i).waitingTime;
            
            avgTurnAroundTime += p.get(i).turnAroundTime;
        }
        
        System.out.println();
        
        System.out.println("Average waiting time: " + (double) avgWaitingTime / p.size()+"\n");
        
        System.out.println("Average turnaround time: " + (double) avgTurnAroundTime / p.size()+"\n");
        
        System.out.println("--------------------------------------------------------------------------------------");
    }

}