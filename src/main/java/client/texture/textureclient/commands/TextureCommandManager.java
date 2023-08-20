package client.texture.textureclient.commands;

import client.texture.textureclient.utils.TextureUtils;
import client.texture.textureclient.commands.command.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TextureCommandManager {

    public static ArrayList<TextureCommand> commands = new ArrayList<>();

    public static void loadCommands(){
        commands.add(new HelpCommand());
        commands.add(new ToggleCommand());
        commands.add(new FlyCommand());
        commands.add(new CriticalsCommand());
        commands.add(new VclipCommand());
        commands.add(new HclipCommand());
        commands.add(new SpeedCommand());
        commands.add(new PlayerCommand());
        commands.add(new TimerCommand());
        commands.add(new InfoCommand());
        commands.add(new NametagsCommand());

        Collections.sort(TextureCommandManager.commands, new Comparator<TextureCommand>() {
            @Override
            public int compare(TextureCommand a1, TextureCommand a2) {
                String a = a1.name;
                String b = a2.name;
                int lengthComparison = Integer.compare(b.length(), a.length());
                if (lengthComparison != 0) {
                    return lengthComparison;
                }
                return a.compareTo(b);
            }
        });
    }

    public static void processCommand(String cmd, String[] arguments){
        String[] modifiedArguments = arguments;
        if (arguments.length >= 2) {
            modifiedArguments = new String[arguments.length - 1];
            System.arraycopy(arguments, 1, modifiedArguments, 0, modifiedArguments.length);
            modifiedArguments[0] = modifiedArguments[0].substring(1);
        }
        for (TextureCommand x : commands){
            if (x.name.equalsIgnoreCase(cmd)){
                try {
                    x.runCommand(modifiedArguments);
                }catch(Exception e){
                    TextureUtils.sendChatMessage("§fException while running command §7"+x.name+":§f \n"+e.getMessage());
                }
            }
        }
    }

}
