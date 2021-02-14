package edu.authentcate.api.service;

import edu.authentcate.api.model.TermsAndConditions;
import edu.authentcate.api.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class ApplicationService {

    public List<User> getAllUsers() {
        return Arrays.asList(User.builder().userId(1000001L).firstName("Mahadevan").lastName("Eswaran").build(),
                User.builder().userId(1000002L).firstName("Zeus").lastName("Titan").build(),
                User.builder().userId(1000003L).firstName("Odin").lastName("Wuodan").build());
    }

    public TermsAndConditions getTermsAndConditions() {
        return TermsAndConditions.builder().terms("Terms and Conditions are just a phrase or terms applicable for all conditions as long as we are human beings").since(LocalDate.now()).build();
    }
}
