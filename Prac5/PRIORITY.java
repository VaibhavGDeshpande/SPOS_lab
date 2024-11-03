package Prac5;
import java.util.Scanner;

class PRIORITY {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the number of processes");
        int n;
        n = sc.nextInt();
        int i;
        String[] process = new String[n];
        for (i = 0; i < n; i++) {
            process[i] = "p" + (i + 1);
        }
        int[] arrivaltime = new int[n];
        for (i = 0; i < n; i++) {
            arrivaltime[i] = 0;
        }
        System.out.println("Enter CPU time for the  processes");
        int[] CPUtime = new int[n];
        for (i = 0; i < n; i++) {
            CPUtime[i] = sc.nextInt();
        }
        int[] pr = new int[n];
        System.out.println("enter the priorities of the processes");
        for (i = 0; i < n; i++) {
            pr[i] = sc.nextInt();
        }
        for (i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = 0;
                String tmp1;
                int tmp2 = 0;
                if (pr[i] > pr[j]) {
                    tmp1 = process[i];
                    process[i] = process[j];
                    process[j] = tmp1;
                    tmp2 = CPUtime[i];
                    CPUtime[i] = CPUtime[j];
                    CPUtime[j] = tmp2;
                    tmp = pr[i];
                    pr[i] = pr[j];
                    pr[j] = tmp;
                }
            }
        }
        int[] starttime = new int[n];
        starttime[0] = arrivaltime[0];
        for (i = 1; i < n; i++) {
            starttime[i] = starttime[i - 1] + CPUtime[i - 1];
        }
        int[] finishtime = new int[n];
        for (i = 0; i < n; i++) {
            finishtime[i] = starttime[i] + CPUtime[i];
        }
        int[] waitingtime = new int[n];
        for (i = 0; i < n; i++) {
            waitingtime[i] = starttime[i] - arrivaltime[i];
        }
        int[] turnaroundtime = new int[n];
        for (i = 0; i < n; i++) {
            turnaroundtime[i] = waitingtime[i] + CPUtime[i];
        }
        float avg_turnaround_time = 0;
        float avg_waiting_time = 0;
        float sum1 = 0;
        float sum = 0;
        for (i = 0; i < n; i++) {
            sum = turnaroundtime[i] + sum;
            sum1 = waitingtime[i] + sum1;
        }
        avg_turnaround_time = sum / n;
        avg_waiting_time = sum1 / n;
        System.out
                .println("PROCESSESS      PRIORITY      CPUTIME       STARTTIME       FINISHTIME       TURNTIME      WAITINGTIME");
        for (i = 0; i < n; i++) {
            System.out.println("\t");
            System.out.print(process[i] + "\t\t");
            System.out.print(pr[i] + "\t\t");
            System.out.print(CPUtime[i] + "\t\t");
            System.out.print(starttime[i] + "\t\t");
            System.out.print(finishtime[i] + "\t\t");
            System.out.print(turnaroundtime[i] + "\t\t");
            System.out.print(waitingtime[i] + "\t\t");
            System.out.println("\n");
        }
        System.out.println("\n");
        System.out.println("average turnaround time is " + avg_turnaround_time);
        System.out.println("average waiting time is " + avg_waiting_time);
    }
}
// prority print kryla phije
