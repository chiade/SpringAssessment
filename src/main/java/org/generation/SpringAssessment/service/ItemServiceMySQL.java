package org.generation.SpringAssessment.service;

import org.generation.SpringAssessment.repository.ItemRepository;
import org.generation.SpringAssessment.repository.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceMySQL implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceMySQL(@Autowired ItemRepository itemRepository)
    {
        //injecting an instance object of the CrudRepository Object
        //we are able to make use of this.itemRepository to access the properties and methods from the CrudRepository object

        this.itemRepository = itemRepository;
    }

    @Override
    public Item save(Item item) {
        //access the database w the connection and perform Insert query
        return itemRepository.save(item);   //CrudRepository object
    }

    @Override
    public void delete(int itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    public List<Item> all() {
        List<Item> result = new ArrayList<>();
        itemRepository.findAll().forEach(result :: add);
        return result;
    }

    @Override
    public Item findById(int itemId) {  //update product info and delete a product

        //item is an object
        Optional<Item> item = itemRepository.findById(itemId); //cannot be a null
        Item itemResponse = item.get();

        return itemResponse;
    }
}

