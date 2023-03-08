package org.movie.manager.adapters;

import java.util.List;

public interface CSVDatabase {
    List<String[]> readData(String filePath);

    void writeDataToFile(Object[][] data,  String[] header);

}
