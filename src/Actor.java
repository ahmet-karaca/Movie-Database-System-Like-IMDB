import java.io.*;
import java.util.*;

class Actor extends Performer{
    private String height;

    public static LinkedHashMap<Integer,Actor> actors = new LinkedHashMap<Integer,Actor>();
    // A HashMap that holds the people id and the actor object.

    public Actor(String id, String name, String surname, String country, String height) {
        super(id, name, surname, country);
        this.height = height;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }


    public static void actor() throws IOException{
        // A method that creates the actors in peopleList as an object, adds this object to the actors HashMap.

        LinkedHashMap<Integer,Actor> actors = new LinkedHashMap<Integer,Actor>();
        for (String s : peopleList) {
            String[] wordList = s.toString().split("\t");
            if (wordList[0].equals("Actor:")) {
                Actor newActor = new Actor(wordList[1], wordList[2], wordList[3], wordList[4], wordList[5]);
                actors.put(Integer.parseInt(wordList[1]), newActor);
            }
        }
        Actor.actors = actors;
    }
}