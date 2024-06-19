package codes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RoundRobin {
    int quantum;
    int totalProcesses;
    Process[] processes;

    public RoundRobin(int totalProcesses) {
        this.totalProcesses = totalProcesses;
        processes = new Process[totalProcesses];
    }


    public static List<String> executeRRAlgorithm(int numProcesses, String[] processName, int[] arrivalTimes, int[] burstTimes, int quantum) {
        List<String> executionSteps = new ArrayList<>();
        int time = 0;
        

        while (true) {
            boolean done = true;
            for (int i = 0; i < numProcesses; i++) {
                if (arrivalTimes[i] <= time && burstTimes[i] > 0) {
                    done = false;
                    if (burstTimes[i] > quantum) {
                        executionSteps.add("Executing process " + processName[i] + " at time " + time);
                        time += quantum;
                        burstTimes[i] -= quantum;
                    } else {
                        executionSteps.add("Executing process " + processName[i] + " at time " + time);
                        time += burstTimes[i];
                        burstTimes[i] = 0;
                        executionSteps.add("Process " + processName[i] + " is completed at time " + time);
                    }
                }
            }
            if (done)
                break;
            time++; // Time increment when no process is scheduled
        }
        return executionSteps;
    }

}