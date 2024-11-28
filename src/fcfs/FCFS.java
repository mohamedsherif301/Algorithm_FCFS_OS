/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */



package fcfs;

import java.util.Scanner;


class Process {
    int processId;
    int burstTime;
    int waitingTime;
    int turnAroundTime;

    public Process(int processId, int burstTime) {
        this.processId = processId;
        this.burstTime = burstTime;
    }
}
public class FCFS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        Process[] processes = new Process[n];
        

        // Input process details
        OpertionInput(n,processes,scanner);
        

        // Calculate waiting time and turnaround time
        calculateTimes(processes);

        // Display process details
        displayProcessDetails(processes);

        scanner.close();
    }
    public static void OpertionInput(int n,Process[] processes,Scanner scanner){
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Burst Time for Process " + (i + 1) + ": ");
            int burstTime = scanner.nextInt();
            processes[i] = new Process(i + 1, burstTime);
        }
    }

    private static void calculateTimes(Process[] processes) {
        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;

        // Calculate waiting time for each process
        processes[0].waitingTime = 0; // First process has no waiting time
        for (int i = 1; i < processes.length; i++) {
            processes[i].waitingTime = processes[i - 1].waitingTime + processes[i - 1].burstTime;
        }

        // Calculate turnaround time for each process
        for (Process process : processes) {
            process.turnAroundTime = process.waitingTime + process.burstTime;
        }
    }

    private static void displayProcessDetails(Process[] processes) {
        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;

        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        for (Process process : processes) {
            System.out.println(process.processId + "\t\t" + process.burstTime + "\t\t" +
                    process.waitingTime + "\t\t" + process.turnAroundTime);

            totalWaitingTime += process.waitingTime;
            totalTurnAroundTime += process.turnAroundTime;
        }

        // Display average times
        System.out.println("\nAverage Waiting Time: " + (double) totalWaitingTime / processes.length);
        System.out.println("Average Turnaround Time: " + (double) totalTurnAroundTime / processes.length);
    }
}

