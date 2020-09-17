package Validator.Singlevallidators;

import DiscordEntity.CommandEntity;
import Enum.Enum_ErrorType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Objects;

public class PermissionValidator extends SingleValidator {
    private CommandEntity Command;

    public PermissionValidator(CommandEntity command) {
        this.Command = command;
    }

    @Override
    public boolean validate() {
        if(this.Command.getPermissions().size() == 0){
            return true;
        }else {
            MessageReceivedEvent event = Command.getSettings().base.getMsg();
            TextChannel MessageChannel = event.getTextChannel();
            if(Objects.requireNonNull(event.getMember()).hasPermission(MessageChannel, this.Command.getPermissions())){
                return true;
            } else {
                event.getMessage().delete().queue();
                Command.PushExeption(
                        Enum_ErrorType.Missing_Permissions,
                        true);
                return false;
            }
        }
    }
}
