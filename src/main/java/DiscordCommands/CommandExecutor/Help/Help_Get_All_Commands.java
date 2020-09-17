package DiscordCommands.CommandExecutor.Help;

import DiscordCommands.Help;
import DiscordEntity.CommandEntity;
import Resourses.CommandsList;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.ArrayList;

public class Help_Get_All_Commands extends DiscordCommands.Help {
    public Help_Get_All_Commands() {
        ArrayList<CommandEntity> Commands = new CommandsList().ReturnCommands();
        // makes message wit a short command description for all commands.
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Commands");
        for (CommandEntity command : Commands) {
            if(command.getHelp() != null && command.getHelp().getshorthelp() != null){
                embed.addField(command.getCommandName(), command.getHelp().getshorthelp(), false);
            }
        }
        this.Settings.base.sendmessage(embed.build());
    }

}
