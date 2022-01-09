import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.user.UserStatus;

import java.util.Locale;

public class bot {

    public static void main(String[] args) {
        String spawnChannel = "928371104626659328";
        long pokeTwoID = 716390085896962058L;
        long botID = 713021503221792799L;
        final String prefix = "tb!";


        DiscordApi api = new DiscordApiBuilder()
                .setToken("")
                .login().join();


        api.updateStatus(UserStatus.DO_NOT_DISTURB);
        api.updateActivity(ActivityType.PLAYING," Poketwo ");
        api.addMessageCreateListener(event -> {
            String msg = event.getMessageContent();

            if (event.getMessageContent().toLowerCase().startsWith(prefix)){
                System.out.println("Message with prefix");
                String[] att = msg.split(" ");
                event.getChannel().sendMessage("");
                if (att.length>1){
                    if (att[1].equalsIgnoreCase("hint")){
                        System.out.println("hint");
                        if (att.length>2 && !(att[2].equalsIgnoreCase(" "))){
                            System.out.println(att[2]);
                            UtilityCode.returnMessageReady(UtilityCode.getPokemonList(att[2]
                                    ), event);

                        } else {
                            event.getChannel().sendMessage("Usage: " + prefix + " <hint from poketwo bot>\nWatch out for spaces!");
                        }
                    }
                }
            }

            if (event.getMessage().getAuthor().getId() == pokeTwoID) {
                System.out.println("Poketwo send message in: " +"Server: "+ event.getServer() + " txt:" + event.getChannel());
                if (event.getMessageContent().matches(".*pok\\u00E9mon is.*\\.")) {
                    System.out.println("Got hint in: " + event.getChannel());
                    String tempName = event.getMessageContent().replaceAll("\\.", "");
                    System.out.println("tempname: " + tempName);
                    UtilityCode.returnMessageReady(UtilityCode.getPokemonList(tempName), event);

                }
            }

        });

    }
}
