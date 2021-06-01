package com.example.airlinesdb.repository;

import com.example.airlinesdb.data.Airport;
import com.example.airlinesdb.data.Country;
import com.example.airlinesdb.tables.Airports;
import com.example.airlinesdb.tables.Countries;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockExecuteContext;
import org.jooq.tools.jdbc.MockResult;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static com.example.airlinesdb.Constants.*;
import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
public class CountryRepositoryTest {

    @Test
    @SneakyThrows
    public void insertTest() {

        Faker faker = new Faker();
        String name1 = faker.country().name();

        System.out.println("Country insert test.");
        System.out.println("Name: " + name1);

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        MockDataProvider provider = new MockDataProvider() {
            @Override
            public MockResult[] execute(MockExecuteContext context)
                    throws SQLException {
                DSLContext dsl = DSL.using(connection);
                Repository.db.init(dsl);

                Country country = new Country();
                country.setId(1000);
                country.setName(name1);
                Repository.db.countries.insert(country);

                Country result = Repository.db.countries.find(1000);
                assertThat(result).isNotNull();

                return new MockResult[] {
                        new MockResult(1, null)
                };
            }
        };
        Connection mockConnection = new MockConnection(provider);
        DSLContext dsl = DSL.using(mockConnection);
    }

    @Test
    @SneakyThrows
    public void updateTest() {

        Faker faker = new Faker();
        String name1 = faker.country().name();
        String name2 = faker.country().name();

        System.out.println("Country name update test.");
        System.out.println("Name: " + name1);
        System.out.println("Editing to: " + name2);

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        MockDataProvider provider = new MockDataProvider() {
            @Override
            public MockResult[] execute(MockExecuteContext context)
                    throws SQLException {

                DSLContext dsl = DSL.using(connection);
                Repository.db.init(dsl);
                Country country = new Country();
                country.setId(1000);
                country.setName(name1);
                Repository.db.countries.insert(country);
                country.setName(name2);
                Repository.db.countries.update(country);

                Country result = Repository.db.countries.find(1000);
                assertThat(result.getName()).endsWith(name2);

                return new MockResult[] {
                        new MockResult(1, null)
                };
            }
        };
        Connection mockConnection = new MockConnection(provider);
        DSLContext dsl = DSL.using(mockConnection);
    }

    @Test
    @SneakyThrows
    public void findTest() {

        Faker faker = new Faker();
        String name1 = faker.country().name();

        System.out.println("Country find test.");
        System.out.println("Name: " + name1);

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        MockDataProvider provider = new MockDataProvider() {
            @Override
            public MockResult[] execute(MockExecuteContext context)
                    throws SQLException {
                DSLContext dsl = DSL.using(connection);
                Repository.db.init(dsl);

                Country country = new Country();
                country.setId(1000);
                country.setName(name1);
                Repository.db.countries.insert(country);

                Country result = Repository.db.countries.find(1000);
                assertThat(result.getName()).endsWith(name1);

                return new MockResult[] {
                        new MockResult(1, null)
                };
            }
        };
        Connection mockConnection = new MockConnection(provider);
        DSLContext dsl = DSL.using(mockConnection);
    }

    @Test
    @SneakyThrows
    public void findAllTest() {

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        MockDataProvider provider = new MockDataProvider() {
            @Override
            public MockResult[] execute(MockExecuteContext context)
                    throws SQLException {
                DSLContext dsl = DSL.using(connection);
                Repository.db.init(dsl);

                List<Country> result = Repository.db.countries.findAll(Countries.COUNTRIES.ID.eq(2));
                assertThat(result).hasSize(1);

                return new MockResult[] {
                        new MockResult(1, null)
                };
            }
        };
        Connection mockConnection = new MockConnection(provider);
        DSLContext dsl = DSL.using(mockConnection);
    }

    @Test
    @SneakyThrows
    public void deleteTest() {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        MockDataProvider provider = new MockDataProvider() {
            @Override
            public MockResult[] execute(MockExecuteContext context)
                    throws SQLException {
                DSLContext dsl = DSL.using(connection);
                Repository.db.init(dsl);

                Repository.db.countries.delete(1);
                assertThat(Repository.db.countries.find(1)).isNull();

                return new MockResult[] {
                        new MockResult(1, null)
                };
            }
        };
        Connection mockConnection = new MockConnection(provider);
        DSLContext dsl = DSL.using(mockConnection);
    }
}