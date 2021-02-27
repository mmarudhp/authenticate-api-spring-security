package edu.authentcate.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private List<String> roles;

    @JsonIgnore
    public boolean isValid() {
        return this.roles.stream().anyMatch(role -> role.contains("ADMIN"));
    }
}
