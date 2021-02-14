package edu.authentcate.api.web;

import edu.authentcate.api.model.TermsAndConditions;
import edu.authentcate.api.model.User;
import edu.authentcate.api.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    private ApplicationService service;

    /**
     * This is post login API which does require an authentication.
     **/
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    /**
     * This is pre login API which does not require an authentication.
     **/
    @GetMapping("/terms")
    public TermsAndConditions getTermsAndConditions() {
        return service.getTermsAndConditions();
    }
}
