import java.io.*;
import java.util.*;

class People {
    private String id;
    private String name;
    private String surname;
    private String country;

    public static ArrayList<String> peopleList;
    // An ArrayList that holds lines of people.txt

    public People(String id,String name,String surname,String country){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public static void peopleReader() throws IOException{
        // A method that reads the peopletxt and adds the lines to the ArrayList.

        ArrayList<String> peopleList = new ArrayList<String >();
        String line;
        FileReader fReader = new FileReader(Main.peopleFile);
        BufferedReader bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
            peopleList.add(line);
        }
        bReader.close();
        fReader.close();
        People.peopleList = peopleList;
    }
}