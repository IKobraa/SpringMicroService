package de.thbingen.UserService.adapters;

import de.thbingen.UserService.dto.*;
import de.thbingen.UserService.entity.Messages;
import de.thbingen.UserService.entity.User;
import de.thbingen.UserService.response.LoginMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import de.thbingen.UserService.ports.AdminServicePort;
import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserServiceRESTController {

    @Autowired
    private AdminServicePort AdminServicePort;

    @PostMapping("saveUserMessage")
    public String saveUserMessage(@RequestBody MessageDto messageDto) { return AdminServicePort.saveUserMessage(messageDto);}
    @GetMapping("getAllMessages")
    public ArrayList<Messages> getAllMessage() { return AdminServicePort.getAllMessage();}
    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        LoginMessage loginMessage = AdminServicePort.loginUser(loginDto);
        return ResponseEntity.ok(loginMessage);
    }
    @PostMapping("logout")
    public ResponseEntity<?> logoutUser(@RequestBody LogoutDto logoutDto) {
        LoginMessage logoutMessage = AdminServicePort.logoutUser(logoutDto);
        return ResponseEntity.ok(logoutMessage);
    }
    @GetMapping("MapChatId/{email}")
    public User getEmailByChatId(@PathVariable("email") String email) { return AdminServicePort.getEmailByChatId(email);}
    @PostMapping("saveID")
    public void saveID(@RequestBody String userChatId) {  AdminServicePort.saveID(userChatId);}
    @PostMapping("submitReport")
    public void submitReport(@RequestBody ReportDto reportDto) { AdminServicePort.submitReport(reportDto);}
    @DeleteMapping("deleteReport/{rep-id}")
    public void deleteReport(@PathVariable("rep-id") int id) {
        AdminServicePort.deleteReport(id);
    }
}
