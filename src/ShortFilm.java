import java.io.*;
import java.util.*;

class ShortFilm extends Films {
    private String[] genre;
    private String releaseDate;
    private String[] writers;

    public static LinkedHashMap<Integer,ShortFilm> shortFilms = new LinkedHashMap<Integer,ShortFilm> ();
    // A HashMap that holds the film id and the shortFilm object.

    public ShortFilm(String id, String filmTitle, String language, String[] directors, String runTime, String country, String[] cast,LinkedHashMap<Integer,String>  rate, String[] genre, String releaseDate, String[] writers) {
        super(id, filmTitle, language, directors, runTime, country, cast, rate);
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.writers = writers;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String[] getWriters() {
        return writers;
    }

    public void setWriters(String[] writers) {
        this.writers = writers;
    }


    public static void shortFilm() throws IOException {
        // A method that creates the shortFilms in FilmList as an object, adds this object to the shortFilms HashMap.(If the runTime is less than 40 minutes)


        ArrayList<String> filmList = new ArrayList<String>();
        LinkedHashMap<Integer,ShortFilm>  shortFilms = new LinkedHashMap<Integer,ShortFilm> ();
        for (String s : Films.filmList) {
            String[] wordList = s.toString().split("\t");
            String[] directorsL = wordList[4].split(",");
            String[] castL = wordList[7].split(",");
            if (wordList[0].equals("ShortFilm:")) {
                String[] genreL = wordList[8].split(",");
                String[] year = wordList[9].split("\\.");
                String[] writersL = wordList[10].split(",");
                if (Integer.parseInt(wordList[5]) <= 40){
                    ShortFilm newShortFilm = new ShortFilm(wordList[1], wordList[2], wordList[3], directorsL, wordList[5], wordList[6], castL, null, genreL, year[2], writersL);
                    shortFilms.put(Integer.parseInt(wordList[1]), newShortFilm);
                }
                else{
                    System.out.println("A short film cannot be longer than 40 minutes!");
                }
            }
        }
        ShortFilm.shortFilms = shortFilms;
    }
}