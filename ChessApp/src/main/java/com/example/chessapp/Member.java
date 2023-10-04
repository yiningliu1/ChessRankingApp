package com.example.chessapp;

public class Member {
    private String name;
    private Integer rank;
    private Integer grade;

    public Member(String name, Integer rank, Integer grade) {
        this.name = name;
        this.rank = rank;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public Integer getRank() {
        return rank;
    }

    public Integer getGrade() {
        return grade;
    }
}
