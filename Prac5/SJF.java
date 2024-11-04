package Prac5;
import java.util.Scanner;

class SJF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();
        
        // Arrays to hold process details
        String[] process = new String[n];
        int[] arrivalTime = new int[n];
        int[] cpuTime = new int[n];
        int[] startTime = new int[n];
        int[] finishTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        boolean[] completed = new boolean[n]; // Track completed processes

        // Initialize processes
        for (int i = 0; i < n; i++) {
            process[i] = "P" + (i + 1);
            System.out.println("Enter arrival time for process " + process[i] + ":");
            arrivalTime[i] = sc.nextInt();
            System.out.println("Enter CPU time for process " + process[i] + ":");
            cpuTime[i] = sc.nextInt();
        }

        int currentTime = 0, completedProcesses = 0;
        
        while (completedProcesses < n) {
            // Find the process with the shortest job that has already arrived
            int shortestIndex = -1;
            int shortestCPU = Integer.MAX_VALUE;
            
            for (int i = 0; i < n; i++) {
                if (!completed[i] && arrivalTime[i] <= currentTime && cpuTime[i] < shortestCPU) {
                    shortestCPU = cpuTime[i];
                    shortestIndex = i;
                }
            }

            // If no process has arrived by the current time, increment time
            if (shortestIndex == -1) {
                currentTime++;
                continue;
            }

            // Set times for the selected process
            startTime[shortestIndex] = currentTime;
            finishTime[shortestIndex] = currentTime + cpuTime[shortestIndex];
            turnaroundTime[shortestIndex] = finishTime[shortestIndex] - arrivalTime[shortestIndex];
            waitingTime[shortestIndex] = turnaroundTime[shortestIndex] - cpuTime[shortestIndex];
            
            // Update current time and mark process as completed
            currentTime = finishTime[shortestIndex];
            completed[shortestIndex] = true;
            completedProcesses++;
        }

        // Calculate averages
        float totalTurnaroundTime = 0, totalWaitingTime = 0;
        for (int i = 0; i < n; i++) {
            totalTurnaroundTime += turnaroundTime[i];
            totalWaitingTime += waitingTime[i];
        }
        
        float avgTurnaroundTime = totalTurnaroundTime / n;
        float avgWaitingTime = totalWaitingTime / n;
        
        // Output results
        System.out.println("PROCESS    ARRIVAL TIME   CPU TIME   START TIME   FINISH TIME   TURNAROUND TIME   WAITING TIME");
        for (int i = 0; i < n; i++) {
            System.out.printf("%s\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n",
                    process[i], arrivalTime[i], cpuTime[i], startTime[i], finishTime[i], turnaroundTime[i], waitingTime[i]);
        }
        System.out.printf("\nAverage Turnaround Time: %.2f\n", avgTurnaroundTime);
        System.out.printf("Average Waiting Time: %.2f\n", avgWaitingTime);
    }
}

















// Enter the number of processes:
// 4
// Enter arrival time for process P1:
// 1
// Enter CPU time for process P1:
// 3
// Enter arrival time for process P2:
// 2
// Enter CPU time for process P2:
// 4
// Enter arrival time for process P3:
// 1
// Enter CPU time for process P3:
// 2
// Enter arrival time for process P4:
// 4
// Enter CPU time for process P4:
// 4
// PROCESS    ARRIVAL TIME   CPU TIME   START TIME   FINISH TIME   TURNAROUND TIME   WAITING TIME
// P1              1               3               3               6               5               2
// P2              2               4               6               10              8               4
// P3              1               2               1               3               2               0
// P4              4               4               10              14              10              6

// Average Turnaround Time: 6.25
// Average Waiting Time: 3.00