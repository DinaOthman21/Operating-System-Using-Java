package A3;
import java.util.ArrayList;

public class Memory 
{
	//Allocates the first free partition in the memory to the process
	public void firstFit(ArrayList<Partitions> partitions, ArrayList<Process> Processes,ArrayList<Process> unallocated_processes) {

		for (Process process : Processes) 
		{
			if (!process.allocated) 
			{

				for (int i = 0; i < partitions.size(); i++) 
				{

					if (partitions.get(i).isEmpty() && partitions.get(i).size >= process.size) 
					{

						partitions.get(i).process=process;
						int remaining_space = partitions.get(i).size - process.size;

						if (remaining_space > 0) 
						{
							Partitions p = new Partitions("New partirion " + partitions.size(), remaining_space);
							partitions.add(i + 1, p);
						}

						partitions.get(i).size = process.size;
						process.allocated = true;
						break;
					}
				}
				if (!process.allocated)
				{
					unallocated_processes.add(process);
				}
			}
		}
	}
	
	//Allocates closest-fitting free partition in the memory to the process
	public void bestFit(ArrayList<Partitions> partitions, ArrayList<Process> Processes,ArrayList<Process> unallocated_processes) {

		for (Process process : Processes) 
		{
			if (!process.allocated) 
			{
				int min_remaing_space = Integer.MAX_VALUE;
				int index = 0;

				for (int i = 0; i < partitions.size(); i++) 
				{

					if (partitions.get(i).isEmpty() && partitions.get(i).size >= process.size)
					{

						int remainingSpace = partitions.get(i).size - process.size;

						if (remainingSpace <= min_remaing_space) 
						{
							min_remaing_space = remainingSpace;
							index = i;
						}
					}
				}
				if (min_remaing_space >= 0)
				{

					partitions.get(index).process=process;
					partitions.get(index).size = process.size;
					process.allocated = true;
					if (min_remaing_space > 0) 
					{
						Partitions p = new Partitions("New partition " + partitions.size(), min_remaing_space);
						partitions.add(index+ 1, p);
					}

				}
				if (!process.allocated)
				{
					unallocated_processes.add(process);
				}
			}
		}
	}
	
	//Allocates largest free partition in the memory to the process
	public void worstFit(ArrayList<Partitions> partitions, ArrayList<Process> Processes,ArrayList<Process> unallocated_processes) 	
	{

		for (Process process : Processes) {
			if (!process.allocated) {
				int max_remaing_space = -1;
				int index = 0;

				for (int i = 0; i < partitions.size(); i++) {

					if (partitions.get(i).isEmpty() && partitions.get(i).size >= process.size) {

						int remainingSpace = partitions.get(i).size - process.size;

						if (remainingSpace >= max_remaing_space) 
						{
							max_remaing_space = remainingSpace;
							index = i;
						}
					}
				}
				if (max_remaing_space >= 0) {

					partitions.get(index).process=process;
					partitions.get(index).size = process.size;
					process.allocated = true;

					if (max_remaing_space > 0) 
					{
						Partitions p = new Partitions("New partition " + partitions.size(), max_remaing_space);
						partitions.add(index + 1, p);
					}

				}
				if (!process.allocated)
				{
					unallocated_processes.add(process);
				}
			}
		}

	}
	
	public void compact(ArrayList<Partitions> partitions, ArrayList<Process> unallocated_processes) 
	{
		int total = 0;
		int parttition_size = partitions.size() - 1;
		
		for (int i = 0; i < partitions.size(); i++) 
		{
			if (partitions.get(i).isEmpty()) 
			{
				total += partitions.get(i).size;
				partitions.remove(i);
				i = 0;
			}
		}
		if (total > 0)
		{
			parttition_size++;
			partitions.add(new Partitions("Partirion " + parttition_size, total));
		}

		for (int j = 0; j < unallocated_processes.size(); j++) 
		{
			Process process = unallocated_processes.get(j);
			for (int i = 0; i < partitions.size(); i++) 
			{

				if (partitions.get(i).isEmpty() && partitions.get(i).size >= process.size) 
				{

					partitions.get(i).process=process;
					int remainingSpace = partitions.get(i).size - process.size;
					unallocated_processes.remove(process);

					if (remainingSpace > 0)
					{
						parttition_size++;
						Partitions p = new Partitions("New partirion " + parttition_size, remainingSpace);
						partitions.add(i + 1, p);
					}

					partitions.get(i).size = process.size;

					process.allocated = true;
					break;
				}
			}

		}

	}

	

	public static void printMemoryInfo(ArrayList<Partitions> partitions, ArrayList<Process> unallocated_processes) 
	{

		System.out.println("Partitions:");
		for (Partitions p : partitions) 
		{
			System.out.print(" " + p.name + " : " + p.size + " KB in process");

			if (p.process != null)
			{
				System.out.println(p.process.name);
			}
			else
			{
				System.out.println("external fragment");
			}
		}
		if (unallocated_processes.size() > 0) 
		{
			System.out.print("Unallocated processes are: ");
			for (Process p : unallocated_processes)
			{
				System.out.println(p.name + ", ");
			}
		}

	}
	
	


}


