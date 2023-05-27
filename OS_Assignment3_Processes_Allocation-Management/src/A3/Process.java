package A3;

import java.util.ArrayList;

public class Process 
{
    public String name;
    public int size;
    public boolean allocated = false;
    

    public Process(String n, int s) 
    {
        this.name = n;
        this.size = s;
    }
    
    public static ArrayList<Process> getRuningProcesses(ArrayList<Process> processes) 
    {
    	ArrayList<Process> runningProcesses = new ArrayList<Process>();
        for (Process p : processes)
        {
        	runningProcesses.add(new Process(p.name, p.size));
        }
        return runningProcesses;
    }
	

}


