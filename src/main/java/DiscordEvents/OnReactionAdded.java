package DiscordEvents;

import Settings.Singleton;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.requests.RestAction;


import java.util.ArrayList;
import java.util.List;

import Enum.Enum_ErrorType;

public class OnReactionAdded {

    public OnReactionAdded(MessageReactionAddEvent event) {
        String[] invite_information = null;
        Guild guild;
        Role Clanrole = null;
        List<Role> roles;


        //todo only do this on reaction checkmark
        if (!event.getUser().isBot()) {
            // information gathering
            User user = event.getUser();
            event.getMessageId();

            ArrayList<String[]> invites = Singleton.getInstance().invitesSend;
            // check if current user has a invite
            for (int i = 0; i < invites.size(); i++) {
                if (invites.get(i)[0].equals(user.getId())) {
                    invite_information = invites.get(i);

                }
            }
            if (invite_information != null) {
                guild = event.getJDA().getGuildById(invite_information[2]);
                if (guild != null) {
                    roles = guild.getRoles();
                    // check if clan role exists
                    for (Role role : roles) {
                        if (role.getName().contains("Clan | ")) {
                            String Rolename = role.getName().replace("Clan | ", "");
                            if (Rolename.equals(invite_information[1])) {
                                Clanrole = role;
                            }
                        }
                    }
                    if (Clanrole != null) {
                        guild.addRoleToMember(invite_information[0], Clanrole).queue();
                        deleteinvite(invites, invite_information);
                        // delete message
                        List<Message> messages = event.getPrivateChannel().getHistory().retrievePast(1).complete();
                        messages.get(0).delete().queue();
                    } else {
                        // role does not exists
                        Singleton.getInstance().base.PushPrivateExeption(
                                Enum_ErrorType.Accepted_Clan_not_existing,
                                false,
                                event.getUser(),
                                null);
                    }
                } else {
                    // guild does not exists
                    Singleton.getInstance().base.PushPrivateExeption(
                            Enum_ErrorType.invitationed_Guild_Does_not_exist,
                            false,
                            event.getUser(),
                            null);
                }
            } else {
                // invite does not exists
                Singleton.getInstance().base.PushPrivateExeption(
                        Enum_ErrorType.invitation_does_not_exists,
                        false,
                        event.getUser(),
                        null);
            }
        }
    }

    public void deleteinvite(ArrayList<String[]> invites, String[] used_invite) {
        if (invites == Singleton.getInstance().invitesSend) {
            for (String[] invite : Singleton.getInstance().invitesSend) {
                if (used_invite == invite) {
                    Singleton.getInstance().invitesSend.remove(used_invite);
                }
            }
        }
    }
}
