package com.example.fitness.dto;

public class UserProfileDto {

    private Long userId;
    private String name;
    private String email;
    private Integer age;
    private String gender;
    private Double heightCm;
    private Double weightKg;

    // getters & setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }
 
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }
 
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getHeightCm() {
        return heightCm;
    }
 
    public void setHeightCm(Double heightCm) {
        this.heightCm = heightCm;
    }

    public Double getWeightKg() {
        return weightKg;
    }
 
    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }
}
