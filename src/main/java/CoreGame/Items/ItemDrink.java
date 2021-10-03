package CoreGame.Items;

public class ItemDrink extends Item {

    int thirst;

    public ItemDrink(String name,String description, int weight, int price, int thirst){
        super(name, description, weight, price);
        this.thirst = thirst;
    }

    public int getThirst() {
        return thirst;
    }
}
