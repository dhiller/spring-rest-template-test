package de.dhiller.spring.rest.controller;

import de.dhiller.spring.rest.model.Order;
import de.dhiller.spring.rest.model.OrderPosition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class OrderController {

    private final AtomicInteger orderNumber = new AtomicInteger(1);
    private final HashMap<Integer, Order> orders = new LinkedHashMap<>();

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getOrdersForm(Model model) {
        model.addAttribute("orders", orders.entrySet());
        return "orders";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public String createOrder(Model model) {
        final Order order = new Order();
        order.setId(orderNumber.getAndIncrement());
        orders.put(order.getId(), order);
        model.addAttribute("orders", orders.entrySet());
        return "orders";
    }

    @RequestMapping(value = "/orders/{orderId:\\d+}", method = RequestMethod.GET)
    public String getOrder(@PathVariable("orderId") int orderId, Model model) {
        model.addAttribute("order", orders.get(orderId));
        model.addAttribute("items", ItemController.ITEMS);
        return "order";
    }

    @RequestMapping(value = "/orders/{orderId:\\d+}", method = RequestMethod.POST)
    public String addPosition(@PathVariable("orderId") int orderId, @RequestBody HashMap<String, String> formData, Model model) {
        Order order = orders.get(orderId);
        OrderPosition orderPosition = new OrderPosition();
        String itemId = formData.get("itemId");
        orderPosition.setItem(ItemController.ITEMS.get(Integer.valueOf(itemId)));
        order.getOrderPositionList().add(orderPosition);
        model.addAttribute("oder", order);
        return "order";
    }

}
