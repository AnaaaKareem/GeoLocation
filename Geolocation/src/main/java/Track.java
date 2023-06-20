import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Kairm Khater
 */

public class Track {

    //Create ArrayList of points
  ArrayList<Point> points = new ArrayList<Point>();

  // TODO: Create a stub for the constructor
    public Track() {

    }

    // Overlaod Constructor with a File Object
    public Track(String filename) throws IOException {
        File file = new File(filename);
        if (file == null) {
            throw new IOException("File does not exist");
        }
        else {
            readFile(filename);
        }
    }

  // TODO: Create a stub for readFile()
    public void readFile(String filename) throws IOException {

        // Clear the ArrayList if not empty clear it
        if (!points.isEmpty()) {
            points.clear();
        }

        // Read the file
        File file = new File(filename);
        Scanner scan = new Scanner(file);

        // Skip the header in the CSV file
        scan.nextLine();

        // Gather data then split it into columns
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] column = line.split(",");
            if (column.length != 4) {
                throw new GPSException("Invalid Number Of Columns");
            }
                // Split the CSV columns and Parse the data to variables
                ZonedDateTime time = ZonedDateTime.parse(column[0]);
                double longitude = Double.parseDouble(column[1]);
                double latitude = Double.parseDouble(column[2]);
                double elevation = Double.parseDouble(column[3]);
                // Create a new point object
                Point point = new Point(time, longitude, latitude, elevation);
                // Add the point to the ArrayList
                points.add(point);
        }
    }

  // TODO: Create a stub for add()
  public void add(Point point) {
      points.add(point);
  }

  // TODO: Create a stub for get()
  public Point get(int index) {
      if (points.isEmpty()) {
          throw new GPSException("Point Parameters are Empty");
      }
        if (index < 0 || index > 3) {
            throw new GPSException("Parameters Entered Out Of Bounds");
        }
        return points.get(index); // return the point based on the index
  }

  // TODO: Create a stub for size()
  public int size() {
      return points.size();
  }

  // TODO: Create a stub for lowestPoint()
  public Point lowestPoint() {
      if (points.isEmpty()) {
          throw new GPSException("Empty Track");
      }
      else {
          // Create a point object
          Point lowestPoint = points.get(0);
          int i;
          // Check the list for the lowest point
          for (i = 0; i < points.size(); i++) {
              if (lowestPoint.getElevation() > points.get(i).getElevation()) {
                  lowestPoint = points.get(i);
              }
          }
          return lowestPoint; // return the lowest point
      }
}
  // TODO: Create a stub for highestPoint()
    public Point highestPoint() {
        if (points.isEmpty()) {
            throw new GPSException("Empty Track");
        }
        else {
            // Create a point object
            Point highestPoint = points.get(0);
            int i;
            // Check the list for the highest point
            for (i = 0; i < points.size(); i++) {
                if (highestPoint.getElevation() < points.get(i).getElevation()) {
                    highestPoint = points.get(i);
                }
            }
            return highestPoint; // return the highest point
        }
    }

  // TODO: Create a stub for totalDistance()
    public double totalDistance() {
        // Check if there are at least 2 points
        if (points.size() < 2) {
            throw new GPSException("Not Enough Points To Compute Total Distance");
        }
        int i;
        double totalPointDistance = 0;
        // Add the distance between each point using greatCircleDistance method
        for (i = 0; i < points.size() - 1; i++) {
            totalPointDistance += Point.greatCircleDistance(points.get(i), points.get(i+1));
        }
        return totalPointDistance; // Return total distance
    }

  // TODO: Create a stub for averageSpeed()
  public double averageSpeed() {
      // Check if there are at least 2 points
      if (points.size() < 2) {
          throw new GPSException("Not Enough Points To Compute Average Speed");
      }
      // Get the seconds from ZonedDateTime by getTIme() method
      double totalTime = ChronoUnit.SECONDS.between(points.get(0).getTime(), points.get(points.size()-1).getTime());
      return totalDistance()/totalTime; // Return average speed in m/s
  }

    // TODO: Create a stub for writeKML()
    public void writeKML(String filename) {
        int counter= 0;
        try {
            // Get csv filename
            File file = new File(filename);
            Scanner scan = new Scanner(file);
            scan.nextLine(); // Skip header

            // Create the KML file
            FileWriter kmlWriter = new FileWriter(filename); // FileWriter to write to the KML file
            String line = scan.nextLine(); // Skip header
            String[] column = line.split(",");
            if (column.length != 4) {
                throw new GPSException("Invalid Number Of Columns");
            }
            // Start writing the KML file
            kmlWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
                    "<Document>\n" +
                    "  <name>My Map</name>\n" +
                    "  <description>My KML Map</description>\n" +
                    "\n" +
                    "  <Style id=\"marker\">\n" +
                    "    <IconStyle>\n" +
                    "      <Icon>\n" +
                    "        <href>http://maps.google.com/mapfiles/kml/pushpin/red-pushpin.png</href>\n" +
                    "      </Icon>\n" +
                    "      <hotSpot x=\"20\" y=\"2\" xunits=\"pixels\" yunits=\"pixels\"/>\n" +
                    "    </IconStyle>\n" +
                    "  </Style>\n" +
                    "\n" +
                    "  <Folder>\n" +
                    "    <name>My Points</name>");

            kmlWriter.write("    <!-- Add Placemarks for each point -->");
            // Place the points in the KML file
            for (int i = 0; i < points.size(); i++) {
                counter += 1;
                kmlWriter.write("    <Placemark>\n" +
                        "      <name>Point" + String.valueOf(counter) + "</name>\n" +
                        "      <description>Current Point Number Is: " + String.valueOf(counter)  + "</description>\n" +
                        "      <styleUrl>#marker</styleUrl>\n" +
                        "      <Point>\n" +
                        "        <coordinates>" + String.valueOf(points.get(i).getLongitude()) +
                        "," + String.valueOf(points.get(i).getLatitude()) + "</coordinates>\n" +
                        "      </Point>\n" +
                        "    </Placemark>");
            }
            kmlWriter.write("  </Folder>\n");
            kmlWriter.write("</Document>");
            kmlWriter.write("</kml>");
            kmlWriter.close(); // Close the KML file
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}


