package com.example.cardapio.controller;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("food")
@CrossOrigin(origins = "http://localhost:4200")

public class FoodController {
    @Autowired
    private FoodRepository repository;

    @GetMapping
    public List<Food> getAll() {
        List<Food> foodlist = repository.findAll();
        return foodlist;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> getById(@PathVariable Long id) {
        Optional<Food> food = repository.findById(id);
        return food.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public void addFood(@RequestBody Food food) {
        List<Food> foodList = repository.findAll();
        foodList.add(food);
        repository.saveAll(foodList);
    }

    @DeleteMapping("/{id}")
    public void deleteFoodById(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteFoodAll(){
        repository.deleteAll();
    }

}
