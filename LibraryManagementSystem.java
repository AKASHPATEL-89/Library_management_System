import java.util.*;

class Book {
    String id, title, author;
    boolean isIssued;
    String issuedTo;
    Date dueDate;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }
}

class User {
    String username;
    String role; // "admin" or "user"

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }
}

public class LibraryManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static Map<String, Book> books = new HashMap<>();
    static Map<String, User> users = new HashMap<>();
    static User currentUser = null;

    public static void main(String[] args) {
        // Default users
        
        
          users.put("admin", new User("admin", "admin"));
         users.put("user1", new User("user1", "user"));

        while (true) {
            if (currentUser == null) {
                login();
            } else {
                if (currentUser.role.equals("admin")) {
                    adminMenu();
                } else {
                    userMenu();
                }
            }
        }
    }

    static void login() {
        System.out.print("Enter username (admin/user1): ");
        String uname = sc.nextLine();

        if (users.containsKey(uname)) {
            currentUser = users.get(uname);
            System.out.println("✅ Logged in as " + currentUser.role);
        } else {
            System.out.println("❌ User not found.");
        }
    }

    static void adminMenu() {
        System.out.println("\n📚 Admin Menu");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Logout");
        System.out.print("Choice: ");
        int ch = sc.nextInt(); sc.nextLine();

        switch (ch) {
            case 1: addBook(); break;
            case 2: viewBooks(); break;
            case 3: currentUser = null; break;
            default: System.out.println("Invalid choice.");
        }
    }

    static void userMenu() {
        System.out.println("\n👤 User Menu");
        System.out.println("1. View Available Books");
        System.out.println("2. Issue Book");
        System.out.println("3. Return Book");
        System.out.println("4. Logout");
        System.out.print("Choice: ");
        int ch = sc.nextInt(); sc.nextLine();

        switch (ch) {
            case 1: viewBooks(); break;
            case 2: issueBook(); break;
            case 3: returnBook(); break;
            case 4: currentUser = null; break;
            default: System.out.println("Invalid choice.");
        }
    }

    static void addBook() {
        System.out.print("Book ID: ");
        String id = sc.nextLine();
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Author: ");
        String author = sc.nextLine();

        Book b = new Book(id, title, author);
        books.put(id, b);
        System.out.println("✅ Book added.");
    }

    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n📘 Book List:");
        for (Book b : books.values()) {
            System.out.println("ID: " + b.id + " | Title: " + b.title + " | Author: " + b.author
                    + " | Issued: " + (b.isIssued ? "Yes to " + b.issuedTo : "No"));
            if (b.isIssued) {
                System.out.println("   Due: " + b.dueDate);
            }
        }
    }

    static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        String id = sc.nextLine();
        Book b = books.get(id);

        if (b == null) {
            System.out.println("❌ Book not found.");
        } else if (b.isIssued) {
            System.out.println("❌ Already issued to " + b.issuedTo);
        } else {
            b.isIssued = true;
            b.issuedTo = currentUser.username;

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, 14); // 2 weeks
            b.dueDate = c.getTime();

            System.out.println("✅ Book issued. Due date: " + b.dueDate);
        }
    }

    static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        String id = sc.nextLine();
        Book b = books.get(id);

        if (b == null || !b.isIssued || !b.issuedTo.equals(currentUser.username)) {
            System.out.println("❌ Cannot return this book.");
        } else {
            b.isIssued = false;
            b.issuedTo = null;
            b.dueDate = null;
            System.out.println("✅ Book returned.");
        }
    }
}
