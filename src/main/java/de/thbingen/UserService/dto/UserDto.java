package de.thbingen.UserService.dto;


public class UserDto {


    private int id;
    private String name;
    private String surname;
    private String email;
    private String department;
    private double salary;
    private int sickDays;
    private int holidays;
    private double hourlyWage;
    private double workHours;
    private boolean workState;
    private String password;

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

    public UserDto(int id, String name, String surname, String email, String department, String password){

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

    public UserDto(int id, String name, String surname, String email, String department, double salary, int sickDays, int holidays, double hourlyWage,
                   double workHours, boolean workState){

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.department = department;
        this.salary = salary;
        this.sickDays = sickDays;
        this.holidays = holidays;
        this.hourlyWage = hourlyWage;
        this.workHours = workHours;
        this.workState = workState;
    }
    public UserDto() {

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
