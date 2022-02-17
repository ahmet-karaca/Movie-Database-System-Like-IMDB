import java.io.*;

public class Main {
    public static String peopleFile;
    public static String filmsFile;
    public static String commandsFile;
    public static String outputFile;

    public static void main(String[] args) throws IOException {
        // A method that takes argument files and executes commands.

        peopleFile = args[0];
        filmsFile = args[1];
        commandsFile = args[2];
        outputFile = args[3];

        Commands.command();
    }
}
