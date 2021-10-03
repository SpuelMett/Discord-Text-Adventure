package IOHandling;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException;




public class IOHandling extends ListenerAdapter {


    private final BasicInputHandling inputHandler = new BasicInputHandling();

    public static void main(String[] args) throws LoginException{

        if (args.length < 1) {
            System.out.println("You have to provide a token as first argument!");
            System.exit(1);
        }

        JDABuilder.createLight(args[0], GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new IOHandling())
                .setActivity(Activity.playing("Text Adventure"))
                .build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        Message msg = event.getMessage();

        if(msg.getContentRaw().startsWith("!!")){
            MessageChannel channel = event.getChannel();

            //Give message to inputHandler
            String output = inputHandler.handleInput(msg);

            channel.sendMessage(output).queue();
        }
    }

    public void shutDown(){

    }
}
