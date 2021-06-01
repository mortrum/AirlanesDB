package com.example.airlinesdb;

import com.example.airlinesdb.data.Country;
import com.example.airlinesdb.repository.Repository;
import com.github.javafaker.Faker;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        try(Connection connection = DriverManager.getConnection("jdbc:h2:~/jooq", "sa", "")) {
            DSLContext dsl = DSL.using(connection);
            Repository.db.init(dsl);

            Faker faker = new Faker();
            String name = faker.country().name();
            Repository.db.countries.insert(new Country(0, name, new ArrayList<>()));
            System.out.println("Its working!");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
