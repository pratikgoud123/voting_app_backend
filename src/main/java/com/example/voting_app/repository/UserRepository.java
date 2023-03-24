package com.example.voting_app.repository;

import com.example.voting_app.exception.UserNotFoundException;
import com.example.voting_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findByEmailId(String emailId);
    User findByEmailIdAndPassword(String emailId, String password) throws UserNotFoundException;
}
