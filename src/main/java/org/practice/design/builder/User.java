package org.practice.design.builder;

public class User {

    String name;
    String email;
    String address;
    String phoneNo;
    Integer age;

    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.address = builder.address;
        this.phoneNo = builder.phoneNo;
        this.age = builder.age;
    }

    static class Builder {
        String name;
        String email;
        String address;
        String phoneNo;
        Integer age;

        // Mandatory fields
        public Builder(String name, String email) {
            this.name = name;
            this.email = email;
        }

        // Optional fields
        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
            return this;
        }

        public Builder setAge(Integer age) {
            this.age = age;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

}
