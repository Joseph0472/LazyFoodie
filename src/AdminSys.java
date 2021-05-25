import java.time.LocalDate;
import java.time.LocalDateTime;
//import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
//import java.util.Set;

public class AdminSys implements DAO<Admin> {
    static Map<String, Admin> map = new HashMap<>();
    //  static Set<String> keys = map.keySet();
    UserSys u = new UserSys();
    OrderSys o = new OrderSys();
    DishesSys d = new DishesSys();
    Scanner sc = new Scanner(System.in);

    /**
     * Add Dishes
     */
    public void addDishes() {
        System.out.println("Please enter the dishes details: (type as: dish ID / Name / Type / Price / Sales number / Total stock)");
        String str = sc.next();
        String[] info = str.split("/");
        //
        if (info.length < 6) {
            System.out.println("Format error, please try again.");
            addDishes();
        } else {
            LocalDate dtime = LocalDate.now();
            Dishes t = new Dishes(info[0], info[1], info[2], dtime, Double.parseDouble(info[3]),
                    Integer.parseInt(info[4]), Integer.parseInt(info[5]));
            d.insert(t);
            System.out.println("Successfully added: " + info[1]);
        }
    }

    /**
     * Check all dishes
     */
    public void showAllDishes(int pageSize) {
        List<Dishes> list = d.findAll();
        int start = 0;
        // Use inf loop to implement paging
        while (true) {
            if (list.size() > (pageSize + start)) {
                System.out.println(list.subList(start, pageSize + start));
            } else {
                System.out.println(list.subList(start, list.size()));
                break;
            }
            start = start + pageSize;
        }
    }

    /**
     * Check dishes of a same type
     */
    public void selecBytypeOfAdmin() {
        System.out.println("Please input the type：");
        String typename = sc.next();
        d.selectBytype(typename);
    }

    /**
     * Modify price based on dish Id
     */
    public void selectByDishesID() {
        System.out.println("Please input the dish id：");
        String id = sc.next();
        Dishes dish = d.findById(id);
        if (dish == null) {
            System.out.println("Unknown dish");
        } else {
            System.out.println("The dish is ：" + dish);
            System.out.println("Please enter the new price：");
            double newPrice = sc.nextDouble();
            Dishes t = new Dishes(dish.getdID(), dish.getDname(), dish.getDtype(), dish.getDtime(), newPrice,
                    dish.getDsales(), dish.getDstocks());
            d.insert(t);
            System.out.println("Successfully modified " + d.findById(t.getdID()));
        }
    }

    /**
     * Delete a dish by Id
     */
    public void deleteDishes() {
        System.out.println("Please enter the id of the dish you want to remove: ");
        String id = sc.next();
        d.delete(id);
        System.out.println("Successfully deleted.");
    }

    /**
     * Add a user
     */
    public void addUser() {
        System.out.println("Please enter a user's info: (type as: User ID / Name / Password / Address / Phone number)");
        String str = sc.next();
        String[] info = str.split("/");
        if (info.length < 6) {
            System.out.println("Wrong info, please try again.");
            addUser();
        } else {
            LocalDateTime utime = LocalDateTime.now();
            u.insert(new User(info[0], info[1], info[2], info[3], info[4], utime));
            System.out.println("User successfully added.");
        }
    }

    /**
     * Check all users
     */
    public void findUser() {
        List<User> userlist = u.findAll();
        for (User user : userlist) {
            System.out.println(user);
        }
    }

    /**
     * Find a user by Id
     */
    public User findUserByid(String id) {
        return u.findById(id);
    }

    /**
     * Delete a user by Id
     */
    public void deleteUserByAdmin() {
        System.out.println("Please enter the id of the user you want to remove：");
        String id = sc.next();
        u.delete(id);
    }

    /**
     * Show all orders
     */
    public void showAllOrder() {
        List<Order> allOrder = o.findAll();
        for (Order order : allOrder) {
            System.out.println(order);
        }
    }

    /**
     * Modify order status by Id
     */
    public void changeOrderValue() {
        System.out.println("Please enter the order Id:");
        String id = sc.next();
        Order order = o.findById(id);
        if (order == null) {
            System.out.println("Order not found, please try again.");
        } else {
            System.out.println("Order found: " + order);
            System.out.println("Please enter the status：0:Not paid; 1：Paid; 2：In delivering; 3：Delivered.");
            int value = sc.nextInt();
            Order t = new Order(order.getOrderID(), order.getUtime(), order.getDishes(), order.getOrdernum(),
                    order.getuID(), order.getOrderprice(), value);
            o.insert(t);
            System.out.println("Successfully modified.");
        }

    }
    /**
     * Show all dishes
     */
    public void showAllDishesByUser() {
        List<Dishes> list = d.findAll();
        Collections.sort(list, (p1, p2) -> p1.getDsales() - p2.getDsales());
        System.out.println(list);
    }

    /**
     * Shop dishes and make an order
     */
    public void shopDishes(User user) {
        showAllDishesByUser();
        System.out.println("Please enter the ");
        String str = sc.next();
        String[] info = str.split("/");
        // Verify the input
        if (info.length < 2) {
            System.out.println("Wrong input, please try again.");
            shopDishes(user);
        } else {
            LocalDateTime l = LocalDateTime.now();
            // String orderID, LocalDateTime utime, Dishes dishes, int ordernum, String uID,
            // Double orderprice,int orderValue
            Order t = new Order(info[0], l, d.findById(info[0]), Integer.parseInt(info[1]), user.getuID(),
                    o.findById(info[0]).getOrderprice(), o.findById(info[0]).getOrderValue());
            o.insert(t);
            System.out.println("Order made!" + o.findById(info[0]));
        }
    }

    /**
     * Display all dishes by types
     */
    public void ShowOfTypeByUser() {
        System.out.println("Please input the type：");
        String str = sc.next();
        System.out.println(d.findByType(str));

    }

    /**
     * Check all orders of current user
     */
    public void showAllOrderByUser(User user) {
        List<Order> list = o.findByuId(user.getuID());
        for (Order order : list) {
            System.out.println(order);
        }
    }

    /**
     * Change pwd of current user
     */
    public void changePwdByUser(User user) {
        u.changepwd(user.getuID());
        System.out.println("Successfully changed!");
    }

    /**
     * Show info of current user
     */
    public void showByUser(User user) {
        User findById = u.findById(user.getuID());
        System.out.println(findById);
    }

    // Set dummy data
    public void addData() {
        map.put("Ajon", new Admin("001", "ajon", "123456"));
        LocalDate time = LocalDate.now();
        Dishes d1 = new Dishes("1", "Chicken Burger", "FastFood", time, 12.5, 20, 30);
        d.insert(d1);
        Dishes d2 = new Dishes("2", "Beef Burger", "FastFood", time, 15, 30, 20);
        d.insert(d2);
        Dishes d3 = new Dishes("3", "Chips", "FastFood", time, 5, 20, 30);
        d.insert(d3);
        Dishes d4 = new Dishes("4", "Veg Salad", "Veg", time, 10, 12, 15);
        d.insert(d4);
        Dishes d5 = new Dishes("5", "Juice", "Drink", time, 6, 70, 60);
        d.insert(d5);
        // String orderID, LocalDateTime utime, Dishes dishes, int ordernum, String uID,
        // Double orderprice,int orderValue
        LocalDateTime localdatetime = LocalDateTime.now();
        Order o1 = new Order("1", localdatetime, d1, 10, "1001", 60.0, 1);
        o.insert(o1);
        Order o2 = new Order("2", localdatetime, d2, 5, "1002", 50.0, 10);
        o.insert(o2);
        Order o3 = new Order("3", localdatetime, d3, 5, "1003", 40.0, 5);
        o.insert(o3);
        // String uID, String uname, String usex, String upwd, String uadress, String
        // utel, LocalDateTime utime
        User u1 = new User("1001", "Tom","123456", "City", "0211234567", localdatetime);
        u.insert(u1);
        User u2 = new User("1002", "Jack", "234567", "Avondale", "0211234568", localdatetime);
        u.insert(u2);
        User u3 = new User("1003", "Leslie","345678", "Sandringham", "0211234569", localdatetime);
        u.insert(u3);
    }

    @Override
    public void insert(Admin admin) {

    }

    @Override
    public Admin findById(String id) {
        return map.get(id);
    }

    @Override
    public List<Admin> findAll() {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}