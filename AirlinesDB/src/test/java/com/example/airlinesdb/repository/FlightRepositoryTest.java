package com.example.airlinesdb.repository;

import com.example.airlinesdb.data.Airport;
import com.example.airlinesdb.data.Country;
import com.example.airlinesdb.data.Flight;
import com.example.airlinesdb.tables.Airports;
import com.example.airlinesdb.tables.Flights;
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
import java.time.LocalDate;
import java.util.List;

import static com.example.airlinesdb.Constants.*;
import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
public class FlightRepositoryTest {

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

        System.out.println("Flight insert test.");

        provider = new MockDataProvider() {
            @Override
            public MockResult[] execute(MockExecuteContext context)
                    throws SQLException {

                Flight flight = new Flight();
                flight.setId(1000);
                flight.setDate(LocalDate.now());
                flight.setDepartureAirportId(0);
                flight.setDestinationAirportId(1);
                Repository.db.flights.insert(flight);

                Flight result = Repository.db.flights.find(1000);
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

                Flight flight = new Flight();
                flight.setId(1000);
                flight.setDate(LocalDate.now());
                flight.setDepartureAirportId(0);
                flight.setDestinationAirportId(1);
                Repository.db.flights.insert(flight);
                flight.setDestinationAirportId(4);
                Repository.db.flights.update(flight);

                Flight result = Repository.db.flights.find(1000);
                assertThat(result.getDestinationAirportId()).isEqualTo(4);

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

                Flight flight = new Flight();
                flight.setId(1000);
                flight.setDate(LocalDate.now());
                flight.setDepartureAirportId(0);
                flight.setDestinationAirportId(1);
                Repository.db.flights.insert(flight);

                Flight result = Repository.db.flights.find(1000);
                assertThat(result.getId()).isEqualTo(1000);

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

                List<Flight> result = Repository.db.flights.findAll(Flights.FLIGHTS.AIRPLANE_ID.eq(2));
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

                Repository.db.flights.delete(1);
                assertThat(Repository.db.flights.find(1)).isNull();

                return new MockResult[] {
                        new MockResult(1, null)
                };
            }
        };
    }
}