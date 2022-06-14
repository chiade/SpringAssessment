package org.generation.SpringAssessment.repository;

import org.generation.SpringAssessment.repository.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer>
{
    //Not only the ItemRepository inherit all the methods from the CrudeRepository
    //Interface, ItemRepository and also have its own methods (don't need)
    //so now I can use the ItemRepository interface to perform basic CRUD operations

}