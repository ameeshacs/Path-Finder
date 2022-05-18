//Name: Ameesha Senanayake
//UoW ID: w18101205
//IIT ID:2019771

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Main {
    private final static Scanner sc = new Scanner(System.in);
    private static Parser parserFile; //parser object
    private static boolean loadSkip; //should skip load or not

    //Driver code
    public static void main(String[] args)
    {
        System.out.println("The path in a maze!\n");
        boolean label = true;
        while (label) {
            loadSkip = false;

            System.out.println("Press 1 to choose a file and load the path");
            System.out.println("Press 2 to exit the application");

            System.out.print("\nNumber : ");
            String loop = sc.nextLine();
            System.out.println();

            switch (loop) {
                case "1":
                    pathFinder();
                    break;
                case "2":
                    label=false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    //method to call the parser class
    private static void pathFinder() {

            if (loadSkip) {
                return;
            }

            boolean loadingError = false;
            System.out.println("Text file should be inside the 'input folder' in the 'src folder' and should be txt extension file");
            try {
                String fileName;
                do {
                    System.out.print("Input file name including the txt extension:  ");
                    fileName = sc.nextLine();
                } while ((!fileName.endsWith(".txt")));

                Parser parser = new Parser();
                parser.readFile("src/input_folder/" + fileName);
                parser.loadedLines();
                parser.loadedValues();
                if (!parser.isRead()) {
                    throw new Exception("File not loaded");
                }
                String inputFileName = parser.getFileName();
                File inputFile = parser.getFile();
                parserFile = parser;
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("\nError reading input file, please try again\n");
                loadingError = true;
            }

            if (!loadingError) {
                //variables to get the time taken to complete
                Instant startTime;
                Instant endTime ;
                Duration duration ;

                loadSkip = true;
                int[][] maze = parserFile.getMaze();
                int[] startPoint = parserFile.getStart();
                int[] endPoint = parserFile.getEnd();
                Distance distance = new Distance();

                startTime = Instant.now();

                System.out.println("No of lines: " + parserFile.getL().size());
                System.out.println("\nShortest Path:\n");

                System.out.println(distance.shortestPath(maze, startPoint, endPoint));

                System.out.println("Done! \n");

                endTime = Instant.now();
                duration = Duration.between(startTime, endTime);
                System.out.print("Time to complete: ");

                if (duration.toMillis() > 1000){
                    System.out.println(duration.toSeconds() + " seconds");
                    return;
                }

                System.out.println(duration.toMillis() + " milliseconds");
                System.out.println();
            }


    }
}
