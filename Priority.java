import java.util.Scanner;
public class Priority {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int[] process = new int[n], priority = new int[n], burst = new int[n];
        int[] completion = new int[n], WT = new int[n], TAT = new int[n];
        int sum = 0;
        float totalTAT = 0, totalWT = 0;
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process " + (i + 1) + " Burst Time and Priority: ");
            burst[i] = sc.nextInt();
            priority[i] = sc.nextInt();
            process[i] = i + 1;
        }
        // Sort by priority
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (priority[i] > priority[j]) {
                    int temp = priority[i]; priority[i] = priority[j]; priority[j] = temp;
                    temp = process[i]; process[i] = process[j]; process[j] = temp;
                    temp = burst[i]; burst[i] = burst[j]; burst[j] = temp;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            sum += burst[i];
            completion[i] = sum;
            TAT[i] = completion[i];
            WT[i] = TAT[i] - burst[i];
            totalTAT += TAT[i];
            totalWT += WT[i];
        }
        System.out.println("\nPriority \tProcess \tBurst Time \tCompletion \tWT \tTAT");
        for (int i = 0; i < n; i++) {
            System.out.println(priority[i] + "\t\t" + process[i] + "\t\t" + burst[i] + "\t\t" + completion[i] + "\t\t" + WT[i] + "\t\t" + TAT[i]);
        }
        System.out.printf("\nAverage Turnaround Time: %.2f", totalTAT / n);
        System.out.printf("\nAverage Wait Time: %.2f", totalWT / n);
        sc.close();
    }
}
