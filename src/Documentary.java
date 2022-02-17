import java.io.*;
import java.util.*;

class Documentary extends Films{
    private String releaseDate;

    public static LinkedHashMap<Integer,Documentary>  documentaries = new LinkedHashMap<Integer,Documentary>();
    // A HashMap that holds the film id and the documentary object.

    public Documentary(String id, String filmTitle, String language, String[] directors, String runTime, String country, String[] cast,LinkedHashMap<Integer,String > rate, String releaseDate) {
        super(id, filmTitle, language, directors, runTime, country, cast, rate);
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    public static void documentary() throws IOException {
        // A method that creates the documentary in FilmList as an object, adds this object to the documentaries HashMap.

        ArrayList<String> filmList = new ArrayList<String>();

        LinkedHashMap<Integer,Documentary> documentaries = new LinkedHashMap<Integer,Documentary>();
        for (String s : Films.filmList) {
            String[] wordList = s.toString().split("\t");
            String[] directorsL = wordList[4].split(",");
            String[] castL = wordList[7].split(",");
            String[] year = wordList[8].split("\\.");
            if (wordList[0].equals("Documentary:")) {
                Documentary newDocumentary = new Documentary(wordList[1], wordList[2], wordList[3], directorsL, wordList[5], wordList[6], castL, null, year[2]);
                documentaries.put(Integer.parseInt(wordList[1]), newDocumentary);
            }
        }
        Documentary.documentaries = documentaries;
    }
}