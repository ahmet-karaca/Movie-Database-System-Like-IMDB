import java.io.*;
import java.util.*;

class ChildActor extends Performer{
    private String age;

    public static LinkedHashMap<Integer,ChildActor> childActors = new LinkedHashMap<Integer,ChildActor>();
    // A HashMap that holds the people id and the childActor object.

    public ChildActor(String id, String name, String surname, String country, String age) {
        super(id, name, surname, country);
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public static void childActor() throws IOException{
        // A method that creates the childActors in peopleList as an object, adds this object to the childActors HashMap.

        LinkedHashMap<Integer,ChildActor> childActors = new LinkedHashMap<Integer,ChildActor>();
        for (String s : peopleList) {
            String[] wordList = s.toString().split("\t");
            if (wordList[0].equals("ChildActor:")) {
                ChildActor newChildActor = new ChildActor(wordList[1], wordList[2], wordList[3], wordList[4], wordList[5]);
                childActors.put(Integer.parseInt(wordList[1]), newChildActor);
            }
        }
        ChildActor.childActors = childActors;
    }
}