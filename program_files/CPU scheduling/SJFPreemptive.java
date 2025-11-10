import java.util.*;

class  Process{
    int pid,arrivalTime,burstTime,remainingTime,completionTime,turnArroundTime,waitingTime;

    Process(int pid , int arrivalTime , int burstTime){
        this.pid  = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class SJFPreemptive {
    private static void sjf_preemptive(ArrayList<Process> processes){
        int time = 0;
        int completedProcesses = 0;
        Process current = null;
        int n = processes.size();

        processes.sort(Comparator.comparingInt(p->p.arrivalTime));

        while (completedProcesses < n) {
            List<Process> ReadyQueue = new ArrayList<>();

            for(Process p : processes){
                if(p.arrivalTime <= time && p.remainingTime > 0){
                    ReadyQueue.add(p);
                }
            }

            if(!ReadyQueue.isEmpty()){
                current = ReadyQueue.stream().min(Comparator.comparingInt(p -> p.remainingTime)).get(); 
                current.remainingTime--;
                time++;


                if(current.remainingTime == 0){
                    completedProcesses++;
                    current.completionTime = time;
                    current.turnArroundTime = current.completionTime - current.arrivalTime;
                    current.waitingTime = current.turnArroundTime - current.burstTime;
                }

            }else{
                time++;
            }
        }
        display(processes);
    }

    private static void display(ArrayList<Process> processes){
        System.out.println("\n PID \t AT  \t BT \t CT \t TAT \t WT");
        int totalTAT = 0;
        int totalWT = 0;

        for(Process p : processes){
            System.out.printf("%d \t %d \t %d \t %d \t %d \t %d \t\n",p.pid,p.arrivalTime,p.burstTime,p.completionTime,p.turnArroundTime,p.waitingTime);
            totalTAT += p.turnArroundTime;
            totalWT += p.waitingTime;
        }
        float avgTAT = (float)totalTAT/processes.size();
        float avgWT = (float)totalWT/processes.size();
        System.out.println("Average Turn Around Time : "+ avgTAT);
        System.out.println("Average Waiting Time : " + avgWT);
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of Processes : ");
        int n = sc.nextInt();
        ArrayList<Process>processes = new ArrayList<>();

        for(int i = 0 ;i < n ;i++){
            System.out.println("Enter ArrivalTime and BurstTime of Process(" + (i+1) + ")");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            processes.add(new Process(i+1, at, bt));
        }
            sjf_preemptive(processes);
            sc.close();
    }
}