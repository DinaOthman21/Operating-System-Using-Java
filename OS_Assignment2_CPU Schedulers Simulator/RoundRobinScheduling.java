package ProjectPackage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RoundRobinScheduling
{
	ArrayList<Process> processes = new ArrayList<>(); //Array hold all processes
	ArrayList<Process> waiting = new ArrayList<>(); //Array holds processes waiting to be executed 
    ArrayList<Process> cycle = new ArrayList<>();  //Array holds the cycle time of the processes
    ArrayList<Process> done = new ArrayList<>(); //Array holds processes finished
    int contextSwitching; //Context switching time
    int quantum; //Quantum time
    int t = 0; //current time
    
    RoundRobinScheduling(ArrayList<Process> processes, int contextSwitching, int quantum) 
    {
        this.processes = processes;
        this.waiting = processes;
        this.contextSwitching = contextSwitching;
        this.quantum = quantum;
    }
    
    static void executeRoundRobin()
    {
        System.out.println("Enter number of process: ");
        int n = main.in.nextInt();
        
        ArrayList<Process> processes = new ArrayList<>();
        
        System.out.print("Enter quantum: \n");
        int quantum = main.in.nextInt();
        
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
            
            processes.add(new Process(name, arrivalTime, burstTime));
        }
        
        System.out.println();
        RoundRobinScheduling RR = new RoundRobinScheduling(processes, contextTime, quantum);
        
    	System.out.println("--------------------------------------------------------------------------------------");
    	System.out.println("Execution Order:"); 
    	System.out.println("--------------------------------------------------------------------------------------");
    	System.out.println("T"+ " "+ "Name"+ " ");
    	
    	
        while (!RR.finished()) 
        {
            RR.execute();
        }
        System.out.println();
        
    }
    
    boolean finished() //Checks if all processes completed execution
    {
        if( waiting.isEmpty() && cycle.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    boolean isRunning() //If the processes finished is cycling then it's running
    {
        return !cycle.isEmpty();
    }
    
    boolean turn()
    {
        if(waiting.isEmpty()) //If no waiting processes == finished
        {
            return false;
        }
        else
        {
        	return waiting.get(0).getArrivalTime()<=t; //Get the first process if it's arrival time less than current time
        }
        
    }
    
    boolean checkProcess(int i) //Check every process with index i
    {
        Process p = cycle.get(i);
        
        if (p.responseTime== -1) 
        {
            p.responseTime=t; //Response time is the current time
        }
        
        if (p.remaining<= quantum) //Finished execution
        {
            System.out.println(t + " "+ p.name);
            
            t += p.remaining;
            
            p.completionTime=t;
            
            p.waitingTime=(p.completionTime- p.burstTime - p.getArrivalTime());
            
            p.turnAroundTime=(p.burstTime + p.waitingTime);
            
            done.add(p); //Add to list of processes that finished execution
            
            cycle.remove(i); //Remove from cycle list 
            
            //System.out.println(t);
            
            return true;
        } 
        else 
        {
            System.out.println(t + " "+ p.name);
            t+= quantum;
            p.remaining=(p.remaining - quantum);
            cycle.set(i, p); //Update the cycle of the process
        }
        //System.out.println(t);

        return false;
    }
    
    
    void update() 
    {
        while (!waiting.isEmpty() && waiting.get(0).getArrivalTime() <= t)
        {
            cycle.add(waiting.get(0)); //The process's turn to be executed
            
            checkProcess(cycle.size() - 1);
            
            t += contextSwitching;
            
            waiting.remove(0); //Remove process from waiting
        }
    }
    
    void updateCycle() 
    {
        for (int i = 0; i < cycle.size(); i++) 
        {
            if (checkProcess(i))
            {
                i--;
            }
            t += contextSwitching;
        }
    }
    
    void execute() 
    {
        Collections.sort(waiting, Comparator.comparing(Process::getArrivalTime));//Sorting waiting processes according to arrival time
        
        while (!cycle.isEmpty() || !waiting.isEmpty()) 
        {
            update();
            
            if(cycle.isEmpty()&&!waiting.isEmpty())
            {
                t+=1;
            }
            updateCycle();
        }
        Collections.sort(done, Comparator.comparing(Process::getName));//Sorting finished processes according to their name
        print();
    }
    
    void print()
    {
    	System.out.println("--------------------------------------------------------------------------------------");
    	System.out.println("Round Robin sheduling information:"); 
    	System.out.println("--------------------------------------------------------------------------------------");
    	
    	int avgWaitingTime = 0;
    	int avgTurnAroundTime = 0;
    	
        System.out.printf("%s %20s %20s %20s %20s %20s %20s\n", "Name",
        		"Burst Time", "Arrival Time", "Completion Time",
                "TurnAround Time", "Waiting Time", "Response Time");
        
        
        for (Process p : done)
        {
            System.out.printf("%s %20d %20d %20d %20d %20d %20d\n", p.name, p.burstTime,
            		p.getArrivalTime(), p.completionTime,
                    p.turnAroundTime, p.waitingTime, p.responseTime);
            
            avgWaitingTime += p.waitingTime;
            avgTurnAroundTime += p.turnAroundTime;
        }
        
        System.out.println("Average waiting time:    " + (double) avgWaitingTime / done.size());
        
        System.out.println("Average turnaround time: " + (double) avgTurnAroundTime / done.size());
        
        System.out.println("--------------------------------------------------------------------------------------");
    }
    
    
    
    
    
    
    
    
    
    

}
