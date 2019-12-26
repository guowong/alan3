public class Item {
    protected double price; // protected variables can be accessed by child class
    protected int inStock;
    
    // constructors
    Item( ) { }

    // getters
    public double getPrice( )  { return price; }
    public    int getInStock() { return inStock; }
    
    // setters
    public double setPrice (double price) { 
        this.price=price;
        return this.price;
    }
    public int setInStock(int inStock) { 
        this.inStock=inStock; 
        return this.inStock; 
    }

}
