package CoreGame.Items;

public class ItemWeapon extends Item{

    private int damage;

    public ItemWeapon(String name,String description, int weight, int price, int damage){
        super(name, description, weight, price);
        this.damage = damage;
    }

    public int getDamage(){
        return damage;
    }
}
