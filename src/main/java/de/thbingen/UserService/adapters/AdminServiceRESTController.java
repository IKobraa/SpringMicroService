package de.thbingen.UserService.adapters;

import de.thbingen.UserService.dto.*;
import de.thbingen.UserService.entity.Report;
import de.thbingen.UserService.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import de.thbingen.UserService.ports.AdminServicePort;
import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("api/admin")
public class AdminServiceRESTController {

    @Autowired
    private AdminServicePort AdminServicePort;
    @GetMapping
    public String greeting() {
        return "welcome to our homepage";
    }
    @GetMapping("admin/admin123")
    public boolean AdminLogin(){return true;}
    @PostMapping("add")
    public String addUser(@RequestBody UserDto userDto) {
        return AdminServicePort.addUser(userDto);
    }
    @PostMapping("saveAll")
    public void saveAllUser(@RequestBody ArrayList<User> allUsers) {AdminServicePort.saveAllUser(allUsers);}
    @GetMapping("user/all")
    public ArrayList<User> getAllUsers(){ return AdminServicePort.getAllUser();}
    @GetMapping("user/email/{email}")
    public User getUser(@PathVariable("email") String email) {
        return AdminServicePort.getUser(email);
    }
    //not implemented
    @GetMapping("user/department/{department}")
    public String getUserD(@PathVariable("department") String department) {
        return AdminServicePort.getUserD(department);
    }
    @DeleteMapping("delete")
    public String deleteUser(@RequestBody UserDto userDto) {
        return AdminServicePort.deleteUser(userDto);
    }
    @GetMapping("getAllReports")
    public ArrayList<Report> getAllReports(){return AdminServicePort.getAllReports();}
    @GetMapping("getReport/{rep-id}")
    public Report getReport(@PathVariable("rep-id") int id) {return AdminServicePort.getReport(id);}
    @PostMapping("ReportReply/{rep-id}")
    public void replyReport(@PathVariable("rep-id") int id, @RequestBody String reply) {AdminServicePort.replyReport(reply,id);}
    @PostMapping("updateUser")
    public void updateUser(@RequestBody UserDto userDto) { AdminServicePort.updateUser(userDto);}


}

