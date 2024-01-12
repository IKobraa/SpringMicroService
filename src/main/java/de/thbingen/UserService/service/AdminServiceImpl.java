package de.thbingen.UserService.service;

import de.thbingen.UserService.dto.*;
import de.thbingen.UserService.entity.Report;
import de.thbingen.UserService.entity.User;
import de.thbingen.UserService.entity.Messages;
import de.thbingen.UserService.ports.AdminServicePort;
import de.thbingen.UserService.repo.MessageRepo;
import de.thbingen.UserService.repo.ReportRepo;
import de.thbingen.UserService.repo.UserRepo;
import de.thbingen.UserService.response.LoginMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminServicePort {

    @Autowired
    private ReportRepo reportRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    User tmpUser;

    @Override
    public String addUser(UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getEmail(),
                userDto.getDepartment(),
                this.passwordEncoder.encode(userDto.getPassword())
        );

        userRepo.save(user);
        return "User "+user.getName()+" was added to database";
    }

    @Override
    public User getUser(String email) {
        return userRepo.findByEmail(email);
    }

    //not implemented
    @Override
    public String getUserD(String department) {
       return "done";
    }

    @Override
    public LoginMessage loginUser(LoginDto loginDto) {


        User user = userRepo.findByEmail(loginDto.getEmail());

        if(user != null){
            String password = loginDto.getPassword();
            String encPassword = user.getPassword();
            boolean correctPassword = passwordEncoder.matches(password,encPassword);
            if(correctPassword) {
                Optional<User> userPresent = userRepo.findOneByEmailAndPassword(loginDto.getEmail(), encPassword);
                if(userPresent.isPresent()) {
                    user.setWorkState(true);
                    user.setSalary(user.getWorkHours()*user.getHourlyWage());
                    user.setLastLoginTime(LocalDateTime.now());
                    tmpUser = user;
                    userRepo.save(user);
                    return new LoginMessage("Login was successful!",true);
                } else {
                    return new LoginMessage("Login failed!",true);
                }
            }else{
                return new LoginMessage("Login failed!",false);
            }
        }else {
            return new LoginMessage("Login failed!",false);
        }
    }

    @Override
    public LoginMessage logoutUser(LogoutDto logoutDto) {

        User user = userRepo.findByEmail(logoutDto.getEmail());

        if(user != null) {
            user.setWorkState(false);
            userRepo.save(user);
            LocalDateTime now = LocalDateTime.now();
           Duration diff = Duration.between(user.getLastLoginTime(),now);
            //not sure if toHours() also has the minutes and seconds
            user.setWorkHours(diff.toHours());
            userRepo.save(user);
            return new LoginMessage("user was logged out! "
                    +"time spent working: "+diff.toHours()+":"+diff.toMinutes()+":"+diff.toSeconds(),true);
        }
        else {
            return new LoginMessage("user was not found!",false);
        }
    }

    @Override
    public String deleteUser(UserDto userDto) {

        User user = userRepo.findByEmail(userDto.getEmail());
        if(user != null) {
            userRepo.delete(user);
        }
        else {
            return "User was not found!";
        }
        return "User was deleted!";
    }

    @Override
    public ArrayList<User> getAllUser() {

        ArrayList<User> allUsers = new ArrayList<>();
        Iterable<User> it = userRepo.findAll();
        for (User user : it) {
            allUsers.add(user);
        }
        return allUsers;
    }

    @Override
    public void saveAllUser(ArrayList<User> allUsers) {
        userRepo.saveAll(allUsers);
    }

    @Override
    public void saveID(String userChatId) {

        tmpUser.setChatId(userChatId);
        userRepo.save(tmpUser);
    }

    @Override
    public User getEmailByChatId(String email) {

        Optional<User> user = userRepo.findByChatId(email);
        if(user.isPresent()){
            System.out.println(user.get().getChatId());
            return user.get();
        }
        return null;
    }

    @Override
    public String saveUserMessage(MessageDto messageDto) {

        System.out.println("in saveUserMessage()");
        Messages message = new Messages(
                messageDto.getId(),
                messageDto.getReceiverEmail(),
                messageDto.getSenderEmail(),
                messageDto.getMessage()
        );
        messageRepo.save(message);
        return "saved";
    }

    @Override
    public ArrayList<Messages> getAllMessage() {

        ArrayList<Messages> allMessages = new ArrayList<>();
        Iterable<Messages> it = messageRepo.findAll();
        for (Messages msg : it) {
            allMessages.add(msg);
        }
        Collections.reverse(allMessages);
        return allMessages;
    }

    @Override
    public void submitReport(ReportDto reportDto) {

        Report report = new Report(
                reportDto.getId(),
                reportDto.getSenderEmail(),
                reportDto.getDepartment(),
                reportDto.getTitle(),
                reportDto.getDateTime(),
                reportDto.getProblem()
        );
        reportRepo.save(report);

    }

    @Override
    public ArrayList<Report> getAllReports() {

        ArrayList<Report> allReports = new ArrayList<>();
        Iterable<Report> it = reportRepo.findAll();
        for (Report r : it) {
            allReports.add(r);
        }
        return allReports;
    }

    @Override
    public Report getReport(int id) {

        if(reportRepo.getReportById(id).isPresent()){
            return reportRepo.getReportById(id).get();
        }

        return null;
    }

    @Override
    public void replyReport(String reply, int id) {

        System.out.println("reply: "+reply);
        if(reportRepo.getReportById(id).isPresent()){
            System.out.println("in if() replyReport()");
            Report rep = reportRepo.getReportById(id).get();
            System.out.print(rep);
            rep.setReply(reply);
            rep.setDone(true);
            reportRepo.save(rep);
        }else
            System.out.println("failed!");

    }

    @Override
    public void deleteReport(int id) {

        if(reportRepo.getReportById(id).isPresent()){
            Report report = reportRepo.getReportById(id).get();
            reportRepo.delete(report);
        }

    }

    @Override
    public void updateUser(UserDto userDto) {

        System.out.println(userDto);
        User user = userRepo.findByEmail(userDto.getEmail());

        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setDepartment(userDto.getDepartment());
        user.setEmail(userDto.getEmail());
        user.setSalary(userDto.getSalary());
        user.setWorkHours(userDto.getWorkHours());
        user.setWorkState(userDto.getWorkState());
        user.setHolidays(userDto.getHolidays());
        user.setHourlyWage(userDto.getHourlyWage());
        user.setSickDays(userDto.getSickDays());
        userRepo.save(user);

    }
}
