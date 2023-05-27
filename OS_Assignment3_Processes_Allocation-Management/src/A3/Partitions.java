package A3;
import java.util.ArrayList;
import java.util.Collections;

public class Partitions implements Comparable<Partitions> 
{
	public String name;
	public int size;
	public Process process;
	
	public Partitions(String name, int size) 
	{
		this.name = name;
		this.size = size;
		this.process = null;
	}

	public boolean isEmpty() 
	{
		return this.process == null;
	}
	
	public static ArrayList<Partitions> getRuningPartitions(ArrayList<Partitions> partitions) 
	{
		ArrayList<Partitions> runningPartitions = new ArrayList<Partitions>();
		for (Partitions p : partitions)
		{
			runningPartitions.add(new Partitions(p.name, p.size));
		}
		return runningPartitions;
	}

	@Override 
	public int compareTo(Partitions partition) 
	{
		if (this.size > partition.size)
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}


}
