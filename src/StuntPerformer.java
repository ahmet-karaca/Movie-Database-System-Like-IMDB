import java.io.*;
import java.util.*;

class StuntPerformer extends Performer{
    private String height;
    private String[] realActorsIds;

    public static LinkedHashMap<Integer,StuntPerformer> stuntPerformers = new LinkedHashMap<Integer,StuntPerformer>();
    // A HashMap that holds the people id and the stuntPerformer object.

    public StuntPerformer(String id, String name, String surname, String country, String height, String[] realActorsIds) {
        super(id, name, surname, country);
        this.height = height;
        this.realActorsIds = realActorsIds;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String[] getRealActorsIds() {
        return realActorsIds;
    }

    public void setRealActorsIds(String[] realActorsIds) {
        this.realActorsIds = realActorsIds;
    }


    public static void stuntPerformer() throws IOException{
        // A method that creates the stuntPerformers in peopleList as an object, adds this object to the stuntPerformers HashMap.

        LinkedHashMap<Integer,StuntPerformer> stuntPerformers = new LinkedHashMap<Integer,StuntPerformer>();
        for (String s : peopleList) {
            String[] wordList = s.toString().split("\t");
            if (wordList[0].equals("StuntPerformer:")) {
                String[] realActorsIds = wordList[6].split(",");
                StuntPerformer newStuntPerformer = new StuntPerformer(wordList[1], wordList[2], wordList[3], wordList[4], wordList[5], realActorsIds);
                stuntPerformers.put(Integer.parseInt(wordList[1]), newStuntPerformer);
            }
        }
        StuntPerformer.stuntPerformers = stuntPerformers;
    }
}