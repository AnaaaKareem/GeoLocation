import java.io.IOException;

/**
 * Program to provide information on a GPS track stored in a file.
 *
 * @author Karim Khater
 */
public class TrackInfo {
  public static void main(String[] args) {
    // TODO: Implement TrackInfo application here
    if (args.length != 1) {
      System.out.println("Invalid Number of Arguments");
      System.exit(1);
    }
    else {
      try {
        String fileName = args[0];
        Track track = new Track(fileName);
        System.out.println(track.size() + " points in track");
        System.out.println("Lowest point is " + track.lowestPoint());
        System.out.println("Highest point is " + track.highestPoint());
        System.out.printf("Total distance = %.3f km%n", track.totalDistance()/1000);
        System.out.printf("Average speed = %.3f m/s%n", track.averageSpeed());
      }
      catch (IOException e){
        e.printStackTrace();
      }
    }
  }
}