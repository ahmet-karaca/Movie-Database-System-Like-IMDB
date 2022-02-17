import java.io.*;
import java.util.*;

class User extends People{
    private LinkedHashMap<Integer,String> rate;

    public static LinkedHashMap<Integer,User> users = new LinkedHashMap<Integer,User>();
    // A HashMap that holds the people id and the user object.

    public User(String id,String name,String surname,String country, LinkedHashMap<Integer,String> rate) {
        super(id,name,surname,country);
        this.rate = rate;
    }

    public LinkedHashMap<Integer, String> getRate() {
        return rate;
    }

    public void setRate(LinkedHashMap<Integer, String> rate) {
        this.rate = rate;
    }


    public static void user() throws IOException{
        // A method that creates the users in peopleList as an object, adds this object to the user HashMap.

        LinkedHashMap<Integer ,User> users = new LinkedHashMap<Integer ,User>();
        for (String s : People.peopleList) {
            String[] wordList = s.toString().split("\t");
            if (wordList[0].equals("User:")) {
                LinkedHashMap<Integer, String> rate = new LinkedHashMap<Integer, String>();
                User newUser = new User(wordList[1], wordList[2], wordList[3], wordList[4], rate);
                users.put(Integer.parseInt(wordList[1]), newUser);
            }
        }
        User.users = users;
    }

    public static String  listUserRates(String id){
        // A method that writes all the rates given by the specified user to output.txt.

        if (User.users.containsKey(Integer.parseInt(id))){
            if (!User.users.get(Integer.parseInt(id)).getRate().isEmpty()){
                String toString = "";
                for (Integer filmID : users.get(Integer.parseInt(id)).getRate().keySet()){
                    toString += Films.films.get(filmID).getFilmTitle() + ": " + users.get(Integer.parseInt(id)).getRate().get(filmID) + "\n";
                }
                return toString;
            }
            else {
                String toString = "There is not any ratings so far" + "\n";
                return toString;
            }
        }
        else{
            String toString = "Command Failed" + "\n" + "User ID: " + id + "\n";
            return toString;
        }
    }
}