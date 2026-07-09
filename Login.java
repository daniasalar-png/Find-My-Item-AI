import java.util.Scanner;

public class Login {

    private Scanner sc;

    public Login(Scanner sc) {

        this.sc = sc;
    }

    public void loginUser() {

    System.out.println("\n===== USER LOGIN =====");

    System.out.print("Enter Email: ");
    String email = sc.nextLine();

    System.out.print("Enter Password: ");
    String password = sc.nextLine();

    UserDAO dao = new UserDAO();

if (dao.loginUser(email, password)) {

    System.out.println("\nLogin Successful!");

    Dashboard dashboard = new Dashboard(sc);
    dashboard.showDashboard(email);

} else {

    System.out.println("\nInvalid Email or Password!");

}
}
}
