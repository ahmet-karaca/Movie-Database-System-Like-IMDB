import java.io.*;
import java.util.*;

class Writer extends Artist{
    private String writingType;

    public static LinkedHashMap<Integer,Writer> writers = new LinkedHashMap<Integer,Writer>();
    // A HashMap that holds the people id and the writer object.

    public Writer(String id, String name, String surname, String country, String writingType) {
        super(id, name, surname, country);
        this.writingType = writingType;
    }

    public String getWritingType() {
        return writingType;
    }

    public void setWritingType(String writingType) {
        this.writingType = writingType;
    }


    public static void writer() throws IOException{
        // A method that creates the writers in peopleList as an object, adds this object to the writers HashMap.

        LinkedHashMap<Integer,Writer> writers = new LinkedHashMap<Integer,Writer>();
        for (String s : peopleList) {
            String[] wordList = s.toString().split("\t");
            if (wordList[0].equals("Writer:")) {
                Writer newWriter = new Writer(wordList[1], wordList[2], wordList[3], wordList[4], wordList[5]);
                writers.put(Integer.parseInt(wordList[1]), newWriter);
            }
        }
        Writer.writers = writers;
    }
}