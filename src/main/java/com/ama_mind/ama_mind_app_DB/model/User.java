package com.ama_mind.ama_mind_app_DB.model;

import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class User {

    @Id 
    private String userKey;

    @NotBlank
    private String password;

    @NotBlank
    private String firstcategorie;

}
