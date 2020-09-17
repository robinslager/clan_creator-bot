package DiscordCommands;

import DiscordCommands.CommandExecutor.Clan.ClanInvite;
import DiscordCommands.CommandExecutor.Clan.MakeClan;
import DiscordEntity.CommandEntity;
import DiscordEntity.HelpString;
import Enum.ArgumentTypes;
import net.dv8tion.jda.api.entities.Role;

import java.util.List;

import static Enum.ArgumentTypes.STRING;
import static Enum.Enum_ErrorType.*;

public class Clan extends CommandEntity {

    // clan make name.
    // clan invite username
    // clan delete clanname
    // clan transfer username clanname
    public Clan() {
        this.CommandName = "clan";
        this.Arguments_Allowed = new int[]{2, 3};
        this.ArgumentTypes_Allowed = new ArgumentTypes[] {STRING};
//        this.Help = new HelpString(new String[] {})
    }

    @Override
    public void Execute() {
        switch (this.Settings.base.getArgs()[0].toLowerCase()){
            case "create":
                boolean clanmember = false;
                List<Role> roles = this.Settings.base.get_guild().getMember(this.Settings.base.get_user()).getRoles();
                for (Role role : roles){
                    if(role.getName().contains("Clan | ")){
                        clanmember = true;
                    }
                }
                if(!clanmember){
                    new MakeClan();
                } else {
                    this.PushExeption(Make_Clan_While_Member, false);
                }
                break;
            case "invite":
                new ClanInvite();
                break;
            case "transfer":
                break;
            case "delete":
                break;
            default:
                this.PushExeption(Invalid_Option_Clan, true);
                break;
        }
    }
}