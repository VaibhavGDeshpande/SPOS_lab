package Prac7;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FIFOPageReplacement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of frames
        System.out.print("Enter number of frames: ");
        int framesCount = scanner.nextInt();

        // Input number of pages
        System.out.print("Enter number of pages: ");
        int pagesCount = scanner.nextInt();

        // Input the page reference string
        int[] pages = new int[pagesCount];
        System.out.println("Enter the page reference string: ");
        for (int i = 0; i < pagesCount; i++) {
            pages[i] = scanner.nextInt();
        }

        // FIFO page replacement algorithm
        Queue<Integer> queue = new LinkedList<>();
        int[] frame = new int[framesCount];
        int pageFaults = 0;

        // Initialize the frame array with -1 indicating empty
        for (int i = 0; i < framesCount; i++) {
            frame[i] = -1;
        }

        System.out.println("\nStep\tPage\tFrames\t\tPage Fault\tComment");

        for (int i = 0; i < pagesCount; i++) {
            int currentPage = pages[i];

            // Check if the page is already in the frame
            boolean pageFound = false;
            for (int j = 0; j < framesCount; j++) {
                if (frame[j] == currentPage) {
                    pageFound = true;
                    break;
                }
            }

            String comment = "";

            // If the page is not in the frame (page fault)
            if (!pageFound) {
                pageFaults++;

                // If the frame is full, remove the first page from the queue
                if (queue.size() == framesCount) {
                    int pageToRemove = queue.poll();
                    for (int j = 0; j < framesCount; j++) {
                        if (frame[j] == pageToRemove) {
                            frame[j] = currentPage;
                            comment = "Replaced " + pageToRemove + " (FIFO)";
                            break;
                        }
                    }
                } else {
                    // Insert the new page in the first empty frame
                    for (int j = 0; j < framesCount; j++) {
                        if (frame[j] == -1) {
                            frame[j] = currentPage;
                            comment = "Loaded into frame";
                            break;
                        }
                    }
                }

                // Add the current page to the queue
                queue.add(currentPage);
            } else {
                comment = "Page Hit";
            }

            // Print the current step, page, frame, and comment
            System.out.print((i + 1) + "\t" + currentPage + "\t");
            for (int j = 0; j < framesCount; j++) {
                if (frame[j] != -1) {
                    System.out.print(frame[j] + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println("\t\t" + (pageFound ? "No" : "Yes") + "\t\t" + comment);
        }

        System.out.println("Total Page Faults = " + pageFaults);
    }
}



















// Enter number of frames: 3
// Enter number of pages: 15
// Enter the page reference string:
// 7
// 0
// 1
// 2
// 0
// 3 
// 0
// 4
// 2
// 3
// 0
// 1
// 2
// 0
// 1

// Step    Page    Frames          Page Fault      Comment
// 1       7       7 - -           Yes             Loaded into frame
// 2       0       7 0 -           Yes             Loaded into frame
// 3       1       7 0 1           Yes             Loaded into frame
// 4       2       2 0 1           Yes             Replaced 7 (FIFO)
// 5       0       2 0 1           No              Page Hit
// 6       3       2 3 1           Yes             Replaced 0 (FIFO)
// 7       0       2 3 0           Yes             Replaced 1 (FIFO)
// 8       4       4 3 0           Yes             Replaced 2 (FIFO)
// 9       2       4 2 0           Yes             Replaced 3 (FIFO)
// 10      3       4 2 3           Yes             Replaced 0 (FIFO)
// 11      0       0 2 3           Yes             Replaced 4 (FIFO)
// 12      1       0 1 3           Yes             Replaced 2 (FIFO)
// 13      2       0 1 2           Yes             Replaced 3 (FIFO)
// 14      0       0 1 2           No              Page Hit
// 15      1       0 1 2           No              Page Hit