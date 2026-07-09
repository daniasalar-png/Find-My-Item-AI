import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        DBConnection.getConnection();

        Scanner sc = new Scanner(System.in);

        Register register = new Register(sc);
        Login login = new Login(sc);

        while (true) {

            System.out.println("\n====== FIND MY ITEM AI ======");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {

                case 1:
                    register.registerUser();
                    break;

                case 2:
                    login.loginUser();
                    break;

                case 3:
                    System.out.println("Thank you for using Find My Item AI.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}