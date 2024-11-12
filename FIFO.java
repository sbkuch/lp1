import java.io.*;
class FIFO {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter number of FRAMES: ");
        int f = Integer.parseInt(br.readLine());
        System.out.print("Enter number of INPUTS: ");
        int n = Integer.parseInt(br.readLine());
        int[] fifo = new int[f];
        int[] inp = new int[n];
        System.out.println("Enter INPUT: ");
        for (int i = 0; i < n; i++) inp[i] = Integer.parseInt(br.readLine());
        int hit = 0, fault = 0, j = 0;
        for (int i = 0; i < f; i++) fifo[i] = -1; // Initialize frames
        for (int i = 0; i < n; i++) {
            boolean found = false;
            for (int k = 0; k < f; k++) {
                if (fifo[k] == inp[i]) {
                    hit++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                fifo[j] = inp[i];
                j = (j + 1) % f;
                fault++;
            }
        }
        System.out.println("HIT: " + hit + " FAULT: " + fault + " HIT RATIO: " + (float) hit / n);
    }
}
