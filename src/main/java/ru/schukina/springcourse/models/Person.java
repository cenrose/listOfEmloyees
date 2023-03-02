package ru.schukina.springcourse.models;

import javax.validation.constraints.*;

public class Person {
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")//имя не может быть null или пустым
    @Size(min=2,max=30,message = "Name should contains min2 and max 30 char")
    private String name;
    @Min(value=0, message="Age could be min 0")
    @Max(value=130, message="Age could be max 130")
    private int age;

    @NotEmpty(message = "Email shouldn't be empty")
    @Email(message= "Email should be valid")
    private String email;
    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
    public Person(){ //пустой конструктор для пустой формы
    }
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}
