package edu.authentcate.api.web;

import edu.authentcate.api.model.TermsAndConditions;
import edu.authentcate.api.model.User;
import edu.authentcate.api.service.ApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    private ApplicationService service;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/terms")
    public TermsAndConditions getTermsAndConditions() {
        return service.getTermsAndConditions();
    }
}
