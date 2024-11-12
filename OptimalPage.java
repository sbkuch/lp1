import java.util.Scanner;
public class OptimalPage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of Frames: ");
        int frames = sc.nextInt();
        System.out.print("Enter number of Pages: ");
        int pages = sc.nextInt();
        int[] frame = new int[frames];
        int[] pageSeq = new int[pages];
        int hit = 0, faults = 0;
        System.out.println("Enter the pages: ");
        for (int i = 0; i < pages; i++) pageSeq[i] = sc.nextInt();
        for (int i = 0; i < frames; i++) frame[i] = -1;
        for (int i = 0; i < pages; i++) {
            boolean found = false;
            for (int j = 0; j < frames; j++) {
                if (frame[j] == pageSeq[i]) { hit++; found = true; break; }
            }
            if (!found) {
                int pos = 0, farthest = -1;
                for (int j = 0; j < frames; j++) {
                    int nextUse = Integer.MAX_VALUE;
                    for (int k = i + 1; k < pages; k++) {
                        if (frame[j] == pageSeq[k]) { nextUse = k; break; }
                    }
                    if (nextUse > farthest) { farthest = nextUse; pos = j; }
                    if (frame[j] == -1) { pos = j; break; }
                }
                frame[pos] = pageSeq[i];
                faults++;
            }
        }
        System.out.println("\nTotal Page Faults: " + faults + "\nTotal Hits: " + hit);
        sc.close();
    }
}
