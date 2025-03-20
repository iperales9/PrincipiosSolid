/*
 * EJERCICIO:
 * Explora el "Principio SOLID de Responsabilidad Única (Single Responsibility
 * Principle, SRP)" y crea un ejemplo simple donde se muestre su funcionamiento
 * de forma correcta e incorrecta.
 *
 * DIFICULTAD EXTRA (opcional):
 * Desarrolla un sistema de gestión para una biblioteca. El sistema necesita
 * manejar diferentes aspectos como el registro de libros, la gestión de usuarios
 * y el procesamiento de préstamos de libros.
 * Requisitos:
 * 1. Registrar libros: El sistema debe permitir agregar nuevos libros con
 * información básica como título, autor y número de copias disponibles.
 * 2. Registrar usuarios: El sistema debe permitir agregar nuevos usuarios con
 * información básica como nombre, número de identificación y correo electrónico.
 * 3. Procesar préstamos de libros: El sistema debe permitir a los usuarios
 * tomar prestados y devolver libros.
 * Instrucciones:
 * 1. Diseña una clase que no cumple el SRP: Crea una clase Library que maneje
 * los tres aspectos mencionados anteriormente (registro de libros, registro de
 * usuarios y procesamiento de préstamos).
 * 2. Refactoriza el código: Separa las responsabilidades en diferentes clases
 * siguiendo el Principio de Responsabilidad Única.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ejercicio26 {
    public static void main(String[] args) {
        IBookManager bookManager = new BookManager();
        IUserManager userManager = new UserManager();
        ILoanManager loanManager = new LoanManager();

        Library library = new Library(bookManager, userManager, loanManager);

        // Añadir libros
        library.addBook(new Book("Cien años de soledad", "Gabriel García Márquez", 5));
        library.addBook(new Book("1984", "George Orwell", 3));

        // Añadir usuarios
        library.addUser(new User("Juan Pérez", 1, "juan@example.com"));
        library.addUser(new User("Ana Gómez", 2, "ana@example.com"));

        // Prestar libros
        library.loan_book(1, "Cien años de soledad");
        library.loan_book(2, "1984");

        // Devolver libros
        library.return_book(1, "Cien años de soledad");
    }

    interface IBookManager {
        void addBook(Book book);

        void removeBook(Book book);

        List<Book> getBooks();
    }

    interface IUserManager {
        void addUser(User user);

        void removeUser(User user);

        List<User> getUsers();
    }

    interface ILoanManager {
        void loanBook(User user, Book book);

        void returnBook(User user, Book book);
    }

    static class Book {
        private String title;
        private String author;
        private int copies;

        public Book(String title, String author, int copies) {
            this.title = title;
            this.author = author;
            this.copies = copies;
        }

        public String getTitle() {
            return title;
        }

        public int getCopies() {
            return copies;
        }

        public void setCopies(int copies) {
            this.copies = copies;
        }

        @Override
        public String toString() {
            return "Book{" + "title='" + title + '\'' + ", author='" + author + '\'' + ", copies=" + copies + '}';
        }
    }

    static class User {
        private int id;
        private String name;
        private String email;

        public User(String name, int id, String email) {
            this.name = name;
            this.id = id;
            this.email = email;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "User{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
        }
    }

    static class Loan {
        private User user;
        private Book book;

        public Loan(User user, Book book) {
            this.user = user;
            this.book = book;
        }
    }

    static class BookManager implements IBookManager {
        private List<Book> books = new ArrayList<>();

        @Override
        public void addBook(Book book) {
            books.add(book);
        }

        @Override
        public void removeBook(Book book) {
            books.remove(book);
        }

        @Override
        public List<Book> getBooks() {
            return books;
        }
    }

    static class UserManager implements IUserManager {
        private List<User> users = new ArrayList<>();

        @Override
        public void addUser(User user) {
            users.add(user);
        }

        @Override
        public void removeUser(User user) {
            users.remove(user);
        }

        @Override
        public List<User> getUsers() {
            return users;
        }
    }

    static class LoanManager implements ILoanManager {
        private Map<User, List<Book>> borrowedBooks = new HashMap<>();

        @Override
        public void loanBook(User user, Book book) {
            if (book.getCopies() > 0) {
                borrowedBooks.computeIfAbsent(user, k -> new ArrayList<>()).add(book);
                book.setCopies(book.getCopies() - 1);
                System.out.println("Libro prestado");
            } else {
                System.out.println("No hay copias disponibles");
            }
        }

        @Override
        public void returnBook(User user, Book book) {
            if (borrowedBooks.containsKey(user)) {
                boolean removed = borrowedBooks.get(user).removeIf(b -> b.equals(book));
                if (removed) {
                    book.setCopies(book.getCopies() + 1);
                    System.out.println("Libro devuelto");
                } else {
                    System.out.println("El usuario no tiene prestado ese libro");
                }
            } else {
                System.out.println("El usuario no tiene préstamos registrados");
            }
        }
    }

    static class Library {
        private IBookManager bookManager;
        private IUserManager userManager;
        private ILoanManager loanManager;

        public Library(IBookManager bookManager, IUserManager userManager, ILoanManager loanManager) {
            this.bookManager = bookManager;
            this.userManager = userManager;
            this.loanManager = loanManager;
        }

        public void addBook(Book book) {
            bookManager.addBook(book);
        }

        public void addUser(User user) {
            userManager.addUser(user);
        }

        public void loan_book(int id_user, String book_title) {
            User user = userManager.getUsers().stream().filter(u -> u.getId() == id_user).findAny().orElse(null);

            Book book = bookManager.getBooks().stream().filter(b -> b.getTitle().equals(book_title)).findAny().orElse(null);

            if (user == null) {
                System.out.println("Usuario no encontrado");
                return;
            }

            if (book == null) {
                System.out.println("Libro no encontrado");
                return;
            }

            loanManager.loanBook(user, book);
        }

        public void return_book(int id_user, String book_title) {
            User user = userManager.getUsers().stream().filter(u -> u.getId() == id_user).findAny().orElse(null);

            Book book = bookManager.getBooks().stream().filter(b -> b.getTitle().equals(book_title)).findAny().orElse(null);

            if (user == null) {
                System.out.println("Usuario no encontrado");
                return;
            }

            if (book == null) {
                System.out.println("Libro no encontrado");
                return;
            }

            loanManager.returnBook(user, book);
        }
    }
}