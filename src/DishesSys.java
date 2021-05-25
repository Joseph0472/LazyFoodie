import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DishesSys implements DAO<Dishes> {
    // A map of dishes
    static Map<String, Dishes> dishesmap = new HashMap<>();
    Set<String> keys = dishesmap.keySet();

    /**
     * Add dishes
     */
    @Override
    public void insert(Dishes t) {
        dishesmap.put(t.getdID(), t);
    }

    /**
     * Find a dish by Id
     */
    @Override
    public Dishes findById(String id) {
        if (dishesmap.get(id) == null) {
            return null;
        } else {
            return dishesmap.get(id);
        }
    }

    /**
     * Find dishes by type
     */
    public List<Dishes> findByType(String type) {
        List<Dishes> list = new ArrayList<>();
        for (String key : keys) {
            if (Objects.equals(type, dishesmap.get(key).getDtype())) {
                list.add(dishesmap.get(key));
            }

        }
        return list;
    }

    /**
     * Show all dishes
     */
    @Override
    public List<Dishes> findAll() {
        List<Dishes> list = new ArrayList<>();

        for (String str : keys) {
            list.add(dishesmap.get(str));
        }
        return list;
    }

    public void selectBytype(String typename) {
        int count = 0;
        for (String key : keys) {
            if (Objects.equals(dishesmap.get(key).getDtype(), typename)) {
                System.out.println(dishesmap.get(key));
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No dishes in such type.");
        }
    }

    /**
     * Delete a dish by id
     */
    @Override
    public void delete(String id) {
        if (dishesmap.get(id) == null) {
            System.out.println("Can't find this dish.");
        } else {
            dishesmap.remove(id);
        }

    }
}