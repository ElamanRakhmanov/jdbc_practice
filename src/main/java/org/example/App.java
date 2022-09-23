package org.example;

import org.example.model.City;
import org.example.model.Country;
import org.example.model.Person;
import org.example.util.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //People
//        createTable();
//        insertTable(1, "Sadyr Japarov", 32, "President");
//        insertTable(2, "Putin", 40, "President");
//        insertTable(3, "Tokaev", 37, "President");
//        insertTable(4, "Mirzoev", 35, "President");
//        insertTable(5, "Joe Biden", 45, "President");
//        getAllPeople();

        //People
//        createTableCountry();
//        insertTableCountry(1, "Kyrgyzstan", 1);
//        insertTableCountry(2, "Russia", 2);
//        insertTableCountry(3, "Kazakhstan", 3);
//        insertTableCountry(4, "Uzbekistan", 4);
//        insertTableCountry(5, "US", 5);
//        getAllCountries();


        //Country
//        createTableCity();
//        insertTableCity(1, "Bishkek", 1, 1);
//        insertTableCity(2,"Moscow", 2, 2);
//        insertTableCity(3,"Astana", 3, 3);
//        insertTableCity(4,"Tashkent", 4, 4);
//        insertTableCity(5,"New York", 5, 5);
//        getAllCities();


        System.out.println(getCityById(1));

    }

    public static void createTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS people(" +
                "id INT PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "age INT," +
                "position VARCHAR(50) NOT NULL)";
        try (
                Connection connect = db.connect();
                Statement statement = connect.createStatement()) {
            statement.executeUpdate(SQL);
            System.out.println("Created table successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertTable(int id, String name, int age, String position) {
        String SQL = "INSERT INTO people(id, name, age, position) VALUES (?, ?, ?, ?)";
        try (
                Connection connect = db.connect();
                PreparedStatement preparedStatement = connect.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, position);
            preparedStatement.executeUpdate();
            System.out.println("Successfully added " + name);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Person> getAllPeople() {
        String SQL = "SELECT * FROM people";
        List<Person> people = new ArrayList<>();
        try (
                Connection connect = db.connect();
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL)) {
            while (resultSet.next()) {
                Person person = new Person();
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String position = resultSet.getString("position");
                System.out.println(id + " " + name + " " + age + " " + position);
                person.setId(id);
                person.setName(name);
                person.setAge(age);
                person.setPosition(position);
                people.add(person);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return people;
    }


    public static void createTableCountry() {
        String SQL = "CREATE TABLE IF NOT EXISTS countries (" +
                "id INT PRIMARY KEY," +
                "nameCountry VARCHAR(50) UNIQUE," +
                "people_id INT REFERENCES people(id))";
        try (
                Connection connect = db.connect();
                Statement statement = connect.createStatement()) {
            statement.executeUpdate(SQL);
            System.out.println("Created table successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertTableCountry(int id, String nameCountry, int people_id) {
        String SQL = "INSERT INTO countries(id, nameCountry, people_id) VALUES (?, ?, ?)";
        try (
                Connection connect = db.connect();
                PreparedStatement preparedStatement = connect.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nameCountry);
            preparedStatement.setInt(3, people_id);
            preparedStatement.executeUpdate();
            System.out.println("Successfully added " + nameCountry);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Country> getAllCountries() {
        String SQL = "SELECT * FROM countries";
        List<Country> countries = new ArrayList<>();
        try (
                Connection connect = db.connect();
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL)) {
            while (resultSet.next()) {
                Country country = new Country();
                int id = resultSet.getInt("id");
                String nameCountry = resultSet.getString("nameCountry");
                int people_id = resultSet.getInt("people_id");
                System.out.println(id + " " + nameCountry + " " + people_id);
                country.setId(id);
                country.setNameCountry(nameCountry);
                country.setPeopleId(people_id);
                countries.add(country);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return countries;
    }


    public static void createTableCity() {
        String SQL = "CREATE TABLE IF NOT EXISTS cities(" +
                "id INT PRIMARY KEY," +
                "nameCity VARCHAR(30) NOT NULL," +
                "people_id INT REFERENCES people(id)," +
                "country_id INT REFERENCES countries(id))";
        try (
                Connection connect = db.connect();
                Statement statement = connect.createStatement()) {
            statement.executeUpdate(SQL);
            System.out.println("Created table successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertTableCity(int id, String nameCity, int people_id, int country_id) {
        String SQL = "INSERT INTO cities(id, nameCity, people_id, country_id) VALUES (?, ?, ?, ?)";
        try (
                Connection connect = db.connect();
                PreparedStatement preparedStatement = connect.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nameCity);
            preparedStatement.setInt(3, people_id);
            preparedStatement.setInt(4, country_id);
            preparedStatement.executeUpdate();
            System.out.println("Successfully added " + nameCity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<City> getAllCities() {
        String SQL = "SELECT * FROM cities";
        List<City> cities = new ArrayList<>();
        try (
                Connection connect = db.connect();
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL)) {
            while (resultSet.next()) {
                City city = new City();
                int id = resultSet.getInt("id");
                String nameCity = resultSet.getString("nameCity");
                int people_id = resultSet.getInt("people_id");
                int country_id = resultSet.getInt("country_id");
                System.out.println(id + " " + nameCity + " " + people_id + " " + country_id);
                city.setId(id);
                city.setNameCity(nameCity);
                city.setPeopleId(people_id);
                city.setCountryId(country_id);
                cities.add(city);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cities;
    }


    public static City getCityById(int id) {
        String SQL = "SELECT * FROM cities WHERE id = ?";
        City city = new City();
        try (Connection connect = db.connect();
             PreparedStatement statement = connect.prepareStatement(SQL)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                city.setId(resultSet.getInt("id"));
                city.setNameCity(resultSet.getString("namecity"));
                city.setPeopleId(resultSet.getInt("people_id"));
                city.setCountryId(resultSet.getInt("country_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return city;
    }
}
