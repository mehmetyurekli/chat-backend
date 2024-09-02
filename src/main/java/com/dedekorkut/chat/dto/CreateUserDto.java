package com.dedekorkut.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {

    private String name;
    private String surname;
    private String username;
}
