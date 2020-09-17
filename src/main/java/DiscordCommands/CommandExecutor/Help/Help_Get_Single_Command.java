package DiscordCommands.CommandExecutor.Help;


import DiscordCommands.Help;
import DiscordEntity.CommandEntity;
import Enum.Enum_ErrorType;

import java.util.ArrayList;

public class Help_Get_Single_Command extends DiscordCommands.Help {
    public Help_Get_Single_Command() {
        ArrayList<CommandEntity> Commands = this.getCommandslist();
        boolean valid = false;
        boolean exesting_help = true;
        for (int i = 0; i < Commands.size(); i++) {
            // gives help message of specified command.
            if(Settings.base.Args[0].toLowerCase().equals(Commands.get(i).getCommandName().toLowerCase())){
                valid = true;
                CommandEntity command = Commands.get(i);
                if(command.getHelp() != null && command.getHelp().GetFullhelp(command.getCommandName()) != null){
                    Settings.base.sendmessage(command.getHelp().GetFullhelp(command.getCommandName()));
                } else {
                    exesting_help = false;
                }
            }
        }
        if(!valid) {
            this.PushExeption(Enum_ErrorType.Invalid_Command, false);
        }
        if(!exesting_help){
            this.PushExeption(Enum_ErrorType.HelpDescription_not_available, false);
        }
    }

}
