# Geo Location Checker and Plotter

Geo Location Checker and Plotter is a Java project that uses GPS data from CSV files to create and analyze GPS tracks. The project can provide information on a GPS track, such as the number of points, the lowest and highest points, the total distance, and the average speed. The project can also generate a KML file from a GPS track that can be viewed on Google Earth.

## How to compile and run

The project uses Gradle as a build tool and can be compiled and run using the following commands:

- `gradlew build`: compiles the project and checks for errors
- `gradlew run`: runs the project with the specified arguments

The project has two main classes: TrackInfo and ConvertToKML. Each class takes one argument: the name of the CSV file that contains the GPS data. The CSV files are located in the data folder under GeoLocation\data\[Files.csv].

For example, to run the TrackInfo class on the file data/leeds-centre.csv, use the following command:

`gradlew run --args="TrackInfo data/leeds-centre.csv"`

To run the ConvertToKML class on the same file, use the following command:

`gradlew run --args="ConvertToKML data/leeds-centre.csv"`

The KML file will be generated in the same folder as the CSV file.

## Author and license

This project was made by Karim Amr Elsayed Khater and Nick Efford. It is not licensed and is free to use and modify.

## Known issues and future work

There are no known issues for this project.

Some possible future work for this project are:

- Adding more A graph using JavaFX
