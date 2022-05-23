package com.example.restinventorytracker.controller;

import com.example.restinventorytracker.data.ItemRepository;
import com.example.restinventorytracker.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/")
    public String defaultGreeting() {
        return "Rest Inventory Tracker";
    }

    @GetMapping("/index")
    public String showItemList(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "index";
    }

    @GetMapping("/items")
    public String showItems(Item item) {
        return "add-item";
    }

    @GetMapping("/addItem")
    public String addItem(Item item, Model model) {
        itemRepository.save(item);
        return "redirect:/index";
    }

    @GetMapping("/edit/{itemId}")
    public String showItem(@PathVariable("itemId") long itemId, Model model) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item id: " + itemId));

        model.addAttribute("item", item);
        return "update-item";
    }

    @PostMapping("/update/{itemId}")
    public String updateItem(@PathVariable("itemId") long itemId, Item item, Model model) {
        itemRepository.save(item);
        return "redirect:/index";
    }

    @GetMapping("/delete/{itemId}")
    public String deleteItem(@PathVariable("itemId") long itemId, Model model) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item id: " + itemId));
        itemRepository.delete(item);
        return "redirect:/index";
    }
}
