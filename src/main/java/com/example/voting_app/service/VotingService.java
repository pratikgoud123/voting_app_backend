package com.example.voting_app.service;

import com.example.voting_app.exception.UserAlreadyExistsException;
import com.example.voting_app.exception.UserNotFoundException;
import com.example.voting_app.model.Candidate;
import com.example.voting_app.model.User;
import com.example.voting_app.model.VoteDTO;

import java.util.List;
import java.util.Map;

public interface VotingService {
    public User addUser(User user) throws UserAlreadyExistsException;
    public User loginCheck(String emailId, String password) throws UserNotFoundException;
    public User getUserByEmailId(String emailId) throws UserNotFoundException;
    public Boolean vote(VoteDTO voteDTO);
    public Map<String, Integer> getCandidateNameAndVoteCount();
    public List<User> getAllUsers();
    public List<Candidate> getAllCandidates();

}
