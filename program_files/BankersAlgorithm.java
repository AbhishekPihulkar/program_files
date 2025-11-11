import java.util.*;

public class BankersAlgorithm {

    public static void main(String args[]) {
        int n;
        int m;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Number Of Processes : ");
        n = sc.nextInt();
        System.out.print("Enter Number of Resource Types :");
        m = sc.nextInt();

        // ArrayList<ArrayList<Integer>> Allocation= new ArrayList<>();
        int Allocation[][] = new int[n][m];
        System.out.println("Enter the Allocation Matrix :");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Allocation[i][j] = sc.nextInt();
            }
        }

        int Max[][] = new int[n][m];
        System.out.println("Enter the Max Matrix : ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Max[i][j] = sc.nextInt();
            }
        }

        int Available[] = new int[m];
        System.out.println("Enter Available Array : ");
        for (int i = 0; i <m; i++) {
            Available[i] = sc.nextInt();
        }

        int work[] = new int[m];
        for (int i = 0; i < m; i++) {
            work[i] = Available[i];
        }

        int Need[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Need[i][j] = Max[i][j] - Allocation[i][j];
            }
        }

        int count = 0;
        int SafeSequence[] = new int[n];
        Boolean Finish[] = new Boolean[n];
        Arrays.fill(Finish, false);

        while (count < n) {
            Boolean found = false;

            for (int i = 0; i < n; i++) {
                if (!Finish[i]) {
                    int j;
                    for (j = 0; j < m; j++) {
                        if (Need[i][j] > work[j]) {
                            break;
                        }
                    }

                    if (j == m) {
                        // process allocated
                        // process finished its word and released the resources
                        // so we have more resources available
                        // lets add them
                        for (int k = 0; k < m; k++) {
                            work[k] += Allocation[i][k];
                        }
                        SafeSequence[count] = i;
                            count++;
                            Finish[i] = true;
                            found = true;
                    }

                }
            }
            if (!found) {
                System.out.println("System is not is Safe State");
                System.exit(0);
            }
        }

        // All process executed then:
        System.out.println("\n System is in SAFE STATE With Safe Sequence:  ");
        for (int i = 0; i < n; i++) {
            System.out.print(SafeSequence[i] + "->");
        }

        sc.close();
    }
}