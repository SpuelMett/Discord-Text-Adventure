package CoreGame.Items;

public class ItemBook extends Item{

    private String content;

    public ItemBook(String name, String description, int weight, int price, String type, String content){
        super(name, description, weight ,price, type, 0);
    }

    public String getContent(){
        return content;
    }
}
