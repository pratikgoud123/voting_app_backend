/*
 * Author Name: Pratik Goud
 * Date: 21-03-2023
 * Created With: IntelliJ IDEA Community Edition
 */
package com.example.voting_app.controller;

import com.example.voting_app.exception.UserAlreadyExistsException;
import com.example.voting_app.exception.UserNotFoundException;
import com.example.voting_app.model.LoginResponse;
import com.example.voting_app.model.User;
import com.example.voting_app.model.VoteDTO;
import com.example.voting_app.service.JWTGenerator;
import com.example.voting_app.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping
public class VotingController {
    private final VotingService votingService;
    private final JWTGenerator jwtGenerator;
    private final LoginResponse loginResponse;

    @Autowired
    public VotingController(VotingService votingService, JWTGenerator jwtGenerator, LoginResponse loginResponse) {
        this.votingService = votingService;
        this.jwtGenerator = jwtGenerator;
        this.loginResponse = loginResponse;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<?> insertUser(@RequestBody User user) throws UserAlreadyExistsException {
        try {
            return new ResponseEntity<>(votingService.addUser(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        } catch (Exception e) {
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {
        try {
            User user1 = votingService.loginCheck(user.getEmailId(), user.getPassword());
            Map<String, String> secretKey = new HashMap<>();
            secretKey = jwtGenerator.generateToken(user);
            loginResponse.setUser(user1);
            loginResponse.setSecretKeyToken(secretKey);
            return new ResponseEntity<>(loginResponse, HttpStatus.CREATED);
        } catch(UserNotFoundException e){
            throw new UserNotFoundException();
        } catch (Exception e){
            return new ResponseEntity<>("Network Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUserByEmailId/{emailId}")
    public ResponseEntity<?> getUserByEmailId(@PathVariable String emailId) throws UserNotFoundException{
        try {
            return new ResponseEntity<>(votingService.getUserByEmailId(emailId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/api/v1/vote")
    public ResponseEntity<?> vote(@RequestBody VoteDTO voteDTO) {
        Boolean success = votingService.vote(voteDTO);
        if (success) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/api/v1/candidateDetails")
    public ResponseEntity<?> candidateDetails() {
        return new ResponseEntity<>(votingService.getCandidateNameAndVoteCount(), HttpStatus.OK);
    }

    @GetMapping("/api/v1/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(votingService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/api/v1/getAllCandidates")
    public ResponseEntity<?> getAllCandidates() {
        return new ResponseEntity<>(votingService.getAllCandidates(), HttpStatus.OK);
    }

}
