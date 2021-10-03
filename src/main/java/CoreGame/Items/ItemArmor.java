package CoreGame.Items;

public class ItemArmor extends Item{

    private int defence;

    public ItemArmor(String name,String description, int weight, int price, int defence){
        super(name, description, weight, price);
        this.defence = defence;
    }

    public int getDefence() {
        return defence;
    }
}
