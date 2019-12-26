
public class BooksAndClothesInheritance {
    
    // define an array of books
    private static final Book[] BOOK_LIST = {
        new Book("ULYSSES", "James Joyce", 1918, 32.95, 16 ),
        new Book("THE GREAT GATSBY", "F. Scott Fitzgerald", 1925, 13.95, 30 ),
        new Book("BRAVE NEW WORLD", "Aldous Huxley", 1931 , 14.95, 28 ),
    };
    private static final int BOOK_COUNT = BOOK_LIST.length; // number of books
    
    // define an array of shirts
    private static final Shirt[] SHIRT_LIST = {
        new Shirt("T-shirt", "Guess", "M", "Blue", 14.95, 23),
        new Shirt("Dress shirt", "Ralph Lauren", "L", "White", 39.95, 5),
        new Shirt("Blouse", "Versace", "s", "Yellow", 44.95, 6),       
    };
    
    //Alan added:
    // define an array of pants
    private static final Pants[] PANTS_LIST = {
        new Pants("Jeans", "Levi's", 113, 82, "Black", 49.95, 26),
        new Pants("Khaki pants", "Van Heusen", 114, 86, "Red", 69.95, 14),
        new Pants("Yoga pants", "Banana Republic", 115, 90, "Gray", 15.95, 19),       
    };
 
    
    
    public static void main(String[] args) {
        // create some objects from inside main( )
        Book myBook = new Book("Dan the Programmer", "Dan McElroy", 2018, 14.95, 10);
        Shirt myShirt = new Shirt("T-shirt", "Guess", "M", "Blue", 14.95, 23);
        
        //Alan added:
        Pants myPants = new Pants("Jeans", "Levi's", 113, 82, "Black", 49.95, 26);
        
        // display the objects that were created in main( )
        System.out.println ("My first book is: " + myBook.getTitle());
        System.out.println ("My shirt selection: "+ myShirt.getType());
        
        //Alan added
        System.out.println ("My pants selection: "+ myPants.getType());
        
        // use a standard for statement to display all the books in BOOK_LIST
        //   using  i  to index through the array
        System.out.println ( ); // blank line
        for (int i=0; i<BOOK_COUNT; i++) {
               System.out.printf("%3d  %7.2f  %-30.30s\n", 
                BOOK_LIST[i].getInStock(), BOOK_LIST[i].getPrice(), BOOK_LIST[i].getTitle() );         
        }
        
       
        // use the enhanced-for statement to display all the shirts in SHIRT_LIST
        //   the variable  s  will refer to the current shirt as the for loop
        //   steps through the array, one book a time
        System.out.println ( ); // blank line
        for (Shirt s : SHIRT_LIST) {
            System.out.printf("%3d  %7.2f  %-30.30s\n", 
                s.getInStock(), s.getPrice(), s.getType() );
        } // end of for loop
        
        
        //Alan added
        System.out.println ( ); // blank line
        for (Pants s : PANTS_LIST) {
            System.out.printf("%3d  %7.2f  %-30.30s\n", 
                s.getInStock(), s.getPrice(), s.getType() );
        } // end of for loop       
        
        
        
        
        // display items in the arrays using the toString method
        System.out.println("\nBooks in the array");
        for (Book b : BOOK_LIST) { System.out.println(b); }
        
        System.out.println("\nShirts in the array");
        for (Shirt s : SHIRT_LIST) { System.out.println(s); }
        
        
        //Alan added
        System.out.println("\nPants in the array");
        for (Pants s : PANTS_LIST) { System.out.println(s); }       
        
    } // end of main()
    
} // end of class