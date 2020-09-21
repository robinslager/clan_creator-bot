package DiscordEvents;

import DiscordController.CommandController;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class OnMessageRecieved {
    public OnMessageRecieved(MessageReceivedEvent event) {

        if(!event.getAuthor().getName().equals("git-admin")) {
            char prefix = '!';
            try {
                if (event.getMessage().getContentRaw().charAt(0) == prefix) {

                    String message = event.getMessage().getContentRaw();

                    String[] parts = message.split(" ");

                    new CommandController(parts[0].substring(1), event);

                } else {
                    System.out.println("this is a normal message");
                }
            } catch (StringIndexOutOfBoundsException e) {
                log.println("idk ");
            }
        }
    }
}