package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class StudentApp {

    public static void main(String[] args) {

        // Hibernate configuration
        Configuration configuration = new Configuration();

        Properties properties = new Properties();

        properties.put("hibernate.connection.driver_class",
                "com.mysql.cj.jdbc.Driver");

        properties.put("hibernate.connection.url",
                "jdbc:mysql://localhost:3306/studentdb");

        properties.put("hibernate.connection.username",
                "root");

        properties.put("hibernate.connection.password",
                "");

        properties.put("hibernate.dialect",
                "org.hibernate.dialect.MySQLDialect");

        properties.put("hibernate.hbm2ddl.auto",
                "update");

        properties.put("hibernate.show_sql",
                "true");

        configuration.setProperties(properties);

        configuration.addAnnotatedClass(Student.class);

        SessionFactory factory =
                configuration.buildSessionFactory();

        // Create Student object
        Student student = new Student(
                1,
                "Rahul",
                "rahul@gmail.com",
                "Information Technology"
        );

        // INSERT
        Session session = factory.openSession();

        session.beginTransaction();

        session.persist(student);

        session.getTransaction().commit();

        session.close();

        System.out.println("Student inserted successfully!");

        // UPDATE
        Session updateSession = factory.openSession();

        updateSession.beginTransaction();

        Student existingStudent =
                updateSession.get(Student.class, 1);

        existingStudent.setEmail("rahul123@gmail.com");
        existingStudent.setCourse("Computer Science");

        updateSession.getTransaction().commit();

        updateSession.close();

        System.out.println("Student updated successfully!");

        factory.close();
    }
}