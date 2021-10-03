package Parsing;

public class Command implements java.io.Serializable{
    private String command;
    private String secondWord;
    private String thirdWord;

    public Command(String command, String secondWord, String thirdWord){
        this.command = command;
        this.secondWord = secondWord;
        this.thirdWord = thirdWord;
    }

    public String getCommand(){
        return command;
    }
    public String getSecondWord(){
        return secondWord;
    }
    public String getThirdWord(){
        return thirdWord;
    }

    public boolean hasSecondWord(){
        return secondWord != null;
    }
}
