package ProjectPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PriorityScheduling
{
    private final ArrayList<Process> allProcesses=new ArrayList<>();
    private final ArrayList<Process> readyProcesses=new ArrayList<>(); //Holds ready processes
    private final ArrayList<Process> waiting=new ArrayList<>(); //Holds the waiting processes
    private int t=0;
    private int max=100; //Maximum time

    public PriorityScheduling(ArrayList<Process> p)
    {
        for(int i=0;i<p.size();i++) 
        {
        	allProcesses.add(p.get(i)); //Adding to the list of all processes
        	
            waiting.add(p.get(i)); //Adding to the list of waiting processes
            
            waiting.get(i).waitingTime=(-1)*p.get(i).burstTime; //Waiting Time = Turn Around Time - Burst Time = 0, but Lower the number the higher the priority         
            
            waiting.get(i).turnAroundTime=p.get(i).burstTime;
        }
        
        Collections.sort(allProcesses, Comparator.comparing(Process::getArrivalTime)); //Sorting according to arrival time
    }
    
    public void excute()
    {
        while(t<max)
        {
            while(allProcesses.size()>0&&allProcesses.get(0).arrivalTime<=t)
            {
            	
                readyProcesses.add(allProcesses.get(0)); //1st process turn to be executed
                
                allProcesses.remove(0);//Remove from waiting processes
            }
            
            Collections.sort(readyProcesses, Comparator.comparing(Process::getPriority)); //Sort according to priority
            
            
            if(readyProcesses.size()==0)
            {
                break;
            }
            
            int remTime=readyProcesses.get(0).burstTime;
            
            
            if(remTime>1)
            {
            	readyProcesses.get(0).burstTime=remTime-1; //Decrement burst time
            }
            
            else //Process finished execution
            {
            	
            	readyProcesses.get(0).completionTime=t+1;
            	
            	readyProcesses.get(0).waitingTime=(readyProcesses.get(0).completionTime-readyProcesses.get(0).getArrivalTime()+readyProcesses.get(0).waitingTime);
            	
            	readyProcesses.get(0).burstTime=readyProcesses.get(0).turnAroundTime;
            	
            	readyProcesses.get(0).turnAroundTime=readyProcesses.get(0).waitingTime+readyProcesses.get(0).turnAroundTime;
            	
            	readyProcesses.remove(0); //Remove from ready processes
            }
            t++;
        }
        print();
    }
    
    static void executePriority()
    {
        System.out.println("Enter number of process: ");
        int n = main.in.nextInt();
        
        ArrayList<Process> processes = new ArrayList<>();
        
        for (int i = 0; i < n; i++) 
        {
            main.in.nextLine();
            
            System.out.print("Enter name of process "+(i+1)+" : \n");
            String name = main.in.nextLine();
            
            System.out.print("Enter burst time of process "+(i+1)+" : \n");
            int burstTime = main.in.nextInt();
            
            System.out.print("Enter arrival time of process "+(i+1)+" : \n");
            int arrivalTime = main.in.nextInt();
            
            System.out.print("Enter priority of process "+(i+1)+": \n");
            int pn = main.in.nextInt();
            
            processes.add(new Process(name, arrivalTime, burstTime,pn));
        }
        
        System.out.println();
        
        PriorityScheduling p = new PriorityScheduling(processes);
    	
        p.excute();
        
        System.out.println();
    }

    
    
    
    public void print()
    {

    	System.out.println("--------------------------------------------------------------------------------------");
    	System.out.println("Priority scheduling information:"); 
    	System.out.println("--------------------------------------------------------------------------------------");

        System.out.printf("%s %20s %20s %20s %20s %20s %20s\n", "Name",
        		"Burst Time", "Priority","Arrival Time", "Completion Time",
                "TurnAround Time", "Waiting Time");
        
    	int avgWaitingTime = 0;
    	int avgTurnAroundTime = 0;
    	
        
        for(int i=0;i<waiting.size();i++) 
        {
        	
        	System.out.printf("%s %20d %20d %20d %20d %20d %20d\n", waiting.get(i).name, waiting.get(i).burstTime,
        			waiting.get(i).getPriority(),waiting.get(i).getArrivalTime(), waiting.get(i).completionTime,
        			waiting.get(i).turnAroundTime ,waiting.get(i).waitingTime);

            avgWaitingTime += waiting.get(i).waitingTime;
            avgTurnAroundTime += waiting.get(i).turnAroundTime;
        }
        System.out.println("Average waiting time:      " + (double) avgWaitingTime / waiting.size());
        System.out.println("Average turnaround time: " + (double) avgTurnAroundTime / waiting.size());
        System.out.println("--------------------------------------------------------------------------------------");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
