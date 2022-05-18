//Name: Ameesha Senanayake
//UoW ID: w18101205
//IIT ID:2019771

//parser file to the read the files
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.nio.file.Files;
import java.nio.charset.Charset;

public class Parser {
    Scanner sc = null;
    private final ArrayList<String> l = new ArrayList<>(); //lines
    private boolean read; //has read file
    private int[] start; //starting point
    private int[] end; //ending point
    private int[][] maze; //matrix
    private boolean loaded; //has loaded file
    private File inputFile; //text file

    //get method for starting pont
    public int[] getStart() {
        if (loaded()) {
            return this.start;
        }
        return null;
    }

    //get method for ending point
    public int[] getEnd() {
        if (loaded()) {
            return this.end;
        }
        return null;
    }

    //get method to get the array of maze
    public int[][] getMaze() {
        if (loaded()) {
            return this.maze;
        }
        return null;
    }

    //to check whether it is read
    public Boolean isRead() {
        return this.read;
    }

    //method to load the lines in the maze
    public void loadedLines() throws IOException {
        if (this.read)
        {
            l.addAll(Files.readAllLines(inputFile.toPath(), Charset.defaultCharset()));
            this.loaded = true;
        }
    }

    //method to read the text file
    public void readFile(String path) throws FileNotFoundException {
        File inputFile = new File(path);

        //if the file cannot be found
        if (inputFile.length() == 0) {
            throw new FileNotFoundException("File " + path + " does not exist");
        }

        this.inputFile = inputFile;
        this.read = true;
    }

    //method to get the file name
    public String getFileName() {
        if (loaded) {
            return inputFile.getName();
        }
        return null;
    }

    //get method for lines
    public ArrayList<String> getL() {
        if (this.read) {
            return this.l;
        }
        return null;
    }

    //get method for file
    public File getFile() {
        if (this.read) {
            return inputFile;
        }
        return null;

    }

    //to check whether the file is loaded
    public Boolean loaded() {
        if (this.isRead()) {
            return this.loaded;
        }
        return null;
    }

    //method to load the values in the file
    public boolean loadedValues(){
        ArrayList<String> lines = this.getL();
        this.sc = new Scanner(this.getL().get(0));

        //size of the floor in the game
        int size = this.getL().get(0).trim().length();
        this.maze = new int[size][lines.size()];

        //count of the lines
        int count = 0;
        for (int i = 0; i < lines.size(); i++){
            String line = lines.get(i);
            int[] floor = new int[size];
            this.sc = new Scanner(line);

            //counter
            int i1 = 0;
            //change the content to binary
            //1 : not allowed to pass through
            //0 : allowed to pass through
            while (sc.hasNext()) {
                if (i1 < size){
                    String line1 = sc.nextLine();
                    //replace "0" with 1
                    //replace "." with 0
                    line1 = line1.replace("0", "1");
                    line1 = line1.replace(".", "0");

                    //replace the "S" with 0
                    if(line1.contains("S")){
                        this.start = new int[]{count, line1.indexOf("S")};
                        line1 = line1.replace("S", "0");
                    }

                    //replace "F" with 0
                    if(line1.contains("F")){
                        this.end = new int[]{count, line1.indexOf("F")};
                        line1 = line1.replace("F", "0");
                    }
                    String[] string = line1.split("");

                    for (int j = 0; j < string.length; j++) {
                        floor[j] = Integer.valueOf(string[j]);
                    }
                    count++;
                }
                i1++;
            }
            sc.close();
            sc = null;
            maze[i] = floor;
        }


        return true;
    }
}
