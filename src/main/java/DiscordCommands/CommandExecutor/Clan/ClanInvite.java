package DiscordCommands.CommandExecutor.Clan;

import DiscordCommands.Clan;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;

import java.util.List;
import java.util.Objects;

import static Enum.Enum_ErrorType.Attempt_To_Invite_ClanMember;
import static Enum.Enum_ErrorType.Invite_While_Not_In_Clan;

public class ClanInvite extends Clan {

    public ClanInvite() {
        // get clan
        Role Clanrole = null;
        String ClanName = null;
        Member Author = this.Settings.base.get_guild().getMember(this.Settings.event.getAuthor());
        List<Role> roles = Author.getRoles();

        // test for reaction adding
//        this.Settings.invitesSend.add(
//                new String[]{
//                        this.Settings.base.get_user().getId(),
//                        ClanName,
//                        this.Settings.base.get_guild().getId()
//                }
//        );
//
//
//        EmbedBuilder builder = new EmbedBuilder();
//        builder.setTitle("Invite");
//        builder.setAuthor(this.Settings.base.get_user().getName());
//        builder.addField("Server: " + this.Settings.base.get_guild().getName(),
//                "You have been Invited by " + ClanName + "\n this is on the server",
//                false);
//        builder.addBlankField(true);
//        builder.addField("", "To accept this invite check the icon below this message", false);
//
//        MessageEmbed message = builder.build();
//        Author.getUser().openPrivateChannel().queue((privateChannel -> {
//            privateChannel.sendMessage(message).queue(message1 -> {
//                message1.addReaction("\u2705").queue();
//            });
//        }));



        for (Role role : roles){
            if(role.getName().contains("Clan | ")){
                Clanrole = role;
                ClanName = role.getName().replace("Clan | ", "");
            }
        }
        if (Clanrole != null){
            // check if mentioned player has a clan

            List<Member> memberList = this.Settings.base.getMsg().getMessage().getMentionedMembers();
            if(memberList.size() == 1){
                for (Member member : memberList){
                    roles = member.getRoles();
                    for (Role role : roles){

                        if(role.getName().contains("Clan | ")){


                            this.PushExeption(Attempt_To_Invite_ClanMember, false);
                            return;
                        } else {
                            this.Settings.invitesSend.add(
                                    new String[]{
                                            member.getUser().getId(),
                                            ClanName,
                                            this.Settings.base.get_guild().getId()
                                    }
                            );
                            EmbedBuilder builder = new EmbedBuilder();
                            builder.setTitle("Invite");
                            builder.setAuthor(this.Settings.base.get_user().getName());
                            builder.addField("Server: " + this.Settings.base.get_guild().getName(),
                                    "You have been Invited by " + ClanName + "\n this is on the server",
                                    false);
                            builder.addBlankField(true);
                            builder.addField("", "To accept this invite check the icon below this message", false);

                            MessageEmbed message = builder.build();
                            member.getUser().openPrivateChannel().queue((privateChannel -> {
                                privateChannel.sendMessage(message).queue(message1 -> {
                                    message1.addReaction("\u2705").queue();
                                });
                            }));

                        }
                    }
                }
            }
        } else {
            this.PushExeption(Invite_While_Not_In_Clan, true);
        }
    }
}
