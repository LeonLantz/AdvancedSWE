//
// Adopted/inspired by the lecture Software Engineering 4th semester DHBW 2022 by Mr. Lutz
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package org.movie.manager.plugin.csvdatabase;
import org.movie.manager.adapters.CSVDatabase;

import java.io.IOException;
import java.util.List;

public class CSVDatabaseManager implements CSVDatabase {
    private final String CSV_FOLDER_PATH;

    public CSVDatabaseManager(String CSV_FOLDER_PATH) {
        this.CSV_FOLDER_PATH = CSV_FOLDER_PATH;
    }

    public List<String[]> readData(String filePath){
        CSVReader csvReader = new CSVReader(this.CSV_FOLDER_PATH + filePath);
        try {
            return csvReader.readData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeDataToFile(Object[][] data,  String[] header){
        CSVWriter csvWriter = new CSVWriter(this.CSV_FOLDER_PATH, true);
        try {
            csvWriter.writeDataToFile(data,  header);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
