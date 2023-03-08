//
// Adopted/inspired by the lecture Software Engineering 4th semester DHBW 2022 by Mr. Lutz
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package org.movie.manager.plugin.database;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CSVDatabase {
    public static final char DEFAULT_DELIMITER = ';';
    public static final char DEFAULT_COMMENT = '#';
    private final String csvFileName;
    private InputStream ioStream = null;
    private File csvFile = null;
    public CSVDatabase(String csvFileName) {
        this.csvFileName= csvFileName;
        this.csvFile = this.checkFile(this.csvFileName, true);
    }

    private final File checkFile(String fileName, boolean create) throws IllegalArgumentException {
        if (fileName != null && fileName.length() != 0) {
            File csvFile = new File(fileName);
            if (!csvFile.exists()) {
                if (!create) {
                    throw new IllegalArgumentException("File does not exist! If it should be created automatically, use argument value create=true");
                }

                try {
                    csvFile.createNewFile();
                } catch (IOException var5) {
                    throw new IllegalArgumentException("File could not be created: " + var5.getMessage());
                }
            }

            return csvFile;
        } else {
            throw new IllegalArgumentException("File name must be given!");
        }
    }

    public final void writeDataToFile(Object[][] data, String[] header) throws IOException, IllegalArgumentException {
        Objects.requireNonNull(data, "Data must be given!");
        this.writeDataToFile(Arrays.asList(data), header);
    }

    public final void writeDataToFile(Object[][] data, String[] header, char delimiter, char commentChar, String encodingName) throws IOException, IllegalArgumentException {
        Objects.requireNonNull(data, "Data must be given!");
        this.writeDataToFile(Arrays.asList(data), header, delimiter, commentChar, encodingName);
    }

    public final void writeDataToFile(List<Object[]> data, String[] header) throws IOException, IllegalArgumentException {
        this.writeDataToFile(data, header, ';', '#', "UTF-8");
    }

    public final void writeDataToFile(List<Object[]> data, String[] header, char delimiter, char commentChar, String encodingName) throws IOException, IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data must be given!");
        } else {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.csvFile), encodingName));
            if (data.get(0) == null) {
                writer.close();
                throw new IllegalArgumentException("first data line is not given!");
            } else {
                int arrLen;
                if (header != null && header.length > 0) {
                    try {
                        writer.write(commentChar);
                        arrLen = header.length;

                        for(int i = 0; i < arrLen; ++i) {
                            if (header[i] != null) {
                                writer.write(header[i]);
                            }

                            if (arrLen - i > 1) {
                                writer.write(delimiter);
                            }
                        }

                        writer.newLine();
                    } catch (IOException var15) {
                        writer.flush();
                        writer.close();
                        throw var15;
                    }
                }

                try {
                    arrLen = ((Object[])data.get(0)).length;
                    int finalArrLen = arrLen;
                    data.forEach((e) -> {
                        try {
                            for(int i = 0; i < finalArrLen; ++i) {
                                if (e[i] != null) {
                                    writer.write(e[i].toString());
                                }

                                if (finalArrLen - i > 1) {
                                    writer.write(delimiter);
                                }
                            }

                            writer.newLine();
                        } catch (IOException var5) {
                            var5.printStackTrace();
                        }

                    });
                } catch (Exception var13) {
                    throw var13;
                } finally {
                    writer.flush();
                    writer.close();
                }

            }
        }
    }

    private final File checkFile(String fileName) throws IllegalArgumentException {
        if (fileName != null && fileName.length() != 0) {
            File csvFile = new File(fileName);
            if (!csvFile.exists()) {
                throw new IllegalArgumentException("File '" + fileName + "' does not exist");
            } else {
                return csvFile;
            }
        } else {
            throw new IllegalArgumentException("File name must be given!");
        }
    }

    public final List<String[]> readData() throws IOException {
        return this.readData(0, ';', '#', "UTF-8");
    }

    public final List<String[]> readData(int expectedColumns, char delimiter, char comment, String encodingName) throws IOException {
        String encdngNm = encodingName != null && !encodingName.isEmpty() ? encodingName : "UTF-8";
        List<String[]> allLines = new ArrayList();
        BufferedReader reader = null;
        if (this.csvFile != null) {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.csvFile), encdngNm));
        } else {
            if (this.ioStream == null) {
                throw new IllegalArgumentException("either a file or an IOStream must be given! ");
            }

            reader = new BufferedReader(new InputStreamReader(this.ioStream, encdngNm));
        }

        try {
            String line = "";
            String[] lineElements = null;
            int numLinesRead = 0;

            while(line != null) {
                line = reader.readLine();
                ++numLinesRead;
                if (line == null) {
                    break;
                }

                if (!line.startsWith("" + comment) && line.length() != 0) {
                    lineElements = line.split("" + delimiter);
                    if (expectedColumns > 0 && expectedColumns != lineElements.length) {
                        String[] sArr = new String[expectedColumns];
                        int i;
                        if (expectedColumns <= lineElements.length) {
                            for(i = 0; i < expectedColumns; ++i) {
                                sArr[i] = lineElements[i] == null ? "" : lineElements[i];
                            }
                        } else if (expectedColumns > lineElements.length) {
                            System.arraycopy(lineElements, 0, sArr, 0, lineElements.length);

                            for(i = lineElements.length; i < expectedColumns; ++i) {
                                sArr[i] = "";
                            }
                        }

                        lineElements = sArr;
                    }

                    allLines.add(lineElements);
                }
            }
        } catch (IOException var16) {
            throw var16;
        } finally {
            reader.close();
            if (this.ioStream != null) {
                this.ioStream.close();
            }

        }

        return allLines;
    }

    public final String readFirstCommentLineFromFile() throws IOException {
        return this.readFirstCommentLineFromFile("#", "UTF-8");
    }

    public final String[] readHeader(char delimiter, String comment, String encodingName) throws IOException {
        String headerLine = this.readFirstCommentLineFromFile(comment, encodingName);
        String[] lineElements = null;
        if (headerLine != null) {
            headerLine = headerLine.substring(1);
            lineElements = headerLine.split("" + delimiter);
        }

        return lineElements;
    }

    public final String readFirstCommentLineFromFile(String comment, String encodingName) throws IOException {
        String cmnt = comment != null && !comment.isEmpty() ? comment : "#";
        String encdngNm = encodingName != null && !encodingName.isEmpty() ? encodingName : "UTF-8";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.csvFile), encdngNm));

            String var7;
            label53: {
                try {
                    String line = "";

                    while(line != null) {
                        line = reader.readLine();
                        if (line == null) {
                            break;
                        }

                        if (!line.isEmpty() && line.startsWith(cmnt)) {
                            var7 = line;
                            break label53;
                        }
                    }
                } catch (Throwable var9) {
                    try {
                        reader.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }

                    throw var9;
                }

                reader.close();
                return null;
            }

            reader.close();
            return var7;
        } catch (IOException var10) {
            throw var10;
        }
    }
}
