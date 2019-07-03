package com.example.demo;

import com.example.demo.domain.Dog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Service
public class InMemoryStorage implements Storage
{
    private Map<Long, Dog> dogs = new HashMap<>();

    public InMemoryStorage() {}

    @Override
    public Dog storeDog(Dog dog)
    {
        return dogs.put(dog.getId(), dog);
    }

    @Override
    public Dog getDog(long id)
    {
        return dogs.get(id);
    }

    @Override
    public List<Dog> getAllDogs()
    {
        return new ArrayList<>(dogs.values());
    }
}
