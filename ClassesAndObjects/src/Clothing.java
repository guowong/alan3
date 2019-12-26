public class Clothing extends Item {
    private String type;
    private String brand;
    private String color;
    
    ///// getters
    public String getType ( ) { return type; }
    public String getBrand( ) { return brand; }
    public String getColor( ) { return color; }
    
    //// setters
    public String setType ( String type  ) { 
        this.type=type; 
        return this.type; 
    }
    public String setBrand( String brand  ) { 
        this.brand=brand; 
        return this.brand; 
    }
    public String setColor( String color ) { 
        this.color=color; 
        return this.color; 
    }
    
    // constructors
    Clothing( ) { } // empty default constructor
    Clothing( String type, String brand, String color) {
        setType(type);
        setBrand(brand);
        setColor(color);
    } // end of three-argument constructor

} // end of class Clothing