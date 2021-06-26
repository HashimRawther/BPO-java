package Backend;

public class Item{
	public int id;
	public int quantity;
	public String name;
	public double price;
	public int description;
	
	public Item(int quantity, String name,double price) {
		this.quantity=quantity;
		this.name=name;
		this.price=price;
	}
}