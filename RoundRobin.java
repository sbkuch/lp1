import java.util.Scanner;
public class RoundRobin {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int[] process = new int[n], burst = new int[n], completion = new int[n];
        int[] WT = new int[n], TAT = new int[n], oribrust = new int[n];
        int tq, sum = 0;
        float totalTAT = 0, totalWT = 0;
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process " + (i + 1) + " Burst Time: ");
            burst[i] = sc.nextInt();
            process[i] = i + 1;
            oribrust[i] = burst[i];
        }
        System.out.print("Enter Time Quantum: ");
        tq = sc.nextInt();
        int count;
        while (true) {
            count = 0;
            for (int i = 0; i < n; i++) {
                if (burst[i] == 0) {
                    count++;
                    continue;
                }
                int temp = Math.min(burst[i], tq);
                burst[i] -= temp;
                sum += temp;
                completion[i] = sum;
                if (burst[i] == 0) count++;
            }
            if (count == n) break;
        }
        System.out.println("\nProcess \tBurst Time \tCompletion \tTAT \tWT");
        for (int i = 0; i < n; i++) {
            TAT[i] = completion[i];
            WT[i] = TAT[i] - oribrust[i];
            totalTAT += TAT[i];
            totalWT += WT[i];
            System.out.println(process[i] + "\t\t" + oribrust[i] + "\t\t" + completion[i] + "\t\t" + TAT[i] + "\t" + WT[i]);
        }
        System.out.printf("\nAverage Waiting Time: %.2f", totalWT / n);
        System.out.printf("\nAverage Turnaround Time: %.2f", totalTAT / n);
        sc.close();
    }
}
