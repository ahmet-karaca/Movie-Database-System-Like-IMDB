import java.io.*;
import java.util.*;

class Commands {
    public static void command() throws IOException {
        // A method to create required Arraylists and Hashmaps in all classes.

        People.peopleReader();
        User.user();
        Director.director();
        Writer.writer();
        Performer.performer();
        Actor.actor();
        ChildActor.childActor();
        StuntPerformer.stuntPerformer();

        Films.filmReader();
        Films.film();
        FeatureFilm.featureFilm();
        ShortFilm.shortFilm();
        Documentary.documentary();
        TVSeries.tvSeries();

        commandReader();
    }
    public static void commandReader() throws IOException {
        // A method that reads CommandsFile, calls the required methods and writes the return outputs to output.txt.

        ArrayList<String> commandList = new ArrayList<String>();
        String line;
        FileReader fReader = new FileReader(Main.commandsFile);
        BufferedReader bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
            commandList.add(line);
        }
        bReader.close();
        fReader.close();

        File outputFile = new File(Main.outputFile);
        FileWriter fWriter = new FileWriter(outputFile,false);
        BufferedWriter bWriter = new BufferedWriter(fWriter);

        for (String command : commandList) {

            if (command.startsWith("RATE")) {
                String[] wordList = command.split("\t");
                if (Integer.parseInt(wordList[3]) < 1 || Integer.parseInt(wordList[3]) > 10){
                    System.out.println("Rating score must be between 1 and 10 integers.");
                }
                else{
                    bWriter.write(command);
                    bWriter.write("\n" + "\n");
                    String toString = Films.rate(wordList[1], wordList[2], wordList[3]);
                    bWriter.write(toString);
                    bWriter.write("-----------------------------------------------------------------------------------------------------");
                    bWriter.write("\n");
                }
            }

            if (command.startsWith("ADD")) {
                bWriter.write(command);
                bWriter.write("\n" + "\n");
                String[] wordList2 = command.split("\t");
                String toString = FeatureFilm.addFF(wordList2[2], wordList2[3], wordList2[4], wordList2[5].split(","), wordList2[6], wordList2[7], wordList2[8].split(","), wordList2[9].split(","), wordList2[10], wordList2[11].split(","), wordList2[12]);
                bWriter.write(toString);
                bWriter.write("-----------------------------------------------------------------------------------------------------");
                bWriter.write("\n");
            }

            if (command.startsWith("VIEWFILM")) {
                bWriter.write(command);
                bWriter.write("\n" + "\n");
                String[] wordList3 = command.split("\t");
                String toString = Films.viewFilm(wordList3[1]);
                bWriter.write(toString);
                bWriter.write("\n");
                bWriter.write("-----------------------------------------------------------------------------------------------------");
                bWriter.write("\n");
            }

            if (command.startsWith("EDIT")) {
                bWriter.write(command);
                bWriter.write("\n" + "\n");
                String[] wordList4 = command.split("\t");
                String toString = Films.editRate(wordList4[2], wordList4[3], wordList4[4]);
                bWriter.write(toString);
                bWriter.write("-----------------------------------------------------------------------------------------------------");
                bWriter.write("\n");
            }

            if (command.startsWith("REMOVE")) {
                bWriter.write(command);
                bWriter.write("\n" + "\n");
                String[] wordList5 = command.split("\t");
                String toString = Films.remove(wordList5[2], wordList5[3]);
                bWriter.write(toString);
                bWriter.write("-----------------------------------------------------------------------------------------------------");
                bWriter.write("\n");
            }

            if (command.startsWith("LIST\tUSER")) {
                bWriter.write(command);
                bWriter.write("\n" + "\n");
                String[] wordList6 = command.split("\t");
                String toString = User.listUserRates(wordList6[2]);
                bWriter.write(toString);
                bWriter.write("-----------------------------------------------------------------------------------------------------");
                bWriter.write("\n");
            }

            if (command.startsWith("LIST\tFILM\tSERIES")) {
                bWriter.write(command);
                bWriter.write("\n");
                String toString = TVSeries.listFilmSeries();
                bWriter.write(toString);
                bWriter.write("-----------------------------------------------------------------------------------------------------");
                bWriter.write("\n");
            }

            if (command.startsWith("LIST\tFILMS\tBY\tCOUNTRY")) {
                bWriter.write(command);
                bWriter.write("\n");
                String[] wordList8 = command.split("\t");
                String toString = Films.listFilmCountry(wordList8[4]);
                bWriter.write(toString);
                bWriter.write("-----------------------------------------------------------------------------------------------------");
                bWriter.write("\n");
            }

            if (command.startsWith("LIST\tFEATUREFILMS\tBEFORE")) {
                bWriter.write(command);
                bWriter.write("\n");
                String[] wordList9 = command.split("\t");
                String toString = FeatureFilm.listBefore(wordList9[3]);
                bWriter.write(toString);
                bWriter.write("-----------------------------------------------------------------------------------------------------");
                bWriter.write("\n");

            }

            if (command.startsWith("LIST\tFEATUREFILMS\tAFTER")) {
                bWriter.write(command);
                bWriter.write("\n");
                String[] wordList10 = command.split("\t");
                String toString = FeatureFilm.listAfter(wordList10[3]);
                bWriter.write(toString);
                bWriter.write("-----------------------------------------------------------------------------------------------------");
                bWriter.write("\n");
            }

            if (command.startsWith("LIST\tFILMS\tBY\tRATE\tDEGREE")) {
                bWriter.write(command);
                bWriter.write("\n");
                String toString = Films.listFilmRate();
                bWriter.write(toString);
                bWriter.write("-----------------------------------------------------------------------------------------------------");
                bWriter.write("\n");
            }

            if (command.startsWith("LIST\tARTISTS\tFROM")) {
                bWriter.write(command);
                bWriter.write("\n");
                String[] wordList11 = command.split("\t");
                String toString = Artist.listArtistCountry(wordList11[3]);
                bWriter.write(toString);
                bWriter.write("-----------------------------------------------------------------------------------------------------");
                bWriter.write("\n");
            }
        }
        bWriter.close();
    }
}