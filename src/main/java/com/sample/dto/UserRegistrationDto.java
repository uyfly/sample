package com.sample.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    private String email;
    private String password;
    private String confirmPassword;
    private String nickname;
    private String selfIntroduction;
}
