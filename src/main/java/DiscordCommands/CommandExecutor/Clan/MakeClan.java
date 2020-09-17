package DiscordCommands.CommandExecutor.Clan;

import DiscordCommands.Clan;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static Enum.Enum_ErrorType.Clan_Name_Already_Used;

public class MakeClan extends Clan {
    private String Clanname;

    public MakeClan() {

        Clanname = "Clan | " + this.Settings.base.Args[1].replaceAll("_", " ");
        Role ClanRole = createRole();
        if (ClanRole != null) {
            create_channel(ClanRole);
            this.Settings.base.get_guild().addRoleToMember(this.Settings.base.get_user().getId(), ClanRole).queue();
            this.Settings.base.sendmessage("Clan: `" + Clanname + "` has been created. check for your channels in the channel list");
        } else {
            this.PushExeption(Clan_Name_Already_Used, false);
        }
    }

    private void create_channel(Role role) {
        ChannelAction<Category> channelAction = this.Settings.base.get_guild().createCategory(Clanname);
        ArrayList<Permission> collection_A = new ArrayList<>();
        ArrayList<Permission> collection_D = new ArrayList<>();
        collection_D.add(Permission.MESSAGE_READ);
        collection_D.add(Permission.MANAGE_CHANNEL);
        channelAction.addPermissionOverride(this.Settings.base.get_guild().getPublicRole(), collection_A, collection_D);
        collection_A.add(Permission.MESSAGE_READ);
        collection_A.add(Permission.MESSAGE_MANAGE);
        collection_D.clear();
        channelAction.addPermissionOverride(role, collection_A, collection_D);
        channelAction.queue();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        ChannelAction<TextChannel> channel = this.Settings.base.get_guild().getCategoriesByName(Clanname, true).get(0).createTextChannel("Clan Chat");
        channel.queue();

        ChannelAction<VoiceChannel> vchannel = this.Settings.base.get_guild().getCategoriesByName(Clanname, true).get(0).createVoiceChannel(Clanname + " Room");
        vchannel.queue();
    }

    private Role createRole() {
        List<Role> roles = this.Settings.base.get_guild().getRoles();
        for (Role role : roles) {
            if (role.getName().equals(Clanname)) {
                return null;
            }
        }

        Role newrole = this.Settings.base.get_guild().createRole()
                .setHoisted(true)
                .setName(Clanname)
                .complete();
        return newrole;



    }



}
