package com.example.demo;

import com.example.demo.domain.Address;
import com.example.demo.domain.Dog;
import com.example.demo.domain.DogBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

// https://docs.oracle.com/javase/tutorial/jaxb/intro/index.html
// https://javarush.ru/quests/lectures/questcollections.level03.lecture07
// https://javarush.ru/quests/lectures/questcollections.level03.lecture09

@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_XML_VALUE)
public class DogsXmlController
{
    @Autowired
    private Storage storage;

    @GetMapping(path = "/dogs")
    @ResponseBody
//    DogBox getAllDogs(@RequestParam String filter)
    DogBox getAllDogs()
    {
        DogBox dogBox = new DogBox();

//        List<Dog> result = new ArrayList<>();
//
//        for (Dog dog : storage.getAllDogs())
//        {
//            if (dog.getName().startsWith(filter))
//            {
//                result.add(dog);
//            }
//        }

        dogBox.setDogs(storage.getAllDogs());

        return dogBox;
    }

    @GetMapping(path = "/dogs/{dogId}")
    @ResponseBody
    Dog getDog(@PathVariable long dogId)
    {
        return storage.getDog(dogId);
    }

    @GetMapping(path = "/dogs/{dogId}/address")
    @ResponseBody
    Address getAllAddress(@PathVariable long dogId)
    {
        return storage.getDog(dogId).getAddress();
    }
}
