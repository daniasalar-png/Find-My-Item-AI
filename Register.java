import java.util.Scanner;

public class Register {

    private Scanner sc;

    public Register(Scanner sc) {

        this.sc = sc;
    }

    public void registerUser() {

    System.out.println("\n===== USER REGISTRATION =====");

    System.out.print("Enter Full Name: ");
    String name = sc.nextLine();

    System.out.print("Enter Email: ");
    String email = sc.nextLine();

    System.out.print("Create Password: ");
    String password = sc.nextLine();

    UserDAO dao = new UserDAO();

    if (dao.emailExists(email)) {
        System.out.println("\nEmail already registered!");
        return;
    }

    User user = new User(name, email, password);

    if (dao.registerUser(user)) {
        System.out.println("\nRegistration Successful!");
    } else {
        System.out.println("\nRegistration Failed!");
    }
}
}