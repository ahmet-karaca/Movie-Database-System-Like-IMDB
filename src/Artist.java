import java.io.*;
import java.util.*;

class Artist extends People{

    public Artist(String id, String name, String surname, String country) {
        super(id, name, surname, country);
    }

    public static String listArtistCountry(String country){
        // A method to write Artists from the specified country to output.txt.

        int countD = 0,countW = 0,countA = 0,countC = 0,countS = 0;
        // Integer variables that count the number of artistes from the specified country.

        StringBuilder toString = new StringBuilder();

        toString.append("\n" + "Directors:" + "\n");
        for (Director director : Director.directors.values()){
            if (director.getCountry().equals(country)){
                toString.append(director.getName()).append(" ").append(director.getSurname()).append(" ").append(director.getAgent()).append("\n");
                countD += 1;
            }
        }
        if (countD == 0){
            toString.append("No result" + "\n");
        }

        toString.append("\n" + "Writers:" + "\n");
        for (Writer writer : Writer.writers.values()){
            if (writer.getCountry().equals(country)){
                toString.append(writer.getName()).append(" ").append(writer.getSurname()).append(" ").append(writer.getWritingType()).append("\n");
                countW += 1;
            }
        }
        if (countW == 0){
            toString.append("No result" + "\n");
        }

        toString.append("\n" + "Actors:" + "\n");
        for (Actor actor : Actor.actors.values()){
            if ((actor).getCountry().equals(country)){
                toString.append((actor).getName()).append(" ").append((actor).getSurname()).append(" ").append(actor.getHeight()).append(" cm").append("\n");
                countA += 1;
            }
        }
        if (countA == 0){
            toString.append("No result" + "\n");
        }

        toString.append("\n" + "ChildActors:" + "\n");
        for (ChildActor cActor : ChildActor.childActors.values()){
            if (cActor.getCountry().equals(country)){
                toString.append(cActor.getName()).append(" ").append(cActor.getSurname()).append(" ").append(cActor.getAge()).append("\n");
                countC += 1;
            }
        }
        if (countC == 0){
            toString.append("No result" + "\n");
        }

        toString.append("\n" + "StuntPerformers:" + "\n");
        for (StuntPerformer stunt : StuntPerformer.stuntPerformers.values()){
            if (stunt.getCountry().equals(country)){
                toString.append(stunt.getName()).append(" ").append(stunt.getSurname()).append(" ").append(stunt.getHeight()).append(" cm").append("\n");
                countS += 1;
            }
        }
        if (countS == 0){
            toString.append("No result" + "\n");
        }
        return toString.toString();
    }
}