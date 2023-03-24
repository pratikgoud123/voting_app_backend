/*
 * Author Name: Pratik Goud
 * Date: 21-03-2023
 * Created With: IntelliJ IDEA Community Edition
 */
package com.example.voting_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Login Failed!!")
public class UserNotFoundException extends Exception{
}
