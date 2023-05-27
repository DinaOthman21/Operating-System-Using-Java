 package A3;
import java.util.ArrayList;
import java.util.Scanner;

public class main 
{
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		
		int policy;
		
		int processes_no;
		int process_size;
		int partitions_no;
		int partition_size;
		
		String process_name;
		String partition_name ;
		
		ArrayList<Process> processes = new ArrayList<>();
		ArrayList<Partitions> partitions = new ArrayList<>();
		
		System.out.println("Enter Number Of Partitions:  ");
		partitions_no = scanner.nextInt();
		
		for (int i = 0; i < partitions_no; i++) 
		{
			System.out.print("Name of partition no. " + (i) + ":  ");
			partition_name = scanner.next();

			System.out.print("Size of partition no. " + (i) + ":  ");
			partition_size = scanner.nextInt();
			
			partitions.add(new Partitions(partition_name, partition_size));
		}
		
		System.out.println(" ");
		
		System.out.println("Number of processes:");
		processes_no = scanner.nextInt();
		
		for (int i = 0; i < processes_no; i++)
		{
			System.out.print("Name of process no. " + (i+1) + ":  ");
			process_name = scanner.next();
			
			System.out.print("Size of process no. " + (i+1) + ":  ");
			process_size = scanner.nextInt();
			
			processes.add(new Process(process_name, process_size));
		}
		
		System.out.println(" ");
		
		
		do {
			
			ArrayList<Partitions> running_partitions = new ArrayList<>();
			running_partitions = Partitions.getRuningPartitions(partitions);
			
			ArrayList<Process> runingProcesses = new ArrayList<>();
			runingProcesses = Process.getRuningProcesses(processes);
			
			ArrayList<Process> unallocated_processes = new ArrayList<>();

			System.out.println("1.First-Fit");
			System.out.println("2.Best-Fit");
			System.out.println("3.Worst-Fit");
			System.out.println("4.Exit");

			policy = scanner.nextInt();
			Memory memory = new Memory();

			switch (policy) 
			{
			//First fit
			case 1:
				memory.firstFit(running_partitions, runingProcesses, unallocated_processes);
				memory.printMemoryInfo(running_partitions, unallocated_processes);

			break;

			case 2: //Best fit
				memory.bestFit(running_partitions, runingProcesses, unallocated_processes);
				memory.printMemoryInfo(running_partitions, unallocated_processes);

				break;

			case 3: //Worst fit
				memory.worstFit(running_partitions, runingProcesses, unallocated_processes);
				memory.printMemoryInfo(running_partitions, unallocated_processes);

				break;
				
			case 4: //Exit
				break;
			default:
				System.out.println("Select correct policy");
			}

			if (policy != 4) 
			{
				System.out.println("Compact memory?");
				System.out.println("1.yes");
				System.out.println("2.No");
				int policy2 = scanner.nextInt();
				
				if (policy2 == 1) 
				{
					memory.compact(running_partitions, unallocated_processes);
					memory.printMemoryInfo(running_partitions, unallocated_processes);
				}
			}
		} while (policy != 0);

	}

	
	




}
