package Parsing;

import java.util.Arrays;

public class CommandChanger {

    //first word in line is the command
    private static final String[] go
            = {"go", "move"};
    private static final String[] pick
            = {"pick", "pickup","take"};
    private static final String[] drop
            = {"drop"};
    private static final String[] info
            = {"info", "roominfo"};
    private static final String[] help
            = {"help"};
    private static final String[] eat
            = {"eat"};
    private static final String[] drink
            = {"drink"};
    private static final String[] secretCommand
            = {"secretCommand", "gopickdropinfohelp"};
    private static final String[] speak
            = {"speak","ask"};
    private static final String[] attack
            = {"attack", "fight"};
    private static final String[] equip
            = {"equip"};
    private static final String[] unequip
            = {"unequip","deequip"};
    private static final String[] die
            = {"die"};


    /**
     * Converts the first word to the right command word.
     * @param input
     * @return CommandWord
     */
    public static String convertCommand(String input){
        if(Arrays.asList(go).contains(input)) return "go";
        if(Arrays.asList(pick).contains(input)) return "pick";
        if(Arrays.asList(drop).contains(input)) return "drop";
        if(Arrays.asList(info).contains(input)) return "info";
        if(Arrays.asList(help).contains(input)) return "help";
        if(Arrays.asList(eat).contains(input)) return "eat";
        if(Arrays.asList(drink).contains(input)) return "drink";
        if(Arrays.asList(secretCommand).contains(input)) return "secretCommand";
        if(Arrays.asList(speak).contains(input)) return "speak";
        if(Arrays.asList(attack).contains(input)) return "attack";
        if(Arrays.asList(equip).contains(input)) return "equip";
        if(Arrays.asList(unequip).contains(input)) return "unequip";
        if(Arrays.asList(die).contains(input)) return "die";

        //default
        return null;
    }

    /**
     * Return all possible commands.
     * @return
     */
    public static String returnAllCommands(){
        StringBuilder sb = new StringBuilder();
        sb.append(go[0]).append(", ");
        sb.append(pick[0]).append(", ");
        sb.append(drop[0]).append(", ");
        sb.append(info[0]).append(", ");
        sb.append(help[0]).append(", ");
        sb.append(eat[0]).append(", ");
        sb.append(drink[0]).append(", ");
        sb.append(speak[0]).append(", ");
        sb.append(attack[0]).append(", ");
        sb.append(equip[0]).append(", ");
        sb.append(unequip[0]).append(", ");
        sb.append(die[0]);
        return sb.toString();
    }
}
