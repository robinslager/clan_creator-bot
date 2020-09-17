package Resourses;


import DiscordCommands.Clan;
import DiscordCommands.Help;
import DiscordEntity.CommandEntity;

import java.util.ArrayList;

public class CommandsList {
    private ArrayList<CommandEntity> Commands = new ArrayList<>();
    public ArrayList<CommandEntity> ReturnCommands(){
        Commands.add(new Help());
        Commands.add(new Clan());
        return Commands;
    }
}
