package de.dhiller.spring.rest.controller;

import de.dhiller.spring.rest.model.Item;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@RestController
public class ItemController {

    public static final Item APPLE = new Item(1, "Apple");
    public static final Item BANANA = new Item(2, "Banana");
    public static final Item PEAR = new Item(3, "Pear");

    public static final Map<Integer,Item> ITEMS
            = asList(
            APPLE,
            BANANA,
            PEAR
    ).stream()
            .collect(Collectors.toMap(
                    Item::getId,
                    Function.identity()
            ));

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public List<Item> getItems() {
        return asList(
                APPLE,
                BANANA,
                PEAR
        );
    }

}
