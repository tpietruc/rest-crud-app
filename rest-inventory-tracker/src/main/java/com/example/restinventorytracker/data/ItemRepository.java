package com.example.restinventorytracker.data;

import com.example.restinventorytracker.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
