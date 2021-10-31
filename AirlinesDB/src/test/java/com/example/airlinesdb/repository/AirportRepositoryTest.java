package com.example.airlinesdb.repository;

import com.example.airlinesdb.data.Airplane;
import com.example.airlinesdb.data.Airport;
import com.example.airlinesdb.tables.Airplanes;
import com.example.airlinesdb.tables.Airports;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockExecuteContext;
import org.jooq.tools.jdbc.MockResult;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static com.example.airlinesdb.Constants.*;
import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
public class AirportRepositoryTest {

    private MockDataProvider provider;

    @BeforeEach
    @SneakyThrows
    void init() {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        DSLContext dsl = DSL.using(connection);
        Repository.db.init(dsl);
    }

    @AfterEach
    @SneakyThrows
    void tearDown() {
        Connection mockConnection = new MockConnection(provider);
        DSLContext dsl = DSL.using(mockConnection);
    }

    @Test
    @SneakyThrows
    public void insertTest() {
         provider = new MockDataProvider() {
            @Override
            public MockResult[] execute(MockExecuteContext context)
                    throws SQLException {
                Airport airport = new Airport();
                airport.setId(1000);
                airport.setName("#testing");
                airport.setCountryId(0);
                Repository.db.airports.insert(airport);

                Airport result = Repository.db.airports.find(1000);
                assertThat(result).isNotNull();

                return new MockResult[] {
                        new MockResult(1, null)
                };
            }
        };
    }

    @Test
    @SneakyThrows
    public void updateTest() {
        provider = new MockDataProvider() {
            @Override
            public MockResult[] execute(MockExecuteContext context)
                    throws SQLException {
                Airport airport = new Airport();
                airport.setId(1000);
                airport.setName("#testing");
                airport.setCountryId(0);
                Repository.db.airports.insert(airport);
                airport.setName("#testing-by-junit");
                Repository.db.airports.update(airport);

                Airport result = Repository.db.airports.find(1000);
                assertThat(result.getName()).endsWith("junit");

                return new MockResult[] {
                        new MockResult(1, null)
                };
            }
        };
    }

    @Test
    @SneakyThrows
    public void findTest() {
        provider = new MockDataProvider() {
            @Override
            public MockResult[] execute(MockExecuteContext context)
                    throws SQLException {

                Airport airport = new Airport();
                airport.setId(1000);
                airport.setName("#testing");
                airport.setCountryId(0);
                Repository.db.airports.insert(airport);

                Airport result = Repository.db.airports.find(1000);
                assertThat(result.getName()).endsWith("#testing");

                return new MockResult[] {
                        new MockResult(1, null)
                };
            }
        };
    }

    @Test
    @SneakyThrows
    public void findAllTest() {
        provider = new MockDataProvider() {
            @Override
            public MockResult[] execute(MockExecuteContext context)
                    throws SQLException {

                List<Airport> result = Repository.db.airports.findAll(Airports.AIRPORTS.COUNTRY_ID.eq(2));
                assertThat(result).hasSize(1);

                return new MockResult[] {
                        new MockResult(1, null)
                };
            }
        };
    }

    @Test
    @SneakyThrows
    public void deleteTest() {
        provider = new MockDataProvider() {
            @Override
            public MockResult[] execute(MockExecuteContext context)
                    throws SQLException {

                Repository.db.airports.delete(1);
                assertThat(Repository.db.airports.find(1)).isNull();

                return new MockResult[] {
                        new MockResult(1, null)
                };
            }
        };
    }
}