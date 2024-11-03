package Prac5;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Process {
    String name;
    int burstTime;
    int remainingTime;
    int startTime;
    int finishTime;
    int waitingTime;
    int turnaroundTime;

    Process(String name, int burstTime) {
        this.name = name;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
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

        // Input processes and their burst times
        for (int i = 0; i < n; i++) {
            System.out.print("Enter process name for P" + (i + 1) + ": ");
            String name = sc.next();
            System.out.print("Enter CPU burst time for " + name + ": ");
            int burstTime = sc.nextInt();
            processes[i] = new Process(name, burstTime);
        }

        // Input time slice
        System.out.print("Enter the time slice: ");
        int timeSlice = sc.nextInt();

        int currentTime = 0;
        boolean done;
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;

        // List to hold Gantt chart entries
        List<GanttChartEntry> ganttChart = new ArrayList<>();

        do {
            done = true;
            for (Process process : processes) {
                if (process.remainingTime > 0) {
                    done = false;
                    int executionStart = currentTime;

                    if (process.remainingTime == process.burstTime) {
                        process.startTime = currentTime;
                    }

                    if (process.remainingTime > timeSlice) {
                        currentTime += timeSlice;
                        process.remainingTime -= timeSlice;
                    } else {
                        currentTime += process.remainingTime;
                        process.finishTime = currentTime;
                        process.remainingTime = 0;
                        process.turnaroundTime = process.finishTime;
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
        System.out.println("\nProcess\tBurst Time\tStart Time\tFinish Time\tTurnaround Time\tWaiting Time");
        for (Process process : processes) {
            System.out.println(process.name + "\t" + process.burstTime + "\t\t" + process.startTime + "\t\t"
                    + process.finishTime + "\t\t" + process.turnaroundTime + "\t\t" + process.waitingTime);
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
