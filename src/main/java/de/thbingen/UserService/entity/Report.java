package de.thbingen.UserService.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pending_reports")
public class Report {

    @Id
    @Column(name = "rep-id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "sender-Email", length = 255)
    private String senderEmail;
    @Column(name = "department", length = 45)
    private String department;
    @Column(name = "title", length = 255)
    private String title;
    @Column(name = "date/time", length = 255)
    private String dateTime;
    @Column(name = "problem", length = 1000)
    private String problem;

    @Column(name = "done", length = 45)
    private boolean done;
    @Column(name = "reply", length = 1000)
    private String reply;

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Report(int id, String senderEmail, String department, String title, String dateTime, String problem) {
        this.id = id;
        this.senderEmail = senderEmail;
        this.department = department;
        this.title = title;
        this.dateTime = dateTime;
        this.problem = problem;

    }

    public Report() {
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", senderEmail='" + senderEmail + '\'' +
                ", department='" + department + '\'' +
                ", title='" + title + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", problem='" + problem + '\'' +
                ", done=" + done +
                ", reply='" + reply + '\'' +
                '}';
    }
}
