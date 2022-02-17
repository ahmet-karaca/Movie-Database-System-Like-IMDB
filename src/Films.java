import java.io.*;
import java.util.*;

class Films {
    private String id;
    private String filmTitle;
    private String language;
    private String[] directors;
    private String runTime;
    private String country;
    private String[] cast;

    private LinkedHashMap<Integer,String> rate;
    // A HashMap that keeps the film id and rate score.

    public static ArrayList<String> filmList;
    // An ArrayList that holds lines of films.txt.

    public static LinkedHashMap<Integer,Films> films;
    // A HashMap that holds the film id and the film object.

    public static LinkedHashMap<Integer,String> filmTypes = new LinkedHashMap<>();
    // A HashMap that holds the film id and the film types.

    public Films(String id, String filmTitle, String language, String[] directors, String runTime, String country, String[] cast, LinkedHashMap<Integer,String> rate){

        this.id = id;
        this.filmTitle = filmTitle;
        this.language = language;
        this.directors = directors;
        this.runTime = runTime;
        this.country = country;
        this.cast = cast;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String[] getDirectors() {
        return directors;
    }

    public void setDirectors(String[] directors) {
        this.directors = directors;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String[] getCast() {
        return cast;
    }

    public void setCast(String[] cast) {
        this.cast = cast;
    }

    public LinkedHashMap<Integer,String> getRate() {return rate;}

    public void setRate(LinkedHashMap<Integer,String> rate){this.rate = rate;}


    public static void filmReader() throws IOException{
        // A method that reads the filmtxt and adds the lines to the ArrayList.

        ArrayList<String> filmList = new ArrayList<String>();
        String line;
        FileReader fReader = new FileReader(Main.filmsFile);
        BufferedReader bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
            filmList.add(line);
        }
        bReader.close();
        fReader.close();

        Films.filmList = filmList;
    }

    public static void film() throws IOException{
        // A method that creates the films in FilmList as an object, adds this object to the films HashMap, and adds this movie and film's type to the filmTypes HashMap.

        LinkedHashMap<Integer,Films> films = new LinkedHashMap<Integer,Films>();

        for (String s : filmList) {
            String[] wordList = s.toString().split("\t");
            String[] directorsL = wordList[4].split(",");
            String[] castL = wordList[7].split(",");
            LinkedHashMap<Integer,String> rate = new LinkedHashMap<Integer,String>();
            Films newFilm = new Films(wordList[1], wordList[2], wordList[3], directorsL, wordList[5], wordList[6], castL,rate);

            films.put(Integer.parseInt(wordList[1]), newFilm);

            Films.filmTypes.put(Integer.parseInt(wordList[1]),wordList[0].substring(0, (wordList[0].length())-1));

        }
        Films.films = films;
    }


    public static String  rate(String userID,String filmID,String point) throws IOException {
        // A method that retes films, adds this rates to Films objects, and adds these rates to User objects.

        if ((User.users.containsKey(Integer.parseInt(userID))) && (Films.films.containsKey(Integer.parseInt(filmID)))){
            if (((Films) films.get(Integer.parseInt(filmID))).getRate().containsKey(Integer.parseInt(userID))){
                String toString = "This film was earlier rated" + "\n";
                return toString;
            }

            else {
                ((Films) films.get(Integer.parseInt(filmID))).getRate().put(Integer.parseInt(userID),point);
                String toString = "Film rated successfully" + "\n" + "Film type: " + filmTypes.get(Integer.parseInt(filmID)) + "\n" + "Film title: " + films.get(Integer.parseInt(filmID)).getFilmTitle() + "\n";
                User.users.get(Integer.parseInt(userID)).getRate().put(Integer.parseInt(filmID),point);
                return toString;
            }
        }
        else{
            String toString = "Command Failed" + "\n" + "User ID: " + userID + "\n" + "Film ID: " + filmID + "\n";
            return toString;
        }
    }


    public static String  editRate(String userID,String filmID,String newPoint){
        // A method that edits given rates.

        if ((User.users.containsKey(Integer.parseInt(userID))) && (Films.films.containsKey(Integer.parseInt(filmID)))) {
            if (((Films) films.get(Integer.parseInt(filmID))).getRate().containsKey(Integer.parseInt(userID))){
                ((Films) films.get(Integer.parseInt(filmID))).getRate().replace(Integer.parseInt(userID),newPoint);
                String toString = "New ratings done successfully" + "\n" + "Film title: " + films.get(Integer.parseInt(filmID)).getFilmTitle() + "\n" + "Your rating: " + newPoint + "\n";
                User.users.get(Integer.parseInt(userID)).getRate().replace(Integer.parseInt(filmID),newPoint);
                return toString;
            }

            else{
                String toString = "Command Failed" + "\n" + "User ID: " + userID + "\n" + "Film ID: " + filmID + "\n";
                return toString;
            }
        }
        else{
            String toString = "Command Failed" + "\n" + "User ID: " + userID + "\n" + "Film ID: " + filmID + "\n";
            return toString;
        }
    }

    public static String  remove(String userID,String filmID){
        // A method that deletes given rates.

        if ((User.users.containsKey(Integer.parseInt(userID))) && (Films.films.containsKey(Integer.parseInt(filmID)))) {
            if (((Films) films.get(Integer.parseInt(filmID))).getRate().containsKey(Integer.parseInt(userID))){
                ((Films) films.get(Integer.parseInt(filmID))).getRate().remove(Integer.parseInt(userID));
                String toString = "Your film rating was removed successfully" + "\n" + "Film title: " + films.get(Integer.parseInt(filmID)).getFilmTitle() + "\n";
                User.users.get(Integer.parseInt(userID)).getRate().remove(Integer.parseInt(filmID));
                return toString;
            }

            else{
                String toString = "Command Failed" + "\n" + "User ID: " + userID + "\n" + "Film ID: " + filmID + "\n";
                return toString;
            }
        }
        else{
            String toString = "Command Failed" + "\n" + "User ID: " + userID + "\n" + "Film ID: " + filmID + "\n";
            return toString;
        }
    }

    public static String  viewFilm(String id){
        // A method that writes the details of all films types to output.txt.

        if (Films.films.containsKey(Integer.parseInt(id))){
            String type = filmTypes.get(Integer.parseInt(id));
            if (type.equals("FeatureFilm")){
                StringBuilder writerString = new StringBuilder();
                for (String writerID : FeatureFilm.featureFilms.get(Integer.parseInt(id)).getWriters()){
                    writerString.append(" ").append(Writer.writers.get(Integer.parseInt(writerID)).getName()).append(" ").append(Writer.writers.get(Integer.parseInt(writerID)).getSurname()).append(",");
                }
                writerString.deleteCharAt(writerString.length()-1);

                StringBuilder directorString = new StringBuilder();
                for (String directorID : FeatureFilm.featureFilms.get(Integer.parseInt(id)).getDirectors()){
                    directorString.append(" ").append(Director.directors.get(Integer.parseInt(directorID)).getName()).append(" ").append(Director.directors.get(Integer.parseInt(directorID)).getSurname()).append(",");
                }
                directorString.deleteCharAt(directorString.length()-1);

                StringBuilder starsString = new StringBuilder();
                for (String starsID : FeatureFilm.featureFilms.get(Integer.parseInt(id)).getCast()){
                    starsString.append(" ").append(Performer.performers.get(Integer.parseInt(starsID)).getName()).append(" ").append(Performer.performers.get(Integer.parseInt(starsID)).getSurname()).append(",");
                }
                starsString.deleteCharAt(starsString.length()-1);

                if (!Films.films.get(Integer.parseInt(id)).getRate().isEmpty()){
                    double avarageRate = 0.0;
                    for (String s :Films.films.get(Integer.parseInt(id)).getRate().values()){
                        avarageRate += Integer.parseInt(s);
                    }
                    avarageRate /= Films.films.get(Integer.parseInt(id)).getRate().values().size();

                    if (avarageRate != (int) avarageRate){
                        String toString = FeatureFilm.featureFilms.get(Integer.parseInt(id)).getFilmTitle() + " (" + FeatureFilm.featureFilms.get(Integer.parseInt(id)).getReleaseDate() + ")" + "\n"
                                + (Arrays.toString(FeatureFilm.featureFilms.get(Integer.parseInt(id)).getGenre())).substring(1,(Arrays.toString(FeatureFilm.featureFilms.get(Integer.parseInt(id)).getGenre())).length()-1) + "\n"
                                + "Writers:" + writerString + "\n"
                                + "Directors:" + directorString + "\n"
                                + "Stars:" + starsString + "\n"
                                + "Ratings: " + Double.toString(avarageRate).replace(".",",") + "/10 from " + Films.films.get(Integer.parseInt(id)).getRate().size() + " users";
                        return toString;
                    }
                    else {
                        String toString = FeatureFilm.featureFilms.get(Integer.parseInt(id)).getFilmTitle() + " (" + FeatureFilm.featureFilms.get(Integer.parseInt(id)).getReleaseDate() + ")" + "\n"
                                + (Arrays.toString(FeatureFilm.featureFilms.get(Integer.parseInt(id)).getGenre())).substring(1,(Arrays.toString(FeatureFilm.featureFilms.get(Integer.parseInt(id)).getGenre())).length()-1) + "\n"
                                + "Writers:" + writerString + "\n"
                                + "Directors:" + directorString + "\n"
                                + "Stars:" + starsString + "\n"
                                + "Ratings: " + (int) avarageRate + "/10 from " + Films.films.get(Integer.parseInt(id)).getRate().size() + " users";
                        return toString;
                    }
                }
                else{
                    String toString = FeatureFilm.featureFilms.get(Integer.parseInt(id)).getFilmTitle() + " (" + FeatureFilm.featureFilms.get(Integer.parseInt(id)).getReleaseDate() + ")" + "\n"
                            + (Arrays.toString(FeatureFilm.featureFilms.get(Integer.parseInt(id)).getGenre())).substring(1,(Arrays.toString(FeatureFilm.featureFilms.get(Integer.parseInt(id)).getGenre())).length()-1) + "\n"
                            + "Writers:" + writerString + "\n"
                            + "Directors:" + directorString + "\n"
                            + "Stars:" + starsString + "\n"
                            + "Awaiting for votes";
                    return toString;
                }
            }

            if (type.equals("ShortFilm")){
                StringBuilder writerString = new StringBuilder();
                for (String writerID : ShortFilm.shortFilms.get(Integer.parseInt(id)).getWriters()){
                    writerString.append(" ").append(Writer.writers.get(Integer.parseInt(writerID)).getName()).append(" ").append(Writer.writers.get(Integer.parseInt(writerID)).getSurname()).append(",");
                }
                writerString.deleteCharAt(writerString.length()-1);

                StringBuilder directorString = new StringBuilder();
                for (String directorID : ShortFilm.shortFilms.get(Integer.parseInt(id)).getDirectors()){
                    directorString.append(" ").append(Director.directors.get(Integer.parseInt(directorID)).getName()).append(" ").append(Director.directors.get(Integer.parseInt(directorID)).getSurname()).append(",");
                }
                directorString.deleteCharAt(directorString.length()-1);

                StringBuilder starsString = new StringBuilder();
                for (String starsID : ShortFilm.shortFilms.get(Integer.parseInt(id)).getCast()){
                    starsString.append(" ").append(Performer.performers.get(Integer.parseInt(starsID)).getName()).append(" ").append(Performer.performers.get(Integer.parseInt(starsID)).getSurname()).append(",");
                }
                starsString.deleteCharAt(starsString.length()-1);

                if (!Films.films.get(Integer.parseInt(id)).getRate().isEmpty()){

                    double avarageRate = 0.0;
                    for (String s :Films.films.get(Integer.parseInt(id)).getRate().values()){
                        avarageRate += Integer.parseInt(s);
                    }
                    avarageRate /= Films.films.get(Integer.parseInt(id)).getRate().values().size();

                    if (avarageRate != (int) avarageRate){
                        String toString = ShortFilm.shortFilms.get(Integer.parseInt(id)).getFilmTitle() + " (" + ShortFilm.shortFilms.get(Integer.parseInt(id)).getReleaseDate() + ")" + "\n"
                                + (Arrays.toString(ShortFilm.shortFilms.get(Integer.parseInt(id)).getGenre())).substring(1,(Arrays.toString(ShortFilm.shortFilms.get(Integer.parseInt(id)).getGenre())).length()-1) + "\n"
                                + "Writers:" + writerString + "\n"
                                + "Directors:" + directorString + "\n"
                                + "Stars:" + starsString + "\n"
                                + "Ratings: " + Double.toString(avarageRate).replace(".",",") + "/10 from " + Films.films.get(Integer.parseInt(id)).getRate().size() + " users";
                        return toString;
                    }
                    else {
                        String toString = ShortFilm.shortFilms.get(Integer.parseInt(id)).getFilmTitle() + " (" + ShortFilm.shortFilms.get(Integer.parseInt(id)).getReleaseDate() + ")" + "\n"
                                + (Arrays.toString(ShortFilm.shortFilms.get(Integer.parseInt(id)).getGenre())).substring(1,(Arrays.toString(ShortFilm.shortFilms.get(Integer.parseInt(id)).getGenre())).length()-1) + "\n"
                                + "Writers:" + writerString + "\n"
                                + "Directors:" + directorString + "\n"
                                + "Stars:" + starsString + "\n"
                                + "Ratings: " + (int) avarageRate + "/10 from " + Films.films.get(Integer.parseInt(id)).getRate().size() + " users";
                        return toString;
                    }
                }
                else{
                    String toString = ShortFilm.shortFilms.get(Integer.parseInt(id)).getFilmTitle() + " (" + ShortFilm.shortFilms.get(Integer.parseInt(id)).getReleaseDate() + ")" + "\n"
                            + (Arrays.toString(ShortFilm.shortFilms.get(Integer.parseInt(id)).getGenre())).substring(1,(Arrays.toString(ShortFilm.shortFilms.get(Integer.parseInt(id)).getGenre())).length()-1) + "\n"
                            + "Writers:" + writerString + "\n"
                            + "Directors:" + directorString + "\n"
                            + "Stars:" + starsString + "\n"
                            + "Awaiting for votes";
                    return toString;
                }
            }

            if (type.equals("Documentary")){

                StringBuilder directorString = new StringBuilder();
                for (String directorID : Documentary.documentaries.get(Integer.parseInt(id)).getDirectors()){
                    directorString.append(" ").append(Director.directors.get(Integer.parseInt(directorID)).getName()).append(" ").append(Director.directors.get(Integer.parseInt(directorID)).getSurname()).append(",");
                }
                directorString.deleteCharAt(directorString.length()-1);

                StringBuilder starsString = new StringBuilder();
                for (String starsID : Documentary.documentaries.get(Integer.parseInt(id)).getCast()){
                    starsString.append(" ").append(Performer.performers.get(Integer.parseInt(starsID)).getName()).append(" ").append(Performer.performers.get(Integer.parseInt(starsID)).getSurname()).append(",");
                }
                starsString.deleteCharAt(starsString.length()-1);

                if (!Films.films.get(Integer.parseInt(id)).getRate().isEmpty()){
                    double avarageRate = 0.0;
                    for (String s :Films.films.get(Integer.parseInt(id)).getRate().values()){
                        avarageRate += Integer.parseInt(s);
                    }
                    avarageRate /= Films.films.get(Integer.parseInt(id)).getRate().values().size();

                    if (avarageRate != (int) avarageRate){
                        String toString = Documentary.documentaries.get(Integer.parseInt(id)).getFilmTitle() + " (" + Documentary.documentaries.get(Integer.parseInt(id)).getReleaseDate() + ")" + "\n"
                                + "Directors:" + directorString + "\n"
                                + "Stars:" + starsString + "\n"
                                + "Ratings: " + Double.toString(avarageRate).replace(".",",") + "/10 from " + Films.films.get(Integer.parseInt(id)).getRate().size() + " users";
                        return toString;
                    }
                    else {
                        String toString = Documentary.documentaries.get(Integer.parseInt(id)).getFilmTitle() + " (" + Documentary.documentaries.get(Integer.parseInt(id)).getReleaseDate() + ")" + "\n"
                                + "Directors:" + directorString + "\n"
                                + "Stars:" + starsString + "\n"
                                + "Ratings: " + (int) avarageRate + "/10 from " + Films.films.get(Integer.parseInt(id)).getRate().size() + " users";
                        return toString;
                    }
                }
            }

            else if (type.equals("TVSeries")){
                StringBuilder writerString = new StringBuilder();
                for (String writerID : TVSeries.tvSeriess.get(Integer.parseInt(id)).getWriters()){
                    writerString.append(" ").append(Writer.writers.get(Integer.parseInt(writerID)).getName()).append(" ").append(Writer.writers.get(Integer.parseInt(writerID)).getSurname()).append(",");
                }
                writerString.deleteCharAt(writerString.length()-1);

                StringBuilder directorString = new StringBuilder();
                for (String directorID : TVSeries.tvSeriess.get(Integer.parseInt(id)).getDirectors()){
                    directorString.append(" ").append(Director.directors.get(Integer.parseInt(directorID)).getName()).append(" ").append(Director.directors.get(Integer.parseInt(directorID)).getSurname()).append(",");
                }
                directorString.deleteCharAt(directorString.length()-1);

                StringBuilder starsString = new StringBuilder();
                for (String starsID : TVSeries.tvSeriess.get(Integer.parseInt(id)).getCast()){
                    starsString.append(" ").append(Performer.performers.get(Integer.parseInt(starsID)).getName()).append(" ").append(Performer.performers.get(Integer.parseInt(starsID)).getSurname()).append(",");
                }
                starsString.deleteCharAt(starsString.length()-1);

                if (!Films.films.get(Integer.parseInt(id)).getRate().isEmpty()){
                    double avarageRate = 0.0;
                    for (String s :Films.films.get(Integer.parseInt(id)).getRate().values()){
                        avarageRate += Integer.parseInt(s);
                    }
                    avarageRate /= Films.films.get(Integer.parseInt(id)).getRate().values().size();

                    if (avarageRate != (int) avarageRate){
                        String toString = TVSeries.tvSeriess.get(Integer.parseInt(id)).getFilmTitle() + " (" + TVSeries.tvSeriess.get(Integer.parseInt(id)).getReleaseDate() + ")" + "\n"
                                + TVSeries.tvSeriess.get(Integer.parseInt(id)).getNumberofSeason() + " seasons, " + TVSeries.tvSeriess.get(Integer.parseInt(id)).getNumberofEpisode() + " episodes" + "\n"
                                + (Arrays.toString(TVSeries.tvSeriess.get(Integer.parseInt(id)).getGenre())).substring(1,(Arrays.toString(TVSeries.tvSeriess.get(Integer.parseInt(id)).getGenre())).length()-1) + "\n"
                                + "Writers:" + writerString + "\n"
                                + "Directors:" + directorString + "\n"
                                + "Stars:" + starsString + "\n"
                                + "Ratings: " + Double.toString(avarageRate).replace(".",",") + "/10 from " + Films.films.get(Integer.parseInt(id)).getRate().size() + " users";
                        return toString;
                    }
                    else {
                        String toString = TVSeries.tvSeriess.get(Integer.parseInt(id)).getFilmTitle() + " (" + TVSeries.tvSeriess.get(Integer.parseInt(id)).getReleaseDate() + ")" + "\n"
                                + TVSeries.tvSeriess.get(Integer.parseInt(id)).getNumberofSeason() + " seasons, " + TVSeries.tvSeriess.get(Integer.parseInt(id)).getNumberofEpisode() + " episodes" + "\n"
                                + (Arrays.toString(TVSeries.tvSeriess.get(Integer.parseInt(id)).getGenre())).substring(1,(Arrays.toString(TVSeries.tvSeriess.get(Integer.parseInt(id)).getGenre())).length()-1) + "\n"
                                + "Writers:" + writerString + "\n"
                                + "Directors:" + directorString + "\n"
                                + "Stars:" + starsString + "\n"
                                + "Ratings: " + (int) avarageRate + "/10 from " + Films.films.get(Integer.parseInt(id)).getRate().size() + " users";
                        return toString;
                    }
                }

                else {
                    String toString = TVSeries.tvSeriess.get(Integer.parseInt(id)).getFilmTitle() + " (" + TVSeries.tvSeriess.get(Integer.parseInt(id)).getReleaseDate() + ")" + "\n"
                            + TVSeries.tvSeriess.get(Integer.parseInt(id)).getNumberofSeason() + " seasons, " + TVSeries.tvSeriess.get(Integer.parseInt(id)).getNumberofEpisode() + " episodes" + "\n"
                            + (Arrays.toString(TVSeries.tvSeriess.get(Integer.parseInt(id)).getGenre())).substring(1,(Arrays.toString(TVSeries.tvSeriess.get(Integer.parseInt(id)).getGenre())).length()-1) + "\n"
                            + "Writers:" + writerString + "\n"
                            + "Directors:" + directorString + "\n"
                            + "Stars:" + starsString + "\n"
                            + "Awaiting for votes";

                    return toString;
                }
            }
        }
        String toString = "Command Failed" + "\n" + "FilmID: " + id;
        return toString;
    }

    public static String listFilmCountry(String country){
        // A method that lists all movies in a specific country.

        int counter = 0;
        StringBuilder toString = new StringBuilder();
        for (Films films : Films.films.values()){
            if (films.country.equals(country)){
                toString.append("\n").append("Film title: ").append(films.filmTitle).append("\n").append(films.runTime).append(" min").append("\n").append("Language: ").append(films.language).append("\n");
                counter += 1;
            }
        }

        if (counter == 0){
            toString.append("\n" + "No result" + "\n");
        }
        return toString.toString();
    }


    public static LinkedHashMap<String,Double> sorter(LinkedHashMap<String,Double> map){
        // A method that takes films id and the the averages of the rate the films has and sorts them in descending order.

        List<Map.Entry<String, Double>> entries = new ArrayList<Map.Entry<String, Double>>(map.entrySet());
        entries.sort(new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> a, Map.Entry<String, Double> b) {
                return b.getValue().compareTo(a.getValue());
            }
        });
        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public static String  listFilmRate(){
        // A method that separates all films according to their types, takes the average of the given rates, sends them to the sorter() method and sorts them, then writes the sorted rates to output.txt.

        StringBuilder toString = new StringBuilder();

        toString.append("\n" + "FeatureFilm:" + "\n");
        if (!FeatureFilm.featureFilms.isEmpty()){

            LinkedHashMap<String,Double> rateFeature = new LinkedHashMap<>();
            // A HashMap that takes films id and rates as double.
            // The same process for all film types

            for (FeatureFilm featureFilm : FeatureFilm.featureFilms.values()){
                double avarageRate = 0.0;

                if (!Films.films.get(Integer.parseInt(featureFilm.getId())).getRate().isEmpty()){
                    for (String s : (Films.films.get(Integer.parseInt(featureFilm.getId())).getRate()).values()){
                        avarageRate += Integer.parseInt(s);
                    }
                    avarageRate /= (Films.films.get(Integer.parseInt(featureFilm.getId())).getRate()).size();
                    rateFeature.put(featureFilm.getId(),avarageRate);
                }
                else{
                    rateFeature.put(featureFilm.getId(),avarageRate);
                }
            }

            LinkedHashMap<String,Double> sortedRateFeature = sorter(rateFeature);
            // A HashMap that takes the movie id and rate in order.
            // The same process for all film types

            for (String s : sortedRateFeature.keySet()){
                if (sortedRateFeature.get(s) == sortedRateFeature.get(s).intValue()){
                    toString.append(films.get(Integer.parseInt(s)).filmTitle).append(" ").append("(").append(FeatureFilm.featureFilms.get(Integer.parseInt(s)).getReleaseDate()).append(")").append(" ").append("Ratings: ").append(sortedRateFeature.get(s).intValue()).append("/10 from ").append(Films.films.get(Integer.parseInt(s)).getRate().size()).append(" users").append("\n");
                }
                else{
                    toString.append(films.get(Integer.parseInt(s)).filmTitle).append(" ").append("(").append(FeatureFilm.featureFilms.get(Integer.parseInt(s)).getReleaseDate()).append(")").append(" ").append("Ratings: ").append(String.valueOf(Math.round(sortedRateFeature.get(s)  * 10.0 ) / 10.0).replace(".",",")).append("/10 from ").append(Films.films.get(Integer.parseInt(s)).getRate().size()).append(" users").append("\n");
                }
            }

        }
        else{
            toString.append("No result").append("\n");
        }

        toString.append("\n" + "ShortFilm:" + "\n");
        if (!ShortFilm.shortFilms.isEmpty()){
            LinkedHashMap<String ,Double> rateShort = new LinkedHashMap<>();

            for (ShortFilm shortFilm : ShortFilm.shortFilms.values()){
                double avarageRate = 0.0;
                if (!Films.films.get(Integer.parseInt(shortFilm.getId())).getRate().isEmpty()){
                    for (String s : (Films.films.get(Integer.parseInt(shortFilm.getId())).getRate()).values()){
                        avarageRate += Integer.parseInt(s);
                    }

                    avarageRate /= (Films.films.get(Integer.parseInt(shortFilm.getId())).getRate()).size();
                    rateShort.put(shortFilm.getId(),avarageRate);
                }
                else{
                    rateShort.put(shortFilm.getId(),avarageRate);
                }
            }

            LinkedHashMap<String,Double> sortedRateShort = sorter(rateShort);

            for (String s : sortedRateShort.keySet()){
                if (sortedRateShort.get(s) == sortedRateShort.get(s).intValue()){
                    toString.append(films.get(Integer.parseInt(s)).filmTitle).append(" ").append("(").append(ShortFilm.shortFilms.get(Integer.parseInt(s)).getReleaseDate()).append(")").append(" ").append("Ratings: ").append(sortedRateShort.get(s).intValue()).append("/10 from ").append(Films.films.get(Integer.parseInt(s)).getRate().size()).append(" users").append("\n");
                }
                else{
                    toString.append(films.get(Integer.parseInt(s)).filmTitle).append(" ").append("(").append(ShortFilm.shortFilms.get(Integer.parseInt(s)).getReleaseDate()).append(")").append(" ").append("Ratings: ").append(sortedRateShort.get(s).toString().replace(".", ",")).append("/10 from ").append(Films.films.get(Integer.parseInt(s)).getRate().size()).append(" users").append("\n");
                }
            }
        }

        else{
            toString.append("No result").append("\n");
        }
        toString.append("\n" + "Documentary:" + "\n");
        if (!Documentary.documentaries.isEmpty()){
            LinkedHashMap<String,Double> rateDocumentary = new LinkedHashMap<>();

            for (Documentary documentary : Documentary.documentaries.values()){
                double avarageRate = 0.0;
                if (!Films.films.get(Integer.parseInt(documentary.getId())).getRate().isEmpty()){
                    for (String s : (Films.films.get(Integer.parseInt(documentary.getId())).getRate()).values()){
                        avarageRate += Integer.parseInt(s);
                    }
                    avarageRate /= (Films.films.get(Integer.parseInt(documentary.getId())).getRate()).size();
                    rateDocumentary.put(documentary.getId(),avarageRate);
                }
                else{
                    rateDocumentary.put(documentary.getId(),avarageRate);
                }
            }

            LinkedHashMap<String,Double> sortedRateDocumentary = sorter(rateDocumentary);

            for (String s : sortedRateDocumentary.keySet()){
                if (sortedRateDocumentary.get(s) == sortedRateDocumentary.get(s).intValue()){
                    toString.append(films.get(Integer.parseInt(s)).filmTitle).append(" ").append("(").append(Documentary.documentaries.get(Integer.parseInt(s)).getReleaseDate()).append(")").append(" ").append("Ratings: ").append(sortedRateDocumentary.get(s).intValue()).append("/10 from ").append(Films.films.get(Integer.parseInt(s)).getRate().size()).append(" users").append("\n");
                }
                else{
                    toString.append(films.get(Integer.parseInt(s)).filmTitle).append(" ").append("(").append(Documentary.documentaries.get(Integer.parseInt(s)).getReleaseDate()).append(")").append(" ").append("Ratings: ").append(sortedRateDocumentary.get(s).toString().replace(".", ",")).append("/10 from ").append(Films.films.get(Integer.parseInt(s)).getRate().size()).append(" users").append("\n");
                }
            }
        }
        else{
            toString.append("No result").append("\n");
        }

        toString.append("\n" + "TVSeries:" + "\n");
        if (!TVSeries.tvSeriess.isEmpty()){
            LinkedHashMap<String ,Double> rateTVSeries = new LinkedHashMap<>();

            for (TVSeries tvSeries : TVSeries.tvSeriess.values()){
                double avarageRate = 0.0;

                if (!Films.films.get(Integer.parseInt(tvSeries.getId())).getRate().isEmpty()){
                    for (String s : (Films.films.get(Integer.parseInt(tvSeries.getId())).getRate()).values()){
                        avarageRate += Integer.parseInt(s);
                    }

                    avarageRate /= (Films.films.get(Integer.parseInt(tvSeries.getId())).getRate()).size();
                    rateTVSeries.put(tvSeries.getId(),avarageRate);

                }
                else{
                    rateTVSeries.put(tvSeries.getId(),avarageRate);
                }
            }

            LinkedHashMap<String,Double> sortedRateTVSeries = sorter(rateTVSeries);

            for (String s : sortedRateTVSeries.keySet()){
                if (sortedRateTVSeries.get(s) == sortedRateTVSeries.get(s).intValue()){
                    toString.append(films.get(Integer.parseInt(s)).filmTitle).append(" ").append("(").append(TVSeries.tvSeriess.get(Integer.parseInt(s)).getReleaseDate()).append(")").append(" ").append("Ratings: ").append(sortedRateTVSeries.get(s).intValue()).append("/10 from ").append(Films.films.get(Integer.parseInt(s)).getRate().size()).append(" users").append("\n");
                }
                else{
                    toString.append(films.get(Integer.parseInt(s)).filmTitle).append(" ").append("(").append(TVSeries.tvSeriess.get(Integer.parseInt(s)).getReleaseDate()).append(")").append(" ").append("Ratings: ").append(sortedRateTVSeries.get(s).toString().replace(".", ",")).append("/10 from ").append(Films.films.get(Integer.parseInt(s)).getRate().size()).append(" users").append("\n");
                }
            }
        }
        else{
            toString.append("No result").append("\n");
        }

        return toString.toString();

    }
}