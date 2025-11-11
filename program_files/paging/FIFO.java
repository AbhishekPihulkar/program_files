import java.util.*;

// FIFO Page Replacement algorithm
public class FIFO {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Number of Frames: ");
        int frames = sc.nextInt();

        System.out.print("Enter Length of Reference String: ");
        int ref_len = sc.nextInt();

        int ref_str[] = new int[ref_len];
        System.out.println("Enter Reference String: ");
        for (int i = 0; i < ref_len; i++) {
            ref_str[i] = sc.nextInt();
        }

        // Algorithm implementation
        int pointer = 0;
        int hits = 0;
        int faults = 0;
        
        int[] memory = new int[frames];
        Arrays.fill(memory, -1);
        
        // Store all steps for column-wise display
        int[][] stepsMatrix = new int[frames][ref_len];

        for (int i = 0; i < ref_len; i++) {
            boolean found = false;
            int page = ref_str[i];

            // Check if page is already in memory
            for (int j = 0; j < frames; j++) {
                if (memory[j] == page) {
                    found = true;
                    hits++;
                    break;
                }
            }

            // If page fault occurs, replace using FIFO
            if (!found) {
                memory[pointer] = page;
                pointer = (pointer + 1) % frames;
                faults++;
            }

            // Store current state in matrix
            for (int j = 0; j < frames; j++) {
                stepsMatrix[j][i] = memory[j];
            }
            
        }

        // Display in column-wise format
        System.out.println("\nPage Replacement Process (Column-wise Format):\n");
        
        // Print reference string header
        System.out.print("Ref String\t");
        for (int i = 0; i < ref_len; i++) {
            System.out.print(ref_str[i] + "\t");
        }
        System.out.println();
        
        System.out.print("------------\t");
        for (int i = 0; i < ref_len; i++) {
            System.out.print("----\t");
        }
        System.out.println();

        // Print each frame row
        for(int j = 0; j < frames; j++) {
            System.out.print("Frame " + j + "\t\t");
            for (int i = 0; i < ref_len; i++) {
                if (stepsMatrix[j][i] == -1) {
                    System.out.print("-\t");
                } else {
                    System.out.print(stepsMatrix[j][i] + "\t");
                }
            }
            System.out.println();
        }

        System.out.println("\nResults:");
        System.out.println("Page Hits: " + hits);
        System.out.println("Page Faults: " + faults);
        System.out.println("Hit Ratio: " + ((float) hits / ref_len) * 100 + "%");
        
        sc.close();
    }
}

// import java.util.*;

// //FCFS Page Replacement algorithm
// public class FCFS {
//     public static void main(String args[]){
//         Scanner sc  = new Scanner(System.in);

//         int frames;
//         System.out.print("Enter Number of Frames : ");
//         frames = sc.nextInt();

//         int ref_len;
//         System.out.print("Enter Length of reference String :");
//         ref_len = sc.nextInt();

//         int ref_str[] = new int[ref_len];
//         System.out.println("Enter Reference String : ");
//         for(int i = 0 ;i < ref_len ;i++){
//             ref_str[i] = sc.nextInt();
//         }

//         // all requirements taken now lets calculate
//         int pointer = 0; 
//         int hits = 0;
//         int faults = 0;
        
//         // Lets first declare empty meomory
//         int[] memory = new int[frames];
//         Arrays.fill(memory,-1);

//         //Main Logic:
//            System.out.println("\nPage Replacement Process:\n");
//         for(int i = 0 ;i < ref_len ;i++ ){
//             boolean found = false;
//             int page = ref_str[i];

//              // check if page already in memory
//             for(int j = 0 ;j < frames ;j++){
//                 if(memory[j] == page){
//                     found = true;
//                     hits++;
//                     break;
//                 }
//             }
//              // if not found, replace page using FIFO
//              if(!found){
//                 memory[pointer] = page;
//                 pointer = (pointer + 1)%frames;
//                 faults++;
//              }

//              // display current memory layout
//            System.out.print("Step " + (i + 1) + " (" + page + "): ");
//             for(int j = 0 ;j < frames ; j++){
//                 if(memory[j]== -1){
//                     System.out.print("- ");
//                 }else{
//                     System.out.print(memory[j] + " ");
//                 }        
//             }
//              System.out.println();   
//         }

//         System.out.println("Page Hits : " + hits);
//             System.out.println("Page Faults : " + faults);
//            System.out.println("Hit Ratio: " + ((float)hits / ref_len) * 100 + "%");
//         sc.close();
//     }
    
// }