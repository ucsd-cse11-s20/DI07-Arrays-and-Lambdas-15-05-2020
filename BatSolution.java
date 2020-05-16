import java.nio.file.*;
import java.io.IOException;

class FileHelper {
  /*
    Takes a path to a file and returns all of the lines in the file as an
    array of strings, printing an error if it failed.
  */
  static String[] getLines(String path) {
    try {
      return Files.readAllLines(Paths.get(path)).toArray(String[]::new);
    }
    catch(IOException e) {
      System.err.println("Error reading file " + path + ": " + e);
      return new String[]{"Error reading file " + path + ": " + e};
    }
  }
}

class Bat {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the path to a text file.");            
        }
        else {
            String filePath = args[0];
            String[] lines = FileHelper.getLines(filePath);

            for (String line: lines) {
                System.out.println(line);
            }
        }    
    }
}