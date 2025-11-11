import java.util.*;

public class LRU{
public static void main(String[]args){
int[] pages = {2,3,2,1,5,2,4,5,3,2,5,2};
int capacity = 3;
List<Integer> memory = new ArrayList<>();
int faults = 0;

for (int page : pages) {
    if (memory.contains(page)) {
        memory.remove((Integer)page);
    } else {
        if(memory.size() == capacity) memory.remove(0);
        faults++;
    }
    memory.add(page);
    System.out.println(memory);
}
System.out.println("Page faults: " + faults);
}
}