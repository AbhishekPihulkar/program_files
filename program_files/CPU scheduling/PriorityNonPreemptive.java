import java.util.*;

class Process {
    int pid, arrivalTime, burstTime, remainingTime, completionTime, turnArroundTime, waitingTime, priority;

    Process(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
    }
}

public class PriorityNonPreemptive {
    private static void priority_non_preemptive(ArrayList<Process> processes) {
        // Priority preemptive algo logic 
        int Currenttime = 0;
        int completedProcess = 0;
        int n = processes.size();

        processes.sort(Comparator.comparingInt(p->p.arrivalTime));

        while(completedProcess < n){
            List<Process>ReadyQueue = new ArrayList<>();
            for(Process p:processes){
                if(p.arrivalTime <= Currenttime && p.arrivalTime > 0){
                    ReadyQueue.add(p);
                }
            }
            if(!ReadyQueue.isEmpty()){
            Process highestPriorityProcess = ReadyQueue.stream().min(Comparator.comparingInt(p->p.priority)).get();
            
            Currenttime += highestPriorityProcess.burstTime;
            highestPriorityProcess.completionTime = Currenttime;
            highestPriorityProcess.turnArroundTime = highestPriorityProcess.completionTime-highestPriorityProcess.arrivalTime;
            highestPriorityProcess.waitingTime = highestPriorityProcess.turnArroundTime-highestPriorityProcess.burstTime;
            highestPriorityProcess.remainingTime = 0;
            completedProcess++;

        }else{
            Currenttime++;
        }
        }
        display(processes);
    }

    private static void display(ArrayList<Process> processes) {
        System.out.println("\n PID \t AT  \t BT \t CT \t TAT \t WT");
        int totalTAT = 0;
        int totalWT = 0;

        for (Process p : processes) {
            System.out.printf("%d \t %d \t %d \t %d \t %d \t %d \t %d \t\n", p.pid, p.arrivalTime, p.burstTime,p.priority,
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
            System.out.println("Enter ArrivalTime , BurstTime  & Priority of Process(" + (i + 1) + ")");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            int priority = sc.nextInt();
            processes.add(new Process(i + 1, at, bt,priority));
        }
        priority_non_preemptive(processes);
        sc.close();
    }
}