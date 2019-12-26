
public class Pants extends Clothing {
    private int length;
    private int waist;
    private static int pantsCount = 0;
    
    // getters
    public int getlength()  { return length; }
    public int getwaist()  { return waist; }
    
    // setters
    public int setlength( int length ) { 
        this.length=length;
        return this.length; }
    public int setwaist( int waist ) { 
        this.waist=waist;
        return this.waist; }
    
    ///// constructors
    Pants() { 
        pantsCount++;   // keep track of the number of shirts
    } 
    
    Pants(String type, String brand, int length, int waist, String color, double price, int inStock) {
        setType(type);
        setBrand(brand);
        setlength(length);
        setwaist(waist);
        setColor(color);
        setPrice(price);        // located in the superclass
        setInStock(inStock);    // located in the superclass
        pantsCount++;           // keep track of the number of pants
    } // end of the seven argument constructor
    
    @Override
    public String toString() {
        return String.format ("%6.2f %s, by %s", getPrice(), getType(), getBrand());        
    }

} // end of class Pants
