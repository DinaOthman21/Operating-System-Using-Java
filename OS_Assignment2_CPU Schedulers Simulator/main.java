package ProjectPackage;

import java.util.Scanner;

public class main
{
	static Scanner in = new Scanner(System.in);
	static int choice=0;
	public static void main(String[] args) 
	{
		while (choice !=5) 
		{
			System.out.println("Please select sheduler mode: \n");
			System.out.println("1) Shortest Job First. \n");
			System.out.println("2) Round Robin. \n");
			System.out.println("3) Priority. \n");
			System.out.println("4) AG. \n"); // Hate3melo da :)
			System.out.println("5) Exit. \n");
			
			choice = in.nextInt();
			
			if(choice==1)
			{
				SJFscheduling.executeSJF();
			}
			
			else if(choice==2)
			{
				RoundRobinScheduling.executeRoundRobin();
			}
			
			else if(choice==3)
			{
				PriorityScheduling.executePriority();
			}
			//else if(choice==4){}  >>>AG
		}
		System.exit(1);
		

        

	}

}
