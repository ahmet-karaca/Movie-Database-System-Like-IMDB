import java.io.*;
import java.util.*;

class Director extends Artist{
    private String agent;

    public static LinkedHashMap<Integer, Director> directors = new LinkedHashMap<Integer,Director>();
    // A HashMap that holds the people id and the director object.

    public Director(String id, String name, String surname, String country, String agent) {
        super(id, name, surname, country);
        this.agent = agent;
    }
    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }


    public static void director() throws IOException{
        // A method that creates the director in peopleList as an object, adds this object to the directors HashMap.

        LinkedHashMap<Integer,Director> directors = new LinkedHashMap<Integer,Director>();
        for (String s : peopleList) {
            String[] wordList = s.toString().split("\t");
            if (wordList[0].equals("Director:")) {
                Director newDirector = new Director(wordList[1], wordList[2], wordList[3], wordList[4], wordList[5]);
                directors.put(Integer.parseInt(wordList[1]), newDirector);
            }
        }
        Director.directors = directors;
    }
}