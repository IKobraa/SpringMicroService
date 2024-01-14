package de.thbingen.UserService.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user-id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name", length = 45)
    private String name;
    @Column(name = "surname", length = 45)
    private String surname;

    @Column(name = "email", length = 45, unique = true)
    private String email;
    @Column(name = "department", length = 45)
    private String department;
    @Column(name = "salary", length = 45)
    private double salary;
    @Column(name = "sick-days", length = 45)
    private int sickDays;
    @Column(name = "holidays", length = 45)
    private int holidays;
    @Column(name = "hourly-wage", length = 45)
    private double hourlyWage;
    @Column(name = "worked-hours", length = 45)
    private double workHours;
    @Column(name = "work-state", length = 45)
    private boolean workState;
    @Column(name = "password", length = 255)
    private String password;
    @Column(name = "lastLoginTime", length = 255)
    private LocalDateTime lastLoginTime;
    @Column(name = "chat-id", length = 255)
    private String chatId;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getSickDays() {
        return sickDays;
    }

    public void setSickDays(int sickDays) {
        this.sickDays = sickDays;
    }

    public int getHolidays() {
        return holidays;
    }

    public void setHolidays(int holidays) {
        this.holidays = holidays;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(double workHours) {
        this.workHours = workHours;
    }

    public boolean getWorkState() {
        return workState;
    }

    public void setWorkState(boolean workState) {
        this.workState = workState;
    }

    public User(int id, String name, String surname, String email, String department, String password){

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.department = department;
        this.password = password;


        this.salary = 0;
        this.sickDays = 0;
        this.holidays = 0;
        this.hourlyWage = 0;
        this.workHours = 0;
        this.workState = false;
    }
    public User() {

    }
    @Override
    public String toString() {
        return "---------------"+"\n"
        +"id: "+id+"\n"
        +"name: "+name+"\n"
        +"surname: "+surname+"\n"
        +"email: "+email+"\n"
        +"department: "+department+"\n"
        +"salary: "+salary+"\n"
        +"sick days: "+sickDays+"\n"
        +"holidays: "+holidays+"\n"
        +"hourly wage: "+hourlyWage+"\n"
        +"work hours: "+workHours+"\n"
        +"work sate: "+workState+"\n"
        +"---------------"+"\n";
    }

}
