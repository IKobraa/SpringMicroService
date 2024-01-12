package de.thbingen.UserService.ports;

import de.thbingen.UserService.dto.*;
import de.thbingen.UserService.entity.Report;
import de.thbingen.UserService.entity.User;
import de.thbingen.UserService.entity.Messages;
import de.thbingen.UserService.response.LoginMessage;

import java.util.ArrayList;

public interface AdminServicePort {
    String addUser(UserDto userDto);
    User getUser(String email);
    String getUserD(String department);
    LoginMessage loginUser(LoginDto loginDto);
    LoginMessage logoutUser(LogoutDto logoutDto);
    String deleteUser(UserDto userDto);
    ArrayList<User> getAllUser();
    void saveAllUser(ArrayList<User> allUsers);
    void saveID(String userChatId);
    User getEmailByChatId(String email);
    String saveUserMessage(MessageDto messageDto);
    ArrayList<Messages> getAllMessage();
    void submitReport(ReportDto reportDto);
    ArrayList<Report> getAllReports();
    Report getReport(int id);
    void replyReport(String reply, int id);
    void deleteReport(int id);
    void updateUser(UserDto userDto);
}
