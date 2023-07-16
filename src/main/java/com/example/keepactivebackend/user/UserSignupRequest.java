package com.example.keepactivebackend.user;

import lombok.AllArgsConstructor;
//import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
//@EqualsAndHashCode
@ToString
public record UserSignupRequest(String username, String email, String password) {
}
