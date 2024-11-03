package Prac5;
import java.util.Scanner;
class FCFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i;
        String[] process = { "p1", "p2", "p3", "p4", "p5" };
        int[] arrivaltime = new int[5];
        System.out.println("Enter arrival time for 5 processes");
        for (i = 0; i < 5; i++) {
            arrivaltime[i] = sc.nextInt();
        }
        System.out.println("Enter CPU time for 5 processes");
        int[] CPUtime = new int[5];
        for (i = 0; i < 5; i++) {
            CPUtime[i] = sc.nextInt();
        }
        for (i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                int tmp = 0;
                String tmp1;
                int tmp2 = 0;
                if (arrivaltime[i] > arrivaltime[j]) {
                    tmp = arrivaltime[i];
                    arrivaltime[i] = arrivaltime[j];
                    arrivaltime[j] = tmp;

                    tmp1 = process[i];
                    process[i] = process[j];
                    process[j] = tmp1;

                    tmp2 = CPUtime[i];
                    CPUtime[i] = CPUtime[j];
                    CPUtime[j] = tmp2;
                }
            }
        }
        int[] starttime = new int[5];
        starttime[0] = arrivaltime[0];
        for (i = 1; i < 5; i++) {
            starttime[i] = starttime[i - 1] + CPUtime[i - 1];
        }
        int[] finishtime = new int[5];
        for (i = 0; i < 5; i++) {
            finishtime[i] = starttime[i] + CPUtime[i];
        }
        int[] waitingtime = new int[5];
        for (i = 0; i < 5; i++) {
            waitingtime[i] = starttime[i] - arrivaltime[i];
        }
        int[] turnaroundtime = new int[5];
        for (i = 0; i < 5; i++) {
            turnaroundtime[i] = waitingtime[i] + CPUtime[i];
        }
        float avg_turnaround_time = 0;
        float avg_waiting_time = 0;
        float sum1 = 0;
        float sum = 0;
        for (i = 0; i < 5; i++) {
            sum = turnaroundtime[i] + sum;
            sum1 = waitingtime[i] + sum1;
        }
        avg_turnaround_time = sum / 5;
        avg_waiting_time = sum1 / 5;
        System.out.println("PROCESSESS     ARRIVALTIME       CPUTIME      STARTTIME       FINISHTIME       TURNTIME       WAITINGTIME");
        for (i = 0; i < 5; i++) {
            System.out.println("\t");
            System.out.print(process[i] + "\t\t");
            System.out.print(arrivaltime[i] + "\t\t");
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