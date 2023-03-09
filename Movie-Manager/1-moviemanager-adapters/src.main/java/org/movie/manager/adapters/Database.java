package org.movie.manager.adapters;

import java.util.List;

public interface Database {
    List<String[]> readData(String filePath);

    void saveData(List<Object[]> data, String[] header);

}
