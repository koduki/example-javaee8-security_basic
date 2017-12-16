/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.orz.pascal.example.javaee8.security_basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.sql.DataSource;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@DataSourceDefinition(
        name = "java:global/MyAppDataSource",
        minPoolSize = 0,
        initialPoolSize = 0,
        className = "org.apache.derby.jdbc.ClientDataSource",
        user = "APP",
        password = "APP",
        databaseName = "myapp",
        properties = {"connectionAttributes=;create=true"}
)
@Singleton
@Startup
public class DatabaseSetup {

    @Resource(lookup = "java:global/MyAppDataSource")
    private DataSource dataSource;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @PostConstruct
    public void init() {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iterations", "3072");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
        passwordHash.initialize(parameters);

//        executeUpdate(dataSource, "DROP TABLE caller");
//        executeUpdate(dataSource, "DROP TABLE caller_groups");
        executeUpdate(dataSource, "CREATE TABLE caller(name VARCHAR(64) PRIMARY KEY, password VARCHAR(255))");
        executeUpdate(dataSource, "CREATE TABLE  caller_groups(caller_name VARCHAR(64), group_name VARCHAR(64))");

        executeUpdate(dataSource, "INSERT INTO caller VALUES('admin', '" + passwordHash.generate("secret1".toCharArray()) + "')");

        executeUpdate(dataSource, "INSERT INTO caller_groups VALUES('admin', 'admin')");
    }

    @PreDestroy
    public void destroy() {
        try {
            executeUpdate(dataSource, "DROP TABLE caller");
            executeUpdate(dataSource, "DROP TABLE caller_groups");
        } catch (Exception ex) {
            ex.printStackTrace();
            // silently ignore, concerns in-memory database
        }
    }

    private void executeUpdate(DataSource dataSource, String query) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }

}
