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

public class PriorityPreemptive {
    private static void priority_preemptive(ArrayList<Process> processes) {
        // Priority preemptive algo logic 
        int time = 0;
        int completedProcess = 0;
        int n = processes.size();

        processes.sort(Comparator.comparingInt(p->p.arrivalTime));

        while(completedProcess < n){
            Process highestPriorityProcess = null;
            
            for(Process p : processes){
                if(p.arrivalTime <=time && p.remainingTime > 0){
                    if(highestPriorityProcess == null || p.priority < highestPriorityProcess.priority){
                        highestPriorityProcess = p;
                    }
                }
            }

            if(highestPriorityProcess != null){
                
                //execute the process
                highestPriorityProcess.remainingTime--;
                time++;

                //if process completed, calculate the parameters
                if(highestPriorityProcess.remainingTime==0){
                    completedProcess++;

                    highestPriorityProcess.completionTime = time;
                    highestPriorityProcess.turnArroundTime = highestPriorityProcess.completionTime-highestPriorityProcess.arrivalTime;
                    highestPriorityProcess.waitingTime = highestPriorityProcess.turnArroundTime-highestPriorityProcess.burstTime;
                }
            }else{
                time++;
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
        priority_preemptive(processes);
        sc.close();
    }
}