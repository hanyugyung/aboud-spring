package org.example;

import org.example.domain.Order.Order;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PersistenceOrder {

    private static Map<Long, Order> orderMap = new ConcurrentHashMap<>();

    public static List<Order> getList() {
        return orderMap.values().stream().toList();
    }

    public static int addOrder(Order order) {
        orderMap.put(order.getId(), order);
        return orderMap.size();
    }
}
