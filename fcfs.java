import java.util.Scanner;
public class fcfs {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes: ");
        int n = sc.nextInt(); 
        int pid[] = new int[n]; // process ids
        int ar[] = new int[n];  // arrival times
        int bt[] = new int[n];  // burst or execution times
        int ct[] = new int[n];  // completion times
        int ta[] = new int[n];  // turn around times
        int wt[] = new int[n];  // waiting times
        int temp; 
        float avgwt = 0, avgta = 0;

        // Input process arrival time and burst time
        for (int i = 0; i < n; i++) {
            System.out.println("Enter process " + (i + 1) + " arrival time: ");
            ar[i] = sc.nextInt();
            System.out.println("Enter process " + (i + 1) + " burst time: ");
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
        }

        // Sorting processes according to arrival times
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - (i + 1); j++) {
                if (ar[j] > ar[j + 1]) {
                    // Swap arrival times
                    temp = ar[j];
                    ar[j] = ar[j + 1];
                    ar[j + 1] = temp;
                    
                    // Swap burst times
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;
                    
                    // Swap process ids
                    temp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp;
                }
            }
        }

        // Finding completion, turnaround, and waiting times
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ct[i] = ar[i] + bt[i]; // first process completion time
            } else {
                if (ar[i] > ct[i - 1]) {
                    ct[i] = ar[i] + bt[i];
                } else {
                    ct[i] = ct[i - 1] + bt[i];
                }
            }
            ta[i] = ct[i] - ar[i];  // Turnaround time = completion time - arrival time
            wt[i] = ta[i] - bt[i];  // Waiting time = turnaround time - burst time
            avgwt += wt[i];         // Total waiting time
            avgta += ta[i];         // Total turnaround time
        }

        // Displaying process details
        System.out.println("\nPid \t Arrival \t Burst \t Completion \t Turnaround \t Waiting");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t\t" + ar[i] + "\t\t" + bt[i] + "\t\t" + ct[i] + "\t\t" + ta[i] + "\t\t" + wt[i]);
        }
        
        sc.close();
        
        // Displaying average waiting and turnaround times
        System.out.println("\nAverage waiting time: " + (avgwt / n)); 
        System.out.println("Average turnaround time: " + (avgta / n)); 
    }
}
