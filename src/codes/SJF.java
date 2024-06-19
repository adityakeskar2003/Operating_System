package codes;
import java.util.*;

class SJFScheduler {
    Process[] processes;
    int totalProcesses;

    SJFScheduler(int totalProcesses) {
        this.totalProcesses = totalProcesses;
        this.processes = new Process[totalProcesses];
        for (int i = 0; i < totalProcesses; i++) {
            this.processes[i] = new Process("", 0, 0); // Initialize with default values
        }
    }
        
    
    public List<String> executeSJFAlgorithm(int numProcesses,String[] processName, int[] arrivalTimes, int[] burstTimes) {
        List<String> executionSteps = new ArrayList<>();
        int totalProcesses = numProcesses;
        System.out.println();
        Process[] processes = new Process[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            processes[i] = new Process(processName[i], arrivalTimes[i], burstTimes[i]);
        }

        int time = 0;
        int totalCompleted = 0;

        while (totalCompleted < totalProcesses) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            System.out.println();
            for (int i = 0; i < totalProcesses; i++) {
                if (processes[i].arrivalTime <= time && processes[i].remainingTime < min && !processes[i].done) {
                    min = processes[i].remainingTime;
                    index = i;
                }
            }


            if (index != -1) {
                executionSteps.add("Executing process " + processes[index].name + " at time " + (time));
                processes[index].remainingTime--;

                if (processes[index].remainingTime == 0) {
                    executionSteps.add("Process " + processes[index].name + " is completed at time " + (time));
                    processes[index].turnaroundTime = processes[index].completionTime - processes[index].arrivalTime;
                    processes[index].waitingTime = processes[index].turnaroundTime - processes[index].burstTime;
                    processes[index].done = true;
                    totalCompleted++;
                }
            } else {
                executionSteps.add("No process is ready at time " + (time));
            }
            time++;
        }
        return executionSteps;
    }

}

