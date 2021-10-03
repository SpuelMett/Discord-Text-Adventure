package CoreGame;

public class Npc implements INpc, java.io.Serializable{

    String name;
    String description;

    String answer;

    public Npc(String name, String description, String answer){
        this.name = name;
        this.description = description;
        this.answer = answer;
    }

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }

    public String getAnswer() {
        return answer;
    }
}
