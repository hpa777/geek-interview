package ru.geekbrains.service;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class StudentDto {

    private Long id;

    @NotBlank
    private String name;

    @Min(value = 18)
    private Integer age;

    public StudentDto() {
    }

    public StudentDto(Long id, @NotBlank String name, @Min(value = 18) Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
