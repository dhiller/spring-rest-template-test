package de.dhiller.spring.rest.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;

    private List<OrderPosition> orderPositionList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderPosition> getOrderPositionList() {
        return orderPositionList;
    }

    public void setOrderPositionList(List<OrderPosition> orderPositionList) {
        this.orderPositionList = orderPositionList;
    }
}
