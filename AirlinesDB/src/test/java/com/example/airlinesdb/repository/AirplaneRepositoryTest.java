package com.example.airlinesdb.repository;

import com.example.airlinesdb.data.Airplane;
import com.example.airlinesdb.tables.Airplanes;
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
public class AirplaneRepositoryTest {

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
                Airplane airplane = new Airplane();
                airplane.setId(1000);
                Repository.db.airplanes.insert(airplane);

                Airplane result = Repository.db.airplanes.find(1000);
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
                Airplane airplane = new Airplane();
                airplane.setId(1);
                Repository.db.airplanes.update(airplane);

                Airplane result = Repository.db.airplanes.find(1);
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
    public void findTest() {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        MockDataProvider provider = new MockDataProvider() {
            @Override
            public MockResult[] execute(MockExecuteContext context)
                    throws SQLException {
                DSLContext dsl = DSL.using(connection);
                Repository.db.init(dsl);

                Airplane result = Repository.db.airplanes.find(1);
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
    public void findAllTest() {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        MockDataProvider provider = new MockDataProvider() {
            @Override
            public MockResult[] execute(MockExecuteContext context)
                    throws SQLException {
                DSLContext dsl = DSL.using(connection);
                Repository.db.init(dsl);

                List<Airplane> result = Repository.db.airplanes.findAll(Airplanes.AIRPLANES.ID.eq(2));
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

                Repository.db.airplanes.delete(1);
                assertThat(Repository.db.airplanes.find(1)).isNull();

                return new MockResult[] {
                        new MockResult(1, null)
                };
            }
        };
        Connection mockConnection = new MockConnection(provider);
        DSLContext dsl = DSL.using(mockConnection);
    }
}