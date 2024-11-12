import java.util.Scanner;
public class SJF {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int[] pid = new int[n], at = new int[n], bt = new int[n], ct = new int[n];
        int[] ta = new int[n], wt = new int[n], f = new int[n];
        int st = 0, tot = 0;
        float avgwt = 0, avgta = 0;
        for (int i = 0; i < n; i++) {
            System.out.print("Enter process " + (i + 1) + " arrival and burst times: ");
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
        }
        while (tot < n) {
            int min = Integer.MAX_VALUE, c = n;
            for (int i = 0; i < n; i++) {
                if (at[i] <= st && f[i] == 0 && bt[i] < min) {
                    min = bt[i];
                    c = i;
                }
            }
            if (c == n) st++;
            else {
                ct[c] = st + bt[c];
                st += bt[c];
                ta[c] = ct[c] - at[c];
                wt[c] = ta[c] - bt[c];
                f[c] = 1;
                tot++;
            }
        }
        System.out.println("\nPid \t Arrival \t Burst \t Complete \t Turnaround \t Waiting");
        for (int i = 0; i < n; i++) {
            avgwt += wt[i];
            avgta += ta[i];
            System.out.println(pid[i] + "\t\t" + at[i] + "\t\t" + bt[i] + "\t\t" + ct[i] + "\t\t" + ta[i] + "\t\t" + wt[i]);
        }

        System.out.printf("\nAverage turnaround time: %.2f", avgta / n);
        System.out.printf("\nAverage waiting time: %.2f", avgwt / n);
        sc.close();
    }
}
