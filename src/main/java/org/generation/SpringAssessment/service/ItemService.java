package org.generation.SpringAssessment.service;

import org.generation.SpringAssessment.repository.entity.Item;

import java.util.List;

public interface ItemService {
    List<Item> all();

    //this method is used for both add/edit item
    Item save(Item item);

    void delete(int itemId);

    Item findById(int itemId);

}


