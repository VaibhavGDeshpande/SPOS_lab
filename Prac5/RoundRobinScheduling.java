package Prac5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Process {
    String name;
    int burstTime;
    int remainingTime;
    int arrivalTime;
    int startTime;
    int finishTime;
    int waitingTime;
    int turnaroundTime;

    Process(String name, int burstTime, int arrivalTime) {
        this.name = name;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.arrivalTime = arrivalTime;
    }
}

class GanttChartEntry {
    String processName;
    int startTime;
    int endTime;

    GanttChartEntry(String processName, int startTime, int endTime) {
        this.processName = processName;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

public class RoundRobinScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of processes
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        Process[] processes = new Process[n];

        // Input processes and their burst times and arrival times
        for (int i = 0; i < n; i++) {
            String name = "P" + (i + 1); // Assign process name as P1, P2, ...
            System.out.print("Enter CPU burst time for " + name + ": ");
            int burstTime = sc.nextInt();
            System.out.print("Enter arrival time for " + name + ": ");
            int arrivalTime = sc.nextInt();
            processes[i] = new Process(name, burstTime, arrivalTime);
        }

        // Input time slice
        System.out.print("Enter the time slice: ");
        int timeSlice = sc.nextInt();

        List<GanttChartEntry> ganttChart = new ArrayList<>();
        int currentTime = 0;
        boolean done;
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;

        do {
            done = true;
            for (int i = 0; i < n; i++) {
                Process process = processes[i];

                // Only process if it has arrived and has remaining time
                if (process.remainingTime > 0 && process.arrivalTime <= currentTime) {
                    done = false;

                    // Calculate the start time for this execution
                    int executionStart = currentTime;

                    // Check if this is the first execution of the process
                    if (process.remainingTime == process.burstTime) {
                        process.startTime = currentTime; // Set start time on first execution
                    }

                    // Determine how much time to execute this process
                    int timeToExecute = Math.min(process.remainingTime, timeSlice);
                    currentTime += timeToExecute;
                    process.remainingTime -= timeToExecute;

                    // If the process has finished, update the finish time
                    if (process.remainingTime == 0) {
                        process.finishTime = currentTime;
                        process.turnaroundTime = process.finishTime - process.arrivalTime;
                        process.waitingTime = process.turnaroundTime - process.burstTime;
                        totalWaitingTime += process.waitingTime;
                        totalTurnaroundTime += process.turnaroundTime;
                    }

                    // Add execution entry to the Gantt chart
                    ganttChart.add(new GanttChartEntry(process.name, executionStart, currentTime));
                }
            }
        } while (!done);

        // Output the results
        System.out.println("\nProcess\tBurst Time\tArrival Time\tStart Time\tFinish Time\tTurnaround Time\tWaiting Time");
        for (Process process : processes) {
            System.out.println(process.name + "\t" + process.burstTime + "\t\t" + process.arrivalTime + "\t\t"
                    + process.startTime + "\t\t" + process.finishTime + "\t\t" + process.turnaroundTime + "\t\t" + process.waitingTime);
        }

        // Output average waiting and turnaround times
        System.out.println("\nAverage Waiting Time: " + (totalWaitingTime / n));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / n));

        // Print Gantt chart
        System.out.println("\nGantt Chart:");
        for (GanttChartEntry entry : ganttChart) {
            System.out.print("| " + entry.processName + " |");
        }
        System.out.println();
        for (GanttChartEntry entry : ganttChart) {
            System.out.print(entry.startTime + "    ");
        }
        System.out.println(currentTime); // Print final finish time
    }
}















// Enter the number of processes: 4
// Enter CPU burst time for P1: 5
// Enter arrival time for P1: 0
// Enter CPU burst time for P2: 4
// Enter arrival time for P2: 1
// Enter CPU burst time for P3: 2
// Enter arrival time for P3: 2
// Enter CPU burst time for P4: 1
// Enter arrival time for P4: 4
// Enter the time slice: 2

// Process Burst Time      Arrival Time    Start Time      Finish Time     Turnaround Time Waiting Time
// P1      5               0               0               12              12              7
// P2      4               1               2               11              10              6
// P3      2               2               4               6               4               2
// P4      1               4               6               7               3               2

// Average Waiting Time: 4.25
// Average Turnaround Time: 7.25

// Gantt Chart:
// | P1 || P2 || P3 || P4 || P1 || P2 || P1 |
// 0    2    4    6    7    9    11    12