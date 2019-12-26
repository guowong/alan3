public class Book extends Item {
    private static final double MAX_BOOK_PRICE = 100.00;
    private String title;
    private String author;
    private int date;
    private static int bookCount = 0;
    
    // default constructor
    Book ( ) {
        bookCount++;    // keep track of the number of books
    }; 
    
    // constructor with five arguments
    Book (int itemID, String title, String author, int date, double price, int inStock) {
        setItemID(itemID);
        setTitle(title);
        setAuthor(author);
        setDate(date);
        setPrice(price);        // code in the superclass
        setInStock(inStock);    // code in the superclass
        
        bookCount++;            // keep track of the number of books
    }; // end of five argument constructor

    ///////// getters and setters //////////
    public String getTitle( )  { return title; }
    public String getAuthor( ) { return author; }
    public    int getDate( )   { return date; }    
    public static int getBookCount() { return bookCount; }
    
    // setters
    public String setTitle (String t)  { title=t; return title; }
    public String setAuthor (String a) { author=a; return author; }
    public    int setDate (int d)      { date=d; return date; }

    @Override
    public double setPrice(double price) {
        if (price < 0) {
            this.price = 0.00;
            System.out.println ("Negative price not allowed");
        }
        else if (price > MAX_BOOK_PRICE) {
            this.price = MAX_BOOK_PRICE;
            System.out.println ("Attempted to set pice too high");
        }
        else
            this.price = price;
        return this.price;
    } // end of setPrice( )
    
    @Override
    public String toString() {
        return String.format ("%d %6.2f %s, by %s", 
                getItemID(), getPrice(), getTitle(), getAuthor());        
    }
   
}; // end of class Book
