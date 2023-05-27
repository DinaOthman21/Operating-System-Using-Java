package ProjectPackage;

public class Process 
{
	
	public String name;
    public int arrivalTime;
    public int burstTime;
    public int waitingTime;
    public int turnAroundTime;
    public int completionTime;
    public int responseTime;
    public int remaining;
    public int priority;

    public Process(){}

    public Process(String name, int arrivalTime,int burstTime)
    {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.responseTime = -1; //Time at which the process gets the CPU for the first time - Arrival time
        this.remaining = burstTime;
    }
    
    public Process(String name, int arrivalTime, int burstTime,int priority)
    {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority=priority;

    }
    
    public String getName()
    {
    	return this.name;
    }
    
    public int getArrivalTime() 
    {
        return arrivalTime;
    }
    
    public int getPriority() 
    {
        return priority;
    }


    @Override
    public String toString() 
    {
        return name + " :  " + " WaitingTime=" + waitingTime + ",  turnaroundTime=" + turnAroundTime;
    }
	
	

}
