import org.javacord.api.event.message.MessageEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class UtilityCode {
    public static ArrayList<String> getPokemonList(String regexReady){
        System.out.println("input: " + regexReady);
        regexReady = regexReady.replaceAll(".*pok\\u00E9mon is ", "").replaceAll("\\\\", "").replaceAll("\\u00E9", "é")
                .replaceAll("_", ".").replaceAll("\\u2642", "")
                .replaceAll("\\uFE0F","").replaceAll(" ", "");
        System.out.println("searching: " + regexReady);
        ArrayList<String> list = new ArrayList<>();


        File csvFile = new File("./files/pokemonList.txt");
        System.out.println();
        try {
            Scanner fileReader = new Scanner(csvFile);
            while (fileReader.hasNextLine()){
                String next = fileReader.nextLine();
                if (next.toLowerCase().matches(regexReady.toLowerCase())){
                    list.add(next);
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void returnMessageReady(ArrayList<String> list, MessageEvent event){
        ArrayList<String> found = new ArrayList<>();
        String msg = "`I have found the following: `";
        System.out.println("Found:");

        if (list.size() > 15) {
            msg = "`looks like something went wrong and too many results came back, please try again with a different hint.`";
            event.getChannel().sendMessage(msg);
            System.out.println("Too many results");
        }
        else if (list.size()==0){
            msg = "`I haven't found anything.`";
            event.getChannel().sendMessage(msg);
            System.out.println("Nothing");
        }
        else {
            event.getChannel().sendMessage(msg);
            for (String s: list) {
                event.getChannel().sendMessage("`" + s + "`");
                System.out.println(s);
            }
        }

    }

   /* public static void transform(String woord,MessageEvent event){
        woord = woord.replaceAll("\\\\", "").replaceAll("\\u00E9", "é")
                .replaceAll("_", ".").replaceAll("\\u2642", "")
                .replaceAll("\\uFE0F","");
        System.out.println(woord);
        event.getChannel().sendMessage("I see: " + woord);

    }*/
}
