package com.example.demo.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    STUDENT_READ("student:read"),
    STUDENT_ENROLL("student:enroll"),
    Formateur_READ("trainer:read"),
    Formateur_UPDATE("trainer:update"),
   Formateur_CREATE("trainer:create"),
    Formateur_DELETE("trainer:delete"),
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete");

    @Getter
    private final String permission;


}
