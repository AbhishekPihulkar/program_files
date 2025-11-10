import java.util.*;

class Process {
    int pid, arrivalTime, burstTime, remainingTime, completionTime, turnArroundTime, waitingTime,timeQuantum;

    Process(int pid, int arrivalTime, int burstTime, int timeQuantum) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.timeQuantum = timeQuantum;
    }
}

public class RoundRobin {
    private static void round_robin(ArrayList<Process> processes,int TimeQuantum) {
        int time = 0;
        int completedProcess = 0;
        int n = processes.size();
        
        
        List<Process>ReadyQueue = new ArrayList<>();

        while (completedProcess < n) {
            for(Process p : processes){
                if(p.arrivalTime <= time && p.remainingTime > 0){
                    ReadyQueue.add(p);
                }
            }

            if(!ReadyQueue.isEmpty()){
                Process current = ReadyQueue.remove(0);
                int executionTime = Math.min(TimeQuantum,current.remainingTime);
                
                current.remainingTime -= executionTime;// process executed for this execution time
                time += executionTime;

                if(current.remainingTime > 0){
                    ReadyQueue.add(current);
                }else{
                    completedProcess++;
                    current.completionTime = time;
                    current.turnArroundTime = current.completionTime-current.arrivalTime;
                    current.waitingTime = current.turnArroundTime-current.burstTime;
                }
               
            }else{
                time++;
            }
        }

        display(processes,TimeQuantum);
    }

    private static void display(ArrayList<Process> processes , int timeQuantum) {
        System.out.println("Round Robin CPU Scheduling Algorithm with TimeQuantum : " + timeQuantum);
        System.out.println("\n PID \t AT  \t BT \t CT \t TAT \t WT");
        int totalTAT = 0;
        int totalWT = 0;

        for (Process p : processes) {
            System.out.printf("%d \t %d \t %d \t %d \t %d \t %d \t %d \t\n", p.pid, p.arrivalTime, p.burstTime,p.timeQuantum,
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
        System.out.println("Enter the TimeQuantum of the process : ");
        int timeQuantum = sc.nextInt();
        System.out.println("Enter the number of Processes : ");
        int n = sc.nextInt();
        ArrayList<Process> processes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter ArrivalTime , BurstTime  of Process(" + (i + 1) + ")");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            
            processes.add(new Process(i + 1, at, bt,timeQuantum));
        }
        round_robin(processes,timeQuantum);
        sc.close();
    }
}