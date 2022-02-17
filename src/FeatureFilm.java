import java.io.*;
import java.util.*;


class FeatureFilm extends Films{
    private String[] genre;
    private String releaseDate;
    private String[] writers;
    private String budget;

    public static LinkedHashMap<Integer,FeatureFilm> featureFilms = new LinkedHashMap<Integer,FeatureFilm>();
    // // A HashMap that holds the film id and the featureFilm object.

    public FeatureFilm(String id, String filmTitle, String language, String[] directors, String runTime, String country, String[] cast,LinkedHashMap<Integer,String> rate, String[] genre, String releaseDate,String[] writers, String budget){
        super(id,filmTitle,language,directors,runTime,country,cast,rate);
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.writers = writers;
        this.budget = budget;
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

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }


    public static void featureFilm() throws IOException{
        // A method that creates the featureFilms in filmList as an object, adds this object to the featureFilms HashMap.

        LinkedHashMap<Integer,FeatureFilm> featureFilms = new LinkedHashMap<Integer,FeatureFilm>();
        for (String s : Films.filmList) {
            String[] wordList = s.toString().split("\t");
            String[] directorsL = wordList[4].split(",");
            String[] castL = wordList[7].split(",");
            if (wordList[0].equals("FeatureFilm:")) {
                String[] genreL = wordList[8].split(",");
                String[] year = wordList[9].split("\\.");
                String[] writersL = wordList[10].split(",");

                FeatureFilm newFeatureFilm = new FeatureFilm(wordList[1], wordList[2], wordList[3], directorsL, wordList[5], wordList[6], castL, null, genreL, year[2], writersL, wordList[11]);

                featureFilms.put(Integer.valueOf(wordList[1]), newFeatureFilm);
            }
        }
        FeatureFilm.featureFilms = featureFilms;
    }

    public static String addFF(String id, String filmTitle, String language, String[] directors, String runTime, String country, String[] cast,String[] genre, String releaseDate,String[] writers, String budget) throws IOException {
        // A method that creates a new featureFilm with the features given in the command, adding this new featureFilm to the films HashMap, featureFilms HashMap and filmTypes HashMap.

        if (!featureFilms.containsKey(Integer.parseInt(id))){
            for (String director : directors) {
                if (!Director.directors.containsKey(Integer.parseInt(director))) {
                    String toString = "Command Failed" + "\n" + "Film ID: " + id + "\n" + "Film title: " + filmTitle + "\n";
                    return toString;
                }
            }
            for (String writer : writers){
                if(!Writer.writers.containsKey(Integer.parseInt(writer))){
                    String toString = "Command Failed" + "\n" + "Film ID: " + id + "\n" + "Film title: " + filmTitle + "\n";
                    return toString;
                }
            }
            for (String performer : cast){
                if (!Performer.performers.containsKey(Integer.parseInt(performer))){
                    String toString = "Command Failed" + "\n" + "Film ID: " + id + "\n" + "Film title: " + filmTitle + "\n";
                    return toString;
                }
            }
            String[] year = releaseDate.split("\\.");

            FeatureFilm newFeatureFilm = new FeatureFilm(id,filmTitle,language,directors,runTime,country,cast,null, genre,year[2],writers,budget);

            featureFilms.put(Integer.parseInt(id),newFeatureFilm);
            LinkedHashMap<Integer,String> rate = new LinkedHashMap<Integer,String>();
            Films newFilm = new Films(id,filmTitle,language,directors,runTime,country,cast,rate);
            films.put(Integer.parseInt(id),newFilm);
            // Apart from featureFilm, this new film has also been added to the films list.
            Films.filmTypes.put(Integer.parseInt(id),"FeatureFilm");
            // This new featureFilm has also been added to filmTypes.

            String toString = "FeatureFilm added successfully" + "\n" + "Film ID: " + id + "\n" + "Film title: " + filmTitle + "\n";
            return toString;
        }
        else{
            String toString = "Command Failed" + "\n" + "Film ID: " + id + "\n" + "Film title: " + filmTitle + "\n";
            return toString;
        }
    }

    public static String listBefore(String year){
        // A method that lists all featureFilms before to a certain year.
        int counter = 0;
        StringBuilder toString = new StringBuilder();
        for (FeatureFilm film : featureFilms.values()){
            String date = film.releaseDate;

            if (Integer.parseInt(date) < Integer.parseInt(year)){
                toString.append("\n").append("Film title: ").append(film.getFilmTitle()).append(" (").append(film.releaseDate).append(")").append("\n").append(film.getRunTime()).append(" min").append("\n").append("Language: ").append(film.getLanguage()).append("\n");
                counter += 1;
            }
        }
        if (counter == 0){
            toString = new StringBuilder("\n" + "No result" + "\n");
        }
        return toString.toString();
    }
    public static String listAfter(String year){
        // A method that lists all featureFilms after to a certain year.
        int counter = 0;
        StringBuilder toString = new StringBuilder();
        for (FeatureFilm film : featureFilms.values()){
            String date = film.releaseDate;

            if (Integer.parseInt(date) >= Integer.parseInt(year)){
                toString.append("\n").append("Film title: ").append(film.getFilmTitle()).append(" (").append(film.releaseDate).append(")").append("\n").append(film.getRunTime()).append(" min").append("\n").append("Language: ").append(film.getLanguage()).append("\n");
                counter += 1;
            }
        }
        if (counter == 0){
            toString = new StringBuilder("\n" + "No result" + "\n");
        }
        return toString.toString();
    }
}