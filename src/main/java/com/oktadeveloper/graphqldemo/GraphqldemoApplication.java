package com.oktadeveloper.graphqldemo;

import com.oktadeveloper.graphqldemo.entity.Food;
import com.oktadeveloper.graphqldemo.service.FoodService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class GraphqldemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqldemoApplication.class, args);
		System.out.println("test");
	}

	@Bean
	ApplicationRunner init(FoodService foodService) {
		return args -> {
			Stream.of("Pizza", "Spam", "Eggs", "Avocado").forEach(name -> {
				Food food = new Food();
				food.setName(name);
				foodService.saveFood(food);
			});
			foodService.getFoods().forEach(System.out::println);
		};
	}
}
