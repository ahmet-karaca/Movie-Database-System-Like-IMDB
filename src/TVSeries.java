import java.io.*;
import java.util.*;

class TVSeries extends Films{
    private String[] genre;
    private String[] writers;
    private String releaseDate;
    private String numberofSeason;
    private String numberofEpisode;

    public static LinkedHashMap<Integer,TVSeries> tvSeriess = new LinkedHashMap<Integer,TVSeries>();
    // A HashMap that holds the film id and the tvSeries object.

    public TVSeries(String id, String filmTitle, String language, String[] directors, String runTime, String country, String[] cast,LinkedHashMap<Integer,String> rate, String[] genre, String[] writers, String releaseDate, String numberofSeason, String numberofEpisode) {
        super(id, filmTitle, language, directors, runTime, country, cast,rate);
        this.genre = genre;
        this.writers = writers;
        this.releaseDate = releaseDate;
        this.numberofSeason = numberofSeason;
        this.numberofEpisode = numberofEpisode;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public String[] getWriters() {
        return writers;
    }

    public void setWriters(String[] writers) {
        this.writers = writers;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getNumberofSeason() {
        return numberofSeason;
    }

    public void setNumberofSeason(String numberofSeason) {
        this.numberofSeason = numberofSeason;
    }

    public String getNumberofEpisode() {
        return numberofEpisode;
    }

    public void setNumberofEpisode(String numberofEpisode) {
        this.numberofEpisode = numberofEpisode;
    }


    public static void tvSeries(){
        // A method that creates the TVSeries in FilmList as an object, adds this object to the tvSeriess HashMap.

        ArrayList<String> filmList = new ArrayList<String>();

        LinkedHashMap<Integer,TVSeries> tvSeriesss = new LinkedHashMap<Integer,TVSeries>();
        for (String s : Films.filmList) {
            String[] wordList = s.toString().split("\t");
            String[] directorsL = wordList[4].split(",");
            String[] castL = wordList[7].split(",");
            if (wordList[0].equals("TVSeries:")) {
                String[] genreL = wordList[8].split(",");
                String[] writersL = wordList[9].split(",");
                String[] startYear = wordList[10].split("\\.");
                String[] endYear = wordList[11].split("\\.");
                String year = startYear[2] + "-" + endYear[2];
                TVSeries newTVSeries = new TVSeries(wordList[1], wordList[2], wordList[3], directorsL, wordList[5], wordList[6], castL, null, genreL, writersL,year, wordList[12], wordList[13]);
                tvSeriesss.put(Integer.parseInt(wordList[1]), newTVSeries);
            }
        }
        TVSeries.tvSeriess = tvSeriesss;
    }

    public static String  listFilmSeries(){
        // A mothod that lists TVSeries in FilmList and writes to output.txt.

        if (!TVSeries.tvSeriess.isEmpty()){
            StringBuilder toString = new StringBuilder();
            for (TVSeries tvSeries : TVSeries.tvSeriess.values()){
                toString.append("\n").append(tvSeries.getFilmTitle()).append(" (").append(tvSeries.releaseDate).append(")").append("\n").append(tvSeries.numberofSeason).append(" seasons and ").append(tvSeries.numberofEpisode).append(" episodes").append("\n");
            }
            return toString.toString();
        }
        else {
            String toString = "No result" + "\n";
            return toString;
        }
    }
}