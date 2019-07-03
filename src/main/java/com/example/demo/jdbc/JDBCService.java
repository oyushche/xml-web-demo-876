package com.example.demo.jdbc;

import com.example.demo.Storage;
import com.example.demo.domain.Address;
import com.example.demo.domain.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


// You can look inside DB via http://localhost:8080/h2-console
//@Service
public class JDBCService implements Storage
{
    private Connection connection;

    @Autowired
    private DBSchemeManager dbSchemeManager;

    @Override
    public Dog storeDog(Dog dog)
    {
        String dogSql = "INSERT INTO DOG (NAME, AGE) VALUES (?, ?)";
        String addressSql = "INSERT INTO ADDRESS (COUNTRY, CITY, HOUSE, FK_DOG) VALUES (?, ?, ?, ?)";
        PreparedStatement statement;

        try
        {
            statement = connection.prepareStatement(dogSql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, dog.getName());
            statement.setInt(2, dog.getAge());
            statement.executeUpdate();

            statement.getGeneratedKeys().next();
            long dogId = statement.getGeneratedKeys().getLong(1);

            statement.close();

            statement = connection.prepareStatement(addressSql);
            statement.setString(1, dog.getAddress().getCountry());
            statement.setString(2, dog.getAddress().getCity());
            statement.setInt(3, dog.getAddress().getHome());
            statement.setLong(4, dogId);
            statement.executeUpdate();
            statement.close();

        }
        catch (SQLException e)
        {
            // ignore
        }

        return null;
    }

    @Override
    public Dog getDog(long id)
    {
        Dog result = null;

        String sql = "SELECT * FROM DOG d, ADDRESS  a WHERE d.ID = a.FK_DOG AND d.ID = ?";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                result = getDogFromResultSet(resultSet);
            }

            statement.close();
        }
        catch (SQLException e)
        {
            // ignore
        }
        return result;
    }

    @Override
    public List<Dog> getAllDogs()
    {
        List<Dog> result = new ArrayList<>();

        String sql = "SELECT * FROM DOG d, ADDRESS  a WHERE d.ID = a.FK_DOG";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                result.add(getDogFromResultSet(resultSet));
            }

            statement.close();
        }
        catch (SQLException e)
        {
            // ignore
        }
        return result;
    }

    private Dog getDogFromResultSet(ResultSet resultSet) throws SQLException
    {
        Dog dog;
        dog = new Dog();
        dog.setId(resultSet.getLong(1));
        dog.setName(resultSet.getString(2));
        dog.setAge(resultSet.getInt(3));

        Address address = new Address();
        address.setId(resultSet.getLong(4));
        address.setCountry(resultSet.getString(5));
        address.setCity(resultSet.getString(6));
        address.setHome(resultSet.getInt(7));

        dog.setAddress(address);

        return dog;
    }

    @PostConstruct
    public void openConnection()
    {
        try
        {
            Class.forName("org.h2.Driver"); // this is driver for H2
            connection = DriverManager.getConnection("jdbc:h2:~/dogs",
                    "sa", // login
                    "" // password
            );
            dbSchemeManager.initDataBase(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void closeConnection()
    {
        if (null != connection)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                // ignore
            }
        }
    }

}
