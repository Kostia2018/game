package com.game.entity;

import javax.persistence.*;
import java.util.Date;



@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String title;
    @Column
    @Enumerated(EnumType.STRING)
    private Race race;
    @Column
    @Enumerated(EnumType.STRING)
    private Profession profession;
    @Column
    private Integer experience;
    @Column
    private Integer level;
    @Column
    private Integer untilNextLevel;
    @Column
    private Date birthday;
    @Column
    private Boolean banned;


    public Player() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }



    public int getCurrentLevel() {
        return (int) (Math.sqrt(2500+200*experience)-50)/100;
    }
    public int nextLevel() {
        return 50*(level+1)*(level+2)-experience ;
    }




}
