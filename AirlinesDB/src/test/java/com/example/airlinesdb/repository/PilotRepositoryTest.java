package com.example.airlinesdb.repository;

import com.example.airlinesdb.data.Flight;
import com.example.airlinesdb.data.Pilot;
import com.example.airlinesdb.tables.Flights;
import com.example.airlinesdb.tables.Pilots;
import lombok.SneakyThrows;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockExecuteContext;
import org.jooq.tools.jdbc.MockResult;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static com.example.airlinesdb.Constants.*;
import static com.google.common.truth.Truth.assertThat;

public class PilotRepositoryTest {
    @Test
    @SneakyThrows
    public void insertTest() {

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        MockDataProvider provider = new MockDataProvider() {
            @Override
            public MockResult[] execute(MockExecuteContext context)
                    throws SQLException {
                DSLContext dsl = DSL.using(connection);
                Repository.db.init(dsl);

                Pilot pilot = new Pilot(1000, "Alex", 5000);
                Repository.db.pilots.insert(pilot);

                Pilot result = Repository.db.pilots.find(1000);
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

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        MockDataProvider provider = new MockDataProvider() {
            @Override
            public MockResult[] execute(MockExecuteContext context)
                    throws SQLException {

                DSLContext dsl = DSL.using(connection);
                Repository.db.init(dsl);
                Pilot pilot = new Pilot(1000, "Alex", 5000);
                Repository.db.pilots.insert(pilot);
                pilot.setName("Max");
                Repository.db.pilots.update(pilot);

                Pilot result = Repository.db.pilots.find(1000);
                assertThat(result.getName()).contains("Max");

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

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        MockDataProvider provider = new MockDataProvider() {
            @Override
            public MockResult[] execute(MockExecuteContext context)
                    throws SQLException {
                DSLContext dsl = DSL.using(connection);
                Repository.db.init(dsl);

                Pilot pilot = new Pilot(1000, "Alex", 5000);
                Repository.db.pilots.insert(pilot);

                Pilot result = Repository.db.pilots.find(1000);
                assertThat(result.getId()).isEqualTo(1000);

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

                List<Pilot> result = Repository.db.pilots.findAll(Pilots.PILOTS.ID.eq(2));
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

                Repository.db.pilots.delete(1);
                assertThat(Repository.db.pilots.find(1)).isNull();

                return new MockResult[] {
                        new MockResult(1, null)
                };
            }
        };
        Connection mockConnection = new MockConnection(provider);
        DSLContext dsl = DSL.using(mockConnection);
    }
}