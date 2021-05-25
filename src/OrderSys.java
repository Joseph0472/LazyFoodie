import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class OrderSys implements DAO<Order> {
    static Map<String, Order> ordermap = new HashMap<>();
    static List<Order> orderlist = new ArrayList<>();
    /**
     * add an order
     */
    @Override
    public void insert(Order t) {
        ordermap.put(t.getOrderID(), t);

    }
    /**
     * Find order by id
     */
    @Override
    public Order findById(String id) {
        if (ordermap.get(id) == null) {
            return null;
        } else {
            return ordermap.get(id);
        }

    }
    /**
     * Find orders of a user by user id
     * @param uid
     * @return
     */
    public List<Order> findByuId(String uid) {
        List<Order> list = new ArrayList<>();
        Set<String> keys = ordermap.keySet();
        for (String key : keys) {
            if (Objects.equals(uid, ordermap.get(key).getuID())) {
                list.add(ordermap.get(key));
            }
        }
        return list;
    }

    /**
     * show all orders
     */
    @Override
    public List<Order> findAll() {
        Set<String> keys = ordermap.keySet();
        for (String key : keys) {
            orderlist.add(ordermap.get(key));
        }
        return orderlist;
    }
    /**
     * delete order
     */
    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub

    }
}