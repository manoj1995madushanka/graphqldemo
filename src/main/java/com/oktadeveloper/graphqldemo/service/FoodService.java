package com.oktadeveloper.graphqldemo.service;

import com.oktadeveloper.graphqldemo.entity.Food;
import com.oktadeveloper.graphqldemo.repository.FoodRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@GraphQLApi
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    // READ ALL
    @GraphQLQuery(name = "foods")
    public List<Food> getFoods() {
        return foodRepository.findAll();
    }

    // READ BY ID
    @GraphQLQuery(name = "food")
    public Optional<Food> getFoodById(@GraphQLArgument(name = "id") Long id) {
        return foodRepository.findById(id);
    }

    // CREATE
    @GraphQLMutation(name = "saveFood")
    public Food saveFood(@GraphQLArgument(name = "food") Food food) {
        return foodRepository.save(food);
    }

    // DELETE
    @GraphQLMutation(name = "deleteFood")
    public void deleteFood(@GraphQLArgument(name = "id") Long id) {
        foodRepository.deleteById(id);
    }

    // Calculated property of Food
    @GraphQLQuery(name = "isGood")
    public boolean isGood(@GraphQLContext Food food) {
        return !Arrays.asList("Avocado", "Spam").contains(food.getName());
    }
}
