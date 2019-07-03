package com.example.demo;

import com.example.demo.domain.Address;
import com.example.demo.domain.Dog;
import com.example.demo.jdbc.JDBCService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Project generator
 *
 * https://start.spring.io/
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	Storage getJdbcService()
	{
		return new JDBCService();
	}

//	@Bean
//	Storage getInMemoryStorage()
//	{
//		return new InMemoryStorage();
//	}

	@Bean
	CommandLineRunner init(Storage storage)
	{
		return env ->
		{
			for (Dog dog : generateDogs())
			{
				storage.storeDog(dog);
			}
		};
	}

	private List<Dog> generateDogs()
	{
		List<Dog> result = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < 5; i++)
		{
			Address address = new Address("Ukraine_" + i, "Kiev_" + i, i * 10);
			address.setId(i * 100);

			Dog dog = new Dog("Rex_" + i, random.nextInt(20));
			dog.setId(i * 1000);
			dog.setAddress(address);

			result.add(dog);
		}

		return result;
	}

}
