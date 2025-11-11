import java.util.*;

public class Optimal {
    public static void main(String[] args) {
        int[] pages = {2,3,2,1,5,2,4,5,3,2,5,2};
        int frames = 3;
        List<Integer> memory = new ArrayList<>();
        int faults = 0;

        for (int i = 0; i < pages.length; i++) {
            if (!memory.contains(pages[i])) {
                if (memory.size() == frames) {
                    int farthest = -1, replaceIndex = 0;
                    for (int j = 0; j < frames; j++) {
                        int nextUse = Integer.MAX_VALUE;
                        for (int k = i + 1; k < pages.length; k++) {
                            if (pages[k] == memory.get(j)) {
                                nextUse = k;
                                break;
                            }
                        }
                        if (nextUse > farthest) {
                            farthest = nextUse;
                            replaceIndex = j;
                        }
                    }
                    memory.set(replaceIndex, pages[i]);
                } else {
                    memory.add(pages[i]);
                }
                faults++;
            }
            System.out.println("Step " + (i+1) + ": " + memory);
        }
        System.out.println("Total Page Faults: " + faults);
    }
}