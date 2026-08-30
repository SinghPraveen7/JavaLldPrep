package org.practice.design.builder;

/**
 * Builder Pattern is a creational design pattern used to construct complex objects step-by-step.
 * It is particularly useful when a class has many optional fields, as it avoids constructor explosion and improves readability.
 * The builder class collects all required data and creates the final object through a build() method.
 * Builder Pattern is commonly used with immutable objects and supports method chaining by returning this from setter methods.
 * In modern Spring Boot applications, Lombok's @Builder annotation is frequently used to generate builder code automatically.
 */
public class BuilderMain {
    public static void main(String[] args) {
        User user = new User.Builder("Praveen", "praveen@gmail.com")
                .setAddress("Delhi")
                .setPhoneNo("9876543210")
                .setAge(12)
                .build();
        System.out.println(user.name);
        System.out.println(user.phoneNo);
    }
}
