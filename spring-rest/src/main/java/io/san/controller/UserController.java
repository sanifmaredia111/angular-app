package io.san.controller;
import io.san.entity.User;
import io.san.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:63343")
public class UserController {

    @Autowired
    UserService service;

    //rest service to fetch all data
    @RequestMapping(method = RequestMethod.GET, value = "/users" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<User> findAll() {
        return service.findAll();
    }
    //rest service to fetch by id
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}",
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User findOne(@PathVariable("id") String userId) {
        return service.findOne(userId);
    }

    //rest service to post user
    @RequestMapping(method = RequestMethod.POST, value = "/users",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User create(@RequestBody User user) {

        return service.create(user);
    }

    //rest service to update user
    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}",
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User update(@PathVariable("id") String userId, @RequestBody User user) {
        return service.update(userId, user);
    }

    //rest service to delete user
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public void delete(@PathVariable("id") String userId) {
        service.delete(userId);
    }

}