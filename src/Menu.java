import java.util.Objects;
import java.util.Scanner;

public class Menu {
    static AdminSys admin = new AdminSys();
    int pageSize = 0;
    Scanner sc = new Scanner(System.in);

    public void enterMenu() {
        admin.addData();

        System.out.println("Enter 1 if you're a user, 2 if you're an admin:");
        String identity = sc.next();
        if(identity.equals("1")) {
            showMenu();
        } else if(identity.equals("2")) {
            adminMenu();
        } else {
            System.out.println("Wrong input, try again.");
            enterMenu();
        }
    }

    public void showMenu() {
        System.out.println("Please input the userID and Pwd: (type as: userID / pwd )");
        String str = sc.next();
        String[] info = str.split("/");
        if (info.length < 2) {
            System.out.println("Miss something? please try again.");
            showMenu();
        } else {
            if (admin.findById(info[0]) != null && Objects.equals(admin.findById(info[0]).getAccountPWD(), info[1])) {
                adminMenu();
            } else if (admin.findUserByid(info[0]) != null
                    && Objects.equals(info[1], admin.findUserByid(info[0]).getUpwd())) {
                User user = admin.findUserByid(info[0]);
                userMenu(user);
            } else {
                System.out.println("Wrong information.");
                showMenu();
            }
        }

    }

    public void userMenu(User user) {
        System.out.println("=========Welcome to LazyFoodie Food Order System=======");
        System.out.println("===== [1] Order dishes ==================");
        System.out.println("===== [2] Show dishes by type ===========");
        System.out.println("===== [3] Show all my orders ============");
        System.out.println("===== [4] Change password ===============");
        System.out.println("===== [5] Show account info =============");
        System.out.println("===== [6] Exit ==========================");
        System.out.println("Enter the number and go to the next step: ");
        String n = sc.next();
        switch (n) {
            case "1":
                admin.shopDishes(user);
                userMenu(user);
                break;
            case "2":
                admin.ShowOfTypeByUser();
                userMenu(user);
                break;
            case "3":
                admin.showAllOrderByUser(user);
                userMenu(user);
                break;
            case "4":
                admin.changePwdByUser(user);
                userMenu(user);
                break;
            case "5":
                admin.showByUser(user);
                userMenu(user);
                break;
            case "6":
                System.out.println("Thanks, Bye");
                System.exit(0);
            default:
                System.out.println("Wrong input, please try again.");
                userMenu(user);
        }
    }

    public void adminMenu() {
        System.out.println("========= Hello Admin =====================");
        System.out.println("==== [1] Add dishes =======================");
        System.out.println("==== [2] Check all dishes' info ===========");
        System.out.println("==== [3] Check dishes by type =============");
        System.out.println("==== [4] Change the price of the dish =====");
        System.out.println("==== [5] Delete a dish ====================");
        System.out.println("==== [6] Add a user =======================");
        System.out.println("==== [7] Check all users ==================");
        System.out.println("==== [8] Delete a user ====================");
        System.out.println("==== [9] Check all orders =================");
        System.out.println("==== [10] Change the order status =========");
        System.out.println("==== [11] Exit ============================");
        String m = sc.next();
        switch (m) {
            case "1":
                admin.addDishes();
                adminMenu();
                break;
            case "2":
                if(pageSize==0) {
                    System.out.println("How many rows in a page：");
                    pageSize = sc.nextInt();
                }
                admin.showAllDishes(pageSize);
                adminMenu();
                break;
            case "3":
                admin.selecBytypeOfAdmin();
                adminMenu();
                break;
            case "4":
                admin.selectByDishesID();
                adminMenu();
                break;
            case "5":
                admin.deleteDishes();
                adminMenu();
                break;
            case "6":
                admin.addUser();
                adminMenu();
                break;
            case "7":
                admin.findUser();
                adminMenu();
                break;
            case "8":
                admin.deleteUserByAdmin();
                adminMenu();
                break;
            case "9":
                admin.showAllOrder();
                adminMenu();
                break;
            case "10":
                admin.changeOrderValue();
                adminMenu();
                break;
            case "11":
                System.out.println("Thanks, Bye~");
                System.exit(0);
                break;
            default:
                System.out.println("Wrong input, please try again.");
                adminMenu();
        }
    }
}
