package Settings;

import DiscordEntity.CommandEntity;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import Enum.Enum_ErrorType;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class DiscordVariables {

    private MessageReceivedEvent msg = null;
    private GuildJoinEvent servr = null;

    public String[] getArgs() {
        return Args;
    }

    public int getArgs_length() {
        return Args_length;
    }

    public String[] Args;

    public int Args_length;

    public DiscordVariables(MessageReceivedEvent msg, GuildJoinEvent servr) {
        this.msg = msg;
        this.servr = servr;
        this.SetArguments();

    }

    public String getauthername() {
        if (msg != null) {
            return msg.getAuthor().getName();
        } else {
            error("message");
        }
        return "";
    }

    public User get_user() {
        if (msg != null) {
            return msg.getAuthor();
        } else {
            return null;
        }
    }

    public MessageReceivedEvent getMsg() {
        if (msg != null) {
            return msg;
        } else {
            error("message");
        }
        return null;

    }

    public String getmessage() {
        if (msg != null) {
            return msg.getMessage().getContentRaw();
        } else {
            error("message");
        }
        return null;
    }


    public Guild get_guild() {
        if (servr != null) {
            return servr.getGuild();
        } else {
            return msg.getGuild();
        }
    }

    private void SetArguments() {
        if (msg != null) {
            String[] parts = msg.getMessage().getContentRaw().split(" ");
            if (Arrays.copyOfRange(parts, 1, parts.length).length == 0) {
                Args = null;
                Args_length = 0;
            } else {
                Args = Arrays.copyOfRange(parts, 1, parts.length);
                Args_length = Args.length;
            }
        }
    }


    public String get_ServerID() {
        return get_guild().getId();
    }

    public String getGuildname() {
        return get_guild().getName();
    }


    public void sendmessage(String textsend) {
        if (msg != null) {
            msg.getChannel().sendMessage(textsend).queue();
        } else {
            TextChannel channel = servr.getGuild().getDefaultChannel();
            channel.sendMessage(textsend).queue();
        }
    }

    public void sendmessage(MessageEmbed textsend) {
        if (msg != null) {
            msg.getChannel().sendMessage(textsend).queue();
        } else {
            TextChannel channel = servr.getGuild().getDefaultChannel();
            channel.sendMessage(textsend).queue();
        }
    }

    public void sendPrivatemessage(User user, String Message) {
        user.openPrivateChannel().queue((privateChannel -> {
            privateChannel.sendMessage(Message).queue();
        }));
    }

    public void PushPrivateExeption(Enum_ErrorType errorType, Boolean command, User user, CommandEntity commandEntity) {
        String Exeption = "";
        Exeption = "Error occurred: " + errorType + "\n";
        Exeption += errorType.getDiscription() + "\n";
        if (command && commandEntity != null) {
            Exeption += "Do !help " + commandEntity.getCommandName() + " for more information.";
        }
        String finalExeption = Exeption;
        user.openPrivateChannel().queue((privateChannel -> {
            privateChannel.sendMessage(finalExeption).queue();
        }));
    }


    private void error(String var) {
        log.println("Error " + var + " not set basics.java");
    }
}
