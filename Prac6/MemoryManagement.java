package Prac6;
import java.util.Scanner;
import java.util.Arrays;

public class MemoryManagement {
    
    public static void firstFit(int blockSizes[], int processSizes[], int numProcesses, int numBlocks) {
        int allocation[] = new int[numProcesses];
        Arrays.fill(allocation, -1); // Initially, no process is allocated any block
        boolean[] blockAllocated = new boolean[numBlocks]; // Track if block is fully allocated
        for (int i = 0; i < numProcesses; i++) {
            for (int j = 0; j < numBlocks; j++) {
                if (!blockAllocated[j] && blockSizes[j] >= processSizes[i]) {
                    allocation[i] = j;
                    blockAllocated[j] = true; // Mark the block as allocated
                    break;
                }
            }
        }
        printAllocation(allocation, "First Fit", numProcesses);
    }
    
    public static void nextFit(int blockSizes[], int processSizes[], int numProcesses, int numBlocks) {
        int allocation[] = new int[numProcesses];
        Arrays.fill(allocation, -1);
        int lastAllocatedIndex = 0; // Start from the first block
        for (int i = 0; i < numProcesses; i++) {
            boolean allocated = false;
            for (int j = lastAllocatedIndex; j < numBlocks; j++) {
                if (blockSizes[j] >= processSizes[i]) {
                    allocation[i] = j; // Allocate the block to the process
                    blockSizes[j] -= processSizes[i]; // Reduce the block size
                    lastAllocatedIndex = j + 1; // Move index to the next block for future allocations
                    allocated = true;
                    break;
                }
            }
            
            if (i == numProcesses - 1) {
                lastAllocatedIndex = numBlocks; // No more blocks to traverse
            }
        }
        printAllocation(allocation, "Next Fit (No Wrap Around)", numProcesses);
    }
   
    public static void bestFit(int blockSizes[], int processSizes[], int numProcesses, int numBlocks) {
        int allocation[] = new int[numProcesses];
        Arrays.fill(allocation, -1);
        boolean[] blockAllocated = new boolean[numBlocks];
        for (int i = 0; i < numProcesses; i++) {
            int bestIdx = -1;
            for (int j = 0; j < numBlocks; j++) {
                if (!blockAllocated[j] && blockSizes[j] >= processSizes[i]) {
                    if (bestIdx == -1 || blockSizes[j] < blockSizes[bestIdx]) {
                        bestIdx = j;
                    }
                }
            }
            if (bestIdx != -1) {
                allocation[i] = bestIdx;
                blockAllocated[bestIdx] = true; // Mark block as fully allocated
            }
        }
        printAllocation(allocation, "Best Fit", numProcesses);
    }
    
    public static void worstFit(int blockSizes[], int processSizes[], int numProcesses, int numBlocks) {
        int allocation[] = new int[numProcesses];
        Arrays.fill(allocation, -1);
        boolean[] blockAllocated = new boolean[numBlocks];
        for (int i = 0; i < numProcesses; i++) {
            int worstIdx = -1;
            for (int j = 0; j < numBlocks; j++) {
                if (!blockAllocated[j] && blockSizes[j] >= processSizes[i]) {
                    if (worstIdx == -1 || blockSizes[j] > blockSizes[worstIdx]) {
                        worstIdx = j;
                    }
                }
            }
            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                blockAllocated[worstIdx] = true; // Mark block as fully allocated
            }
        }
        printAllocation(allocation, "Worst Fit", numProcesses);
    }
    
    private static void printAllocation(int allocation[], String method, int numProcesses) {
        System.out.println("\nMemory Allocation using " + method + ":");
        for (int i = 0; i < numProcesses; i++) {
            if (allocation[i] != -1) {
                System.out.println("Process " + (i + 1) + " allocated to Block " + (allocation[i] + 1));
            } else {
                System.out.println("Process " + (i + 1) + " not allocated");
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of memory blocks: ");
        int numBlocks = sc.nextInt();
        System.out.print("Enter number of processes: ");
        int numProcesses = sc.nextInt();
        int blockSizes[] = new int[numBlocks];
        int processSizes[] = new int[numProcesses];
        
        System.out.println("Enter sizes of the memory blocks: ");
        for (int i = 0; i < numBlocks; i++) {
            System.out.print("Block " + (i + 1) + " size: ");
            blockSizes[i] = sc.nextInt();
        }
        
        System.out.println("Enter sizes of the processes: ");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Process " + (i + 1) + " size: ");
            processSizes[i] = sc.nextInt();
        }
        
        int blockSizesFirstFit[] = blockSizes.clone();
        int blockSizesNextFit[] = blockSizes.clone();
        int blockSizesBestFit[] = blockSizes.clone();
        int blockSizesWorstFit[] = blockSizes.clone();
        
        firstFit(blockSizesFirstFit, processSizes, numProcesses, numBlocks);
        nextFit(blockSizesNextFit, processSizes, numProcesses, numBlocks);
        bestFit(blockSizesBestFit, processSizes, numProcesses, numBlocks);
        worstFit(blockSizesWorstFit, processSizes, numProcesses, numBlocks);
    }
}
















// Enter number of memory blocks: 5
// Enter number of processes: 4
// Enter sizes of the memory blocks: 
// Block 1 size: 100
// Block 2 size: 500
// Block 3 size: 200
// Block 4 size: 300
// Block 5 size: 600
// Enter sizes of the processes: 
// Process 1 size: 212
// Process 2 size: 417
// Process 3 size: 112
// Process 4 size: 426

// Memory Allocation using First Fit:
// Process 1 allocated to Block 2
// Process 2 allocated to Block 5
// Process 3 allocated to Block 3
// Process 4 not allocated

// Memory Allocation using Next Fit (No Wrap Around):
// Process 1 allocated to Block 2
// Process 2 allocated to Block 5
// Process 3 not allocated
// Process 4 not allocated

// Memory Allocation using Best Fit:
// Process 1 allocated to Block 4
// Process 2 allocated to Block 2
// Process 3 allocated to Block 3
// Process 4 allocated to Block 5

// Memory Allocation using Worst Fit:
// Process 1 allocated to Block 5
// Process 2 allocated to Block 2
// Process 3 allocated to Block 4
// Process 4 not allocated