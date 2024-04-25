import java.util.HashMap;
import java.util.Map;
import java.util.Random;

interface LibraryFacade {
    void borrowBook(String title, String author);
    void returnBook(String title);
    void searchBook(String title);
    void checkAvailability(String title);
}

class LibraryFacadeImpl implements LibraryFacade {
    private BookInventorySystem bookInventorySystem;
    private UserManagementSystem userManagementSystem;

    public LibraryFacadeImpl() {
        this.bookInventorySystem = new BookInventorySystem();
        this.userManagementSystem = new UserManagementSystem();
    }

    @Override
    public void borrowBook(String title, String author) {
        bookInventorySystem.borrowBook(title, author);
    }

    @Override
    public void returnBook(String title) {
        bookInventorySystem.returnBook(title);
    }

    @Override
    public void searchBook(String title) {
        bookInventorySystem.searchBook(title);
    }

    @Override
    public void checkAvailability(String title) {
        bookInventorySystem.checkAvailability(title);
    }
}
class BookInventorySystem {
    private Map<String, Boolean> bookAvailability;

    public BookInventorySystem() {
        bookAvailability = new HashMap<>();
        bookAvailability.put(generateRandomBookTitle(), true);
        bookAvailability.put(generateRandomBookTitle(), false);
        bookAvailability.put(generateRandomBookTitle(), true);
    }

    public void borrowBook(String title, String author) {
        if (bookAvailability.get(title) != null && bookAvailability.get(title)) {
            bookAvailability.put(title, false);
            System.out.println("Book '" + title + "' by " + author + " has been borrowed.");
        } else {
            System.out.println("Book '" + title + "' by " + author + " is not available for borrowing.");
        }
    }

    public void returnBook(String title) {
        if (bookAvailability.get(title) != null && !bookAvailability.get(title)) {
            bookAvailability.put(title, true);
            System.out.println("Book '" + title + "' has been returned.");
        } else {
            System.out.println("Invalid operation: Book '" + title + "' is already available.");
        }
    }

    public void searchBook(String title) {
        if (bookAvailability.containsKey(title)) {
            System.out.println("Book '" + title + "' is available.");
        } else {
            System.out.println("Book '" + title + "' is not available.");
        }
    }

    public void checkAvailability(String title) {
        if (bookAvailability.get(title) != null && bookAvailability.get(title)) {
            System.out.println("Book '" + title + "' is available for borrowing.");
        } else {
            System.out.println("Book '" + title + "' is not available for borrowing.");
        }
    }

    private String generateRandomBookTitle() {
        String[] titles = {"The Great Gatsby", "To Kill a Mockingbird", "1984", "Pride and Prejudice", "The Catcher in the Rye", "Lord of the Flies"};
        return titles[new Random().nextInt(titles.length)];
    }
}

class UserManagementSystem {

}

public class Main {
    public static void main(String[] args) {
        LibraryFacade libraryFacade = new LibraryFacadeImpl();

        libraryFacade.borrowBook("The Great Gatsby", "F. Scott Fitzgerald");
        libraryFacade.borrowBook("1984", "George Orwell");
        libraryFacade.returnBook("The Great Gatsby");

        libraryFacade.searchBook("The Great Gatsby");
        libraryFacade.searchBook("To Kill a Mockingbird");
        libraryFacade.searchBook("Harry Potter");

        libraryFacade.checkAvailability("1984");
        libraryFacade.checkAvailability("Pride and Prejudice");
        libraryFacade.checkAvailability("Lord of the Rings");
    }
}
