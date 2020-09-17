package Settings;

import DiscordController.Filewriter_Controller;
import DiscordEntity.CommandEntity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Singleton {
        // static variable single_instance of type Settings.Singleton
        private static Singleton single_instance = null;
        // variable of type String
        public MessageReceivedEvent event;
        public DiscordVariables base;
        public JSONObject serverinfo;
        public ArrayList<CommandEntity> commands = new ArrayList<>();
        public CommandEntity command;
        public ArrayList<String[]> invitesSend = new ArrayList<>();


    public boolean executing = false;

        // private constructor restricted to this class itself
        private Singleton()
        {

        }
        public void changeExec(){
            if(executing == false){
                executing = true;
            } else {
                executing = false;
            }
        }

        public boolean getexecuting(){
            return executing;
        }

        public void setMessageEvent(MessageReceivedEvent event) {
            this.event = event;
            setBase();
            setServerinfo();
        }
        private void setBase(){
            this.base = new DiscordVariables(this.event, null);
        }

        private void setServerinfo(){
            Guild guild;
            try {
                guild = base.get_guild();
            } catch (IllegalStateException e){
                guild = null;
            }
            if(guild != null) {
                JSONObject serverdata = new JSONObject(new Filewriter_Controller().read());
                JSONArray servers = serverdata.getJSONArray("servers");
                for (int i = 0; i < servers.length(); i++) {
                    JSONObject server_data = servers.getJSONObject(i);
                    if (server_data.get("server_ID").toString().equals(base.get_ServerID())) {
                        serverinfo = server_data;
                    }
                }
            }
        }

        // static method to create instance of Settings.Singleton class
        public static Singleton getInstance()
        {
            if (single_instance == null)
                single_instance = new Singleton();

            return single_instance;
        }
}
