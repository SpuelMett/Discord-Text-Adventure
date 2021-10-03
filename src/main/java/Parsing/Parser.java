package Parsing;
import java.util.StringTokenizer;

public class Parser implements java.io.Serializable{

    private String input;

    public Parser(){

    }


    public Command createCommand(String input){

        //System.out.println(input);
        String word1 = "";
        String word2 = "";
        String word3 = "";

        StringTokenizer tokenizer = new StringTokenizer(input);

        if(tokenizer.hasMoreElements()) word1 = tokenizer.nextToken().toLowerCase();
        else return null;

        if(tokenizer.hasMoreElements()) word2 = tokenizer.nextToken();
        else word2 = null;

        //System.out.println("word2: " + word2);

        if(tokenizer.hasMoreElements()) word3 = tokenizer.nextToken();
        else word3 = null;

        //Convert first word to the command word
        word1 = CommandChanger.convertCommand(word1);

        //only return a command Object if the commandWord(word1) is not empty
        if(word1!=null) return new Command(word1,word2,word3);
        else return null;
    }
}
