package org.softwarephoenix.testzadanie.entities.DTO;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password1;
    private String password2;
    private String fullName;
    private String avatar_url;
}
