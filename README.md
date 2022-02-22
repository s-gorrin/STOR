# STOR: Style Organizer

STOR is an application for managing a closet and organizing outfits.

## Dependencies
Running in Java 11 or higher.  
GUI built on [JavaFX SDK 17.0.2](https://gluonhq.com/products/javafx/).  
Serializing to JSON with [GSON 2.8.5](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.5). - depreciated   
SQLite managed with [SQLite-JDBC 3.36.0.3](https://github.com/xerial/sqlite-jdbc/)  
Testing with [JUnit 4.13.2](https://mvnrepository.com/artifact/junit/junit/4.13.2).

## Usage
Execution begins at `FXController.main()` to launch the GUI, OR in the `Main` class with this configuration:

The following should be added to the `run configurations` in `VM Options`:  
`--module-path "<path/to/javafx>javafx-sdk-17.0.2/lib" --add-modules=javafx.base,javafx.controls,javafx.graphics,javafx.media`  
Please note that the version of JavaFX 17.0.2 should be whatever is appropriate for your system.

A JavaFX GUI interface allows a user to add clothing to the closet. Clothing is identified by a variety of traits, and tracked by a variety of factors, including age and uses. An outfit picker shows the user clothing options and offers compatible clothes based on the initial choice.

## Provenance
This project was created by Seth Gorrin in Spring 2022 for CS 622 at Boston University MET.

## Testing
Due to the limitations, GUI elements are not included in JUnit tests.

## License
No license, but please don't plagiarize this for any school projects.