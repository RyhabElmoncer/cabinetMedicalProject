package com.project.cabinet.DTO;

import com.project.cabinet.Enum.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private String lastName;
    private Role role;

    public UserDTO(Long id, String firstName) {
    }
}
