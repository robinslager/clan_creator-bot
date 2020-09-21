package DiscordEntity;

import DiscordEvents.OnGuildJoin;
import DiscordEvents.OnMessageRecieved;
import Enum.ArgumentTypes;
import Enum.Enum_ErrorType;
import Settings.Singleton;
import Validator.CommandValidator;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;

public abstract class CommandEntity {

    /**
     * @param CommandName
     * is the name called for command in discord.
     */
    protected String CommandName;

    /**
     * @param Arguments_Allowed
     * is for the number of allowed commands for all different options.
     */
    protected int[] Arguments_Allowed;

    /**
     * @param ArgumentTypes_Allowed
     * the argument types that are allowed.
     */
    protected ArgumentTypes[] ArgumentTypes_Allowed;

    /**
     * @param Help
     * is the String that will gets returned if command help is called.
     * @see HelpString
     */
    protected HelpString Help;

    /**
     * @param Permissions
     * A list with all permissions you need for the command.
     */
    protected ArrayList<Permission> Permissions = new ArrayList<>();

    /**
     * @param Exeption
     * a string where we will store a Exeption before it will get to a user.
     */
    protected String Exeption = "";

    /**
     * @param Settings
     * used for all general information you need troughout all commands.
     * most of information is in JDA events you get by executing command.
     * @see OnMessageRecieved
     * @see OnGuildJoin
     */
    protected Singleton Settings;


    /**
     * @Contructor Bot_Command
     * settings get initialized.
     */
    public CommandEntity() {
        Settings = Singleton.getInstance();
    }

    /**
     * @return Settings_bot
     */
    public Singleton getSettings() {
        return Settings;
    }

    /**
     * @return CommandName
     */
    public String getCommandName() {
        return CommandName;
    }

    /**
     * @return Arguments_Allowed
     */
    public int[] getArguments_Allowed() {
        return Arguments_Allowed;
    }

    /**
     * @return ArgumentTypes_Allowed
     */
    public ArgumentTypes[] getArgumentTypes_Allowed() {
        return ArgumentTypes_Allowed;
    }

    /**
     * @return Help
     */
    public HelpString getHelp() {
        return Help;
    }

    /**
     * @return Permissions
     */
    public ArrayList<Permission> getPermissions() {
        return Permissions;
    }


    /**
     * @function PushExeption
     * @var Enum_ErrorType String
     * @var boolean Command
     * @var String description
     * Sets a Exception String in correct format.
     */
    public void PushExeption(Enum_ErrorType errorType, Boolean command){
        Exeption = "Error occurred: " + errorType + "\n";
        Exeption += errorType.getDiscription() + "\n";
        if(command) {
            Exeption += "Do !help " + this.CommandName + " for more information.";
        }
        Settings.base.sendmessage(Exeption);
    }

    public void PushExeption(Enum_ErrorType Error, String Discription, Boolean command){
        Exeption = "Error occurred: " + Error + "\n";
        Exeption += Discription + "\n";
        if(command) {
            Exeption += "Do !help " + this.CommandName + " for more information.";
        }
        Settings.base.sendmessage(Exeption);
    }


    /**
     * @function CommandValidation
     * if validation returns true Execute command.
     * @see CommandValidator
     */
    public void CommandValidation(){
        if(new CommandValidator(this).validate()){
            Execute();
        }
    }

    /**
     * @function Execute
     * executes command.
     */
    public abstract void Execute();
}

