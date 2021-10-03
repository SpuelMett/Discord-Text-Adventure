package CoreGame.Items;

public class ItemFood extends Item {

    int hunger;

    public ItemFood(String name,String description, int weight, int price, int hunger){
        super(name, description, weight, price);
        this.hunger = hunger;
    }

    public int getHunger() {
        return hunger;
    }
}
