package Validator;

import DiscordEntity.CommandEntity;
import Validator.Singlevallidators.ArgumentValidator;
import Validator.Singlevallidators.PermissionValidator;

public class CommandValidator {

    /**
     * @param Command CommandEntity.
     * to initialize everything.
     * with this i dont have to give anything else with the command.
     */
    private CommandEntity Command;

    public CommandValidator(CommandEntity command) {
        this.Command = command;
    }

    public Boolean validate(){
        if(new ArgumentValidator(Command).validate()){
            if(new PermissionValidator(Command).validate()){
                return true;
            }
        }
        return false;
    }
}
