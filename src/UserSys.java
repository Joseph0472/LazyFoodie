import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class UserSys implements DAO<User> {
    static Map<String, User> usermap = new HashMap<>();
    List<User> list = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    /**
     * Add user
     */
    @Override
    public void insert(User t) {
        usermap.put(t.getuID(), t);
    }

    /**
     * Check all users
     */
    @Override
    public List<User> findAll() {
        Set<String> keys = usermap.keySet();
        for (String str : keys) {
            list.add(usermap.get(str));
        }
        return list;
    }

    /**
     * Delete a user by id
     */
    @Override
    public void delete(String id) {
        if (usermap.get(id) == null) {
            System.out.println("Unknown user.");
        } else {
            System.out.println(usermap.get(id) + "deleted");
            usermap.remove(id);
        }
    }

    /**
     * Modify current user pwd
     */
    public void changepwd(String id) {
        User user = findById(id);
        System.out.println("Please input the new password：");
        String str = sc.next();
        User t = new User(user.getuID(), user.getUname(), str, user.getUaddress(), user.getUtel(),
                user.getUtime());
        usermap.put(id, t);
    }

    /**
     * Find user by Id
     */
    @Override
    public User findById(String id) {
        if (usermap.get(id) == null) {
            return null;
        } else {
            return usermap.get(id);
        }

    }

}