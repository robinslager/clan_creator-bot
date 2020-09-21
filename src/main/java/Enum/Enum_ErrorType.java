package Enum;

public enum Enum_ErrorType {

    // validation
    Invalid_Amount_Arguments("This command does not need this amount of Arguments."),
    Missing_Permissions("You dont have the right Permissions to execute this command"),


    // Help
    Invalid_Command("This is no command. \n" +
            "Type: !help for a list with all commands "),
    HelpDescription_not_available("this Command does not have a help discription"),

    // clan
    Invalid_Option_Clan("this is a invalid option choose out of the following options: " +
            "\n**create** " +
            "\n**invite** " +
            "\n**transfer** " +
            "\n**delete** "),
    Accepted_Clan_not_existing("The group you accepted a invite from does not exist anymore."),
    invitationed_Guild_Does_not_exist("The guild of the group you accepted an invite from does not exist anymore."),
    Clan_Name_Already_Used("your picked Clan Name is already used choose a other clan name"),
    invitation_does_not_exists("this invitation does not exist anymore"),
    Make_Clan_While_Member("you are already member of a clan you first need to leave a clan before you can make one"),
    Attempt_To_Invite_ClanMember("this Member is already in a clan and can not join your clan until he leaves his clan"),
    Invite_While_Not_In_Clan("You can not invite people if you are not part of a clan");



    private String Discription;

    public String getDiscription(){
        return this.Discription;
    }
    public String getMissing_Argument(String Argument){
        return this.getDiscription() + Argument;
    }

    Enum_ErrorType(String discription)
    {
        this.Discription = discription;
    };

}