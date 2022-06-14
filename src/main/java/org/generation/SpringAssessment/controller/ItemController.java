package org.generation.SpringAssessment.controller;

import org.generation.SpringAssessment.component.FileUploadUtil;
import org.generation.SpringAssessment.controller.dto.ItemDto;
import org.generation.SpringAssessment.repository.entity.Item;
import org.generation.SpringAssessment.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/item")

public class ItemController {
    //@Value("${image.folder}")
    //private String imageFolder;

    final ItemService itemService;

    public ItemController( @Autowired ItemService itemService )
    {
        this.itemService = itemService;
    }

    //1) create an API endpoint for GET HTTP request from the client
    //CORS (cross-origin resource sharing)
    @CrossOrigin
    @GetMapping( "/all" )
    public Iterable<Item> getItems()
    {
        return itemService.all();
    }

    //2) create an API endpoint for GET HTTP request from client by Id
    @CrossOrigin
    @GetMapping( "/{id}" )
    public Item findItemById( @PathVariable Integer id )
    {
        return itemService.findById( id );
    }

    //3) create an API endpoint for GET HTTP request from client by Id
    @CrossOrigin
    @DeleteMapping( "/{id}" )
    public void delete( @PathVariable Integer id )
    {
        itemService.delete( id );
    }

    //4) create an API endpoint for POST HTTP request from the client
    @CrossOrigin
    @PostMapping("/add")
    public void save(  @RequestParam(name="title", required = true) String title,
                       @RequestParam(name="description", required = true) String description,
                       @RequestParam(name="targetDate", required = true) Date targetDate) {

        //Prepare the fileName by cleaning up the path for saving the image file
        //Part I - upload the image file into the productImages folder in the server
/*
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        FileUploadUtil.saveFile(imageFolder, fileName, multipartFile);

        //Part 2 - save all the records in the MySQL  database
        //productImages/t-shirt1.jpg
        String fullPath = imageFolder + "/" + imageUrl;*/
        ItemDto itemDto = new ItemDto(title, description, targetDate);
        itemService.save(new Item(itemDto));
    }
}
