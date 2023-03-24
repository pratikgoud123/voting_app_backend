/*
 * Author Name: Pratik Goud
 * Date: 21-03-2023
 * Created With: IntelliJ IDEA Community Edition
 */
package com.example.voting_app.service;

import com.example.voting_app.exception.UserAlreadyExistsException;
import com.example.voting_app.exception.UserNotFoundException;
import com.example.voting_app.model.Candidate;
import com.example.voting_app.model.User;
import com.example.voting_app.model.VoteDTO;
import com.example.voting_app.repository.CandidateRepository;
import com.example.voting_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class VotingServiceImpl implements VotingService{

    private UserRepository userRepository;
    private CandidateRepository candidateRepository;
    private VoteDTO voteDTO;
    @Autowired
    public VotingServiceImpl(UserRepository userRepository, CandidateRepository candidateRepository, VoteDTO voteDTO) {
        this.userRepository = userRepository;
        this.candidateRepository = candidateRepository;
        this.voteDTO = voteDTO;
    }

    @PostConstruct
    public void init() {
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(new Candidate("candidate1", 0));
        candidates.add(new Candidate("candidate2", 0));
        candidates.add(new Candidate("candidate3", 0));
        candidates.add(new Candidate("candidate4", 0));
        candidateRepository.saveAll(candidates);

        List<User> users = new ArrayList<>();
        users.add(new User("admin","admin","admin",9898989898l, "admin",true));
        userRepository.saveAll(users);

    }

    @Override
    public User addUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getEmailId()).isPresent()){
            throw new UserAlreadyExistsException();
        }else if (user.getEmailId().equals("admin") && user.getPassword().equals("admin")){
            user.setRole("admin");
            userRepository.save(user);
        }else{
            user.setRole("user");
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public User loginCheck(String emailId, String password) throws UserNotFoundException {
        User user=userRepository.findByEmailIdAndPassword(emailId,password);
        if(user==null){
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User getUserByEmailId(String emailId) throws UserNotFoundException {
        if (userRepository.findByEmailId(emailId)==null){
            throw new UserNotFoundException();
        }
        return userRepository.findByEmailId(emailId);
    }

    @Override
    public Boolean vote(VoteDTO voteDTO) {
        String emailId = voteDTO.getEmailId();
        int candidateId = voteDTO.getCandidateId();
        User user = userRepository.findByEmailId(emailId);
        if (!user.isHasVoted()) {
            Candidate candidate = candidateRepository.findById(candidateId).get();
            candidate.setVoteCounts(candidate.getVoteCounts() + 1);
            candidateRepository.save(candidate);
            user.setHasVoted(true);
            user.setCandidate(candidate);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Integer> getCandidateNameAndVoteCount() {
        Map<String, Integer> results = new HashMap<>();
        List<Candidate> candidates = candidateRepository.findAll();
        for (Candidate candidate : candidates) {
            results.put(candidate.getCandidateName(), candidate.getVoteCounts());
        }
        return results;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

}
