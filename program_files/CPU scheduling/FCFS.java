import java.util.*;

class Process {
    int pid, arrivalTime, burstTime, remainingTime, completionTime, turnArroundTime, waitingTime;

    Process(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class FCFS {
    private static void fcfs(ArrayList<Process> processes) {
        int Currenttime = 0;
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        for (Process p : processes) {
            if (Currenttime < p.arrivalTime) {
                Currenttime = p.arrivalTime; // direct jump to arrival time
            }
            // process execution, time ++ 
            Currenttime += p.burstTime;

            p.completionTime = Currenttime;
            p.turnArroundTime = p.completionTime - p.arrivalTime;
            p.waitingTime = p.turnArroundTime - p.burstTime;

        }
        display(processes);
    }

    private static void display(ArrayList<Process> processes) {
        System.out.println("\n PID \t AT  \t BT \t CT \t TAT \t WT");
        int totalTAT = 0;
        int totalWT = 0;

        for (Process p : processes) {
            System.out.printf("%d \t %d \t %d \t %d \t %d \t %d \t\n", p.pid, p.arrivalTime, p.burstTime,
                    p.completionTime, p.turnArroundTime, p.waitingTime);
            totalTAT += p.turnArroundTime;
            totalWT += p.waitingTime;
        }
        float avgTAT = (float) totalTAT / processes.size();
        float avgWT = (float) totalWT / processes.size();
        System.out.println("Average Turn Around Time : " + avgTAT);
        System.out.println("Average Waiting Time : " + avgWT);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of Processes : ");
        int n = sc.nextInt();
        ArrayList<Process> processes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter ArrivalTime and BurstTime of Process(" + (i + 1) + ")");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            processes.add(new Process(i + 1, at, bt));
        }
        fcfs(processes);
        sc.close();
    }
}