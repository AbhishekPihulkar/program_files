public class MemoryAllocation {
    public static void firstFit(int[] blocks, int[] processes) {
        System.out.println("First Fit:");
        int[] allocation = new int[processes.length];
        for (int i = 0; i < processes.length; i++) {
            allocation[i] = -1;
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[j] >= processes[i]) {
                    allocation[i] = j;
                    blocks[j] -= processes[i];
                    break;
                }
            }
        }
        printAllocation(allocation);
    }

    public static void bestFit(int[] blocks, int[] processes) {
        System.out.println("Best Fit:");
        int[] allocation = new int[processes.length];
        for (int i = 0; i < processes.length; i++) {
            int bestIdx = -1, bestSize = Integer.MAX_VALUE;
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[j] >= processes[i] && blocks[j] < bestSize) {
                    bestSize = blocks[j];
                    bestIdx = j;
                }
            }
            allocation[i] = bestIdx;
            if (bestIdx != -1) blocks[bestIdx] -= processes[i];
        }
        printAllocation(allocation);
    }

    public static void worstFit(int[] blocks, int[] processes) {
        System.out.println("Worst Fit:");
        int[] allocation = new int[processes.length];
        for (int i = 0; i < processes.length; i++) {
            int worstIdx = -1, worstSize = -1;
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[j] >= processes[i] && blocks[j] > worstSize) {
                    worstSize = blocks[j];
                    worstIdx = j;
                }
            }
            allocation[i] = worstIdx;
            if (worstIdx != -1) blocks[worstIdx] -= processes[i];
        }
        printAllocation(allocation);
    }

    private static void printAllocation(int[] allocation) {
        for (int i = 0; i < allocation.length; i++) {
            System.out.println("Process " + i + " allocated to block " + allocation[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] blocks1 = {100, 500, 200, 300, 600};
        int[] blocks2 = blocks1.clone();
        int[] blocks3 = blocks1.clone();
        int[] processes = {212, 417, 112, 426};

        firstFit(blocks1, processes);
        bestFit(blocks2, processes);
        worstFit(blocks3, processes);
    }
}
