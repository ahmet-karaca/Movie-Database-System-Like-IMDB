import java.io.*;
import java.util.*;

class Performer extends Artist{

    public static LinkedHashMap<Integer,Performer> performers = new LinkedHashMap<Integer,Performer>();
    // A HashMap that holds the people id and the performer object.

    public Performer(String id, String name, String surname, String country) {
        super(id, name, surname, country);
    }


    public static void performer() throws IOException{
        // A method that creates the performers in peopleList as an object, adds this object to the performers HashMap.

        LinkedHashMap<Integer,Performer> performers = new LinkedHashMap<Integer,Performer>();
        for (String s : peopleList) {
            String[] wordList = s.toString().split("\t");
            if ((wordList[0].equals("Actor:")) || (wordList[0].equals("ChildActor:")) || (wordList[0].equals("StuntPerformer:"))) {
                Performer newPerformer = new Performer(wordList[1], wordList[2], wordList[3], wordList[4]);
                performers.put(Integer.parseInt(wordList[1]), newPerformer);
            }
        }
        Performer.performers = performers;
    }
}