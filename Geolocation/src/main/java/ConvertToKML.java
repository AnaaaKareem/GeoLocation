import java.io.IOException;

/**
 * Program to general a KML file from GPS track data stored in a file,
 * for the Advanced task of COMP1721 Coursework 1.
 *
 * @author Karim Khater
 */
public class ConvertToKML {
  public static void main(String[] args) {
    // OPTIONAL: implement the ConvertToKML application here
    if (args.length == 0) {
      System.out.println("Invalid Number of Arguments");
      System.exit(1);
    }
    else {
      try {
        // Create a new Track object, input the file name and write the KML file
        String fileName = args[0];
        Track track = new Track(fileName);
        track.writeKML(fileName);
      }
      catch (IOException e){
        e.printStackTrace();
      }
    }
  }
}
