import java.util.ArrayList;
import java.util.Scanner;
public class LRU {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of frames: ");
        int frame = sc.nextInt();
        System.out.print("Enter number of pages: ");
        int pg = sc.nextInt();
        int[] pages = new int[pg];
        System.out.print("Enter pages: ");
        for (int i = 0; i < pg; i++) pages[i] = sc.nextInt();
        ArrayList<Integer> frames = new ArrayList<>(frame);
        int pageFaults = 0;
        for (int page : pages) {
            if (!frames.contains(page)) {
                if (frames.size() == frame) frames.remove(0);
                pageFaults++;
            } else {
                frames.remove((Integer) page);
            }
            frames.add(page); // Move page to the end as recently used
        }
        System.out.println("Page Faults: " + pageFaults);
        sc.close();
    }
}
