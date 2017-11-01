//Conor Raftery - G00274094
//Process Scheduler
//Please excuse the messy menu

package ie.gmit.os;

import java.util.*;

public class ProcessRunner {

	public static void main(String args[]) {

		int num_processes = 0;
		int i = 0;
		int option = 0;
		int burstTime = 0;
		int quantum = 0;
		String name = null;


		Scanner in = new Scanner(System.in);

		System.out.println("Please select which scheduling algorithm you want to use:"
				+ " \n1) Shortest Job First \n2) First Come First Serve \n3) Round Robin");
		option = in.nextInt();
		
		if(option < 1 || option > 3){
			System.out.println("Please enter a valid option!!!");
			return;
		}

		System.out.println("Please enter the number of processes:");
		num_processes = in.nextInt();

		process[] myProcesses = new process[num_processes];
		

		for (i = 0; i < myProcesses.length; i++) {

			System.out.println("Please enter the burst time of process " + (i + 1) + ":");
			burstTime = in.nextInt();

			System.out.println("Please enter the name of process " + (i + 1) + ":");
			name = in.next();

			myProcesses[i] = new process(burstTime, name);

		}

		if (option == 1) {

			Arrays.sort(myProcesses);
			for (i = 0; i < myProcesses.length; i++) {
				myProcesses[i].display();
			}

			System.out.println("Average wait time(SJF):");
			averageWaitTimeFcfsSjf(myProcesses, num_processes);

		} else if (option == 2) {

			for (i = 0; i < myProcesses.length; i++) {
				myProcesses[i].display();
			}

			averageWaitTimeFcfsSjf(myProcesses, num_processes);

		} else if (option == 3) {
			System.out.println("Please enter a quantum: ");
			quantum = in.nextInt();

			averageWaitTimeRr(myProcesses, num_processes, quantum);
		} else {
			System.out.println("Please enter a valid option");
		}
	}// main

	public static void averageWaitTimeRr(process[] list, int size, int quantum) {

		int i, j, k, sum = 0;
		float totalWaitTime = 0;
		float totalTTime = 0;
		
		int waitTime[] = new int[size];
		int tTime[] = new int[size];
		int a[] = new int[size];
		
		for (i = 0; i < size; i++) {

		}
		
		for (i = 0; i < size; i++){
			a[i] = list[i].getBurstTime();
		}
		
		for (i = 0; i < size; i++){
			waitTime[i] = 0;
		}
		
		do {
			for (i = 0; i < size; i++) {
				if (list[i].getBurstTime() > quantum) {
					list[i].burstTime -= quantum;
					for (j = 0; j < size; j++) {
						if ((j != i) && (list[j].getBurstTime() != 0)){
							waitTime[j] += quantum;
						}//inner if
					}//First inner for
				}//outer if
				else {
					for (j = 0; j < size; j++) {
						
						if ((j != i) && (list[j].getBurstTime() != 0)){
							waitTime[j] += list[i].getBurstTime();
						}//if
						
					}//Second inner for
					
					list[i].burstTime = 0;
				}//else
			}//Outer For
			
			sum = 0;
			
			for (k = 0; k < size; k++){
				sum = sum + list[k].getBurstTime();
			}
			
		} while (sum != 0);//DoWhile
		
		for (i = 0; i < size; i++)
			tTime[i] = waitTime[i] + a[i];
		System.out.println("process\t\tBT\tWT\tTAT");
		for (i = 0; i < size; i++) {
			System.out.println(list[i].name + (i + 1) + "\t" + a[i] + "\t" + waitTime[i] + "\t" + tTime[i]);
		}
		for (j = 0; j < size; j++) {
			totalWaitTime += waitTime[j];
		}
		for (j = 0; j < size; j++) {
			totalTTime += tTime[j];
		}
		System.out.printf("average waiting time: %.2f" , (totalWaitTime / size));
		System.out.printf("\nAverage turnaround time: %.2f", (totalTTime / size));
		//Adapted from http://www.javaengineeringprograms.com/round-robin-scheduling-algorithm-program-in-java/
		
	}//RoundRobin

	public static void averageWaitTimeFcfsSjf(process[] list, int size) {
		int waitTime = 0;
		double totalWaitTime = 0;
		int tTime = 0;
		double totalTTime = 0;

		//For loop to get totalWaitTime
		for (int i = 1; i < size; i++) {

			waitTime = list[i - 1].getWaitTime() + list[i - 1].getBurstTime();

			list[i].setWaitTime(waitTime);

			totalWaitTime += waitTime;

		}

		//For loop to get TotalTurnaroundTime
		for (int i = 0; i < size; i++) {
			list[i].setWaitTime(tTime);
			tTime += list[i].getBurstTime();

			totalTTime += tTime;

		}

		System.out.println("Average turnaround time: " + totalTTime / size);
		System.out.println("Average wait time: " + totalWaitTime / size);

	}//averageWaitTimeFcfsSjf

}//ProcessRunner
