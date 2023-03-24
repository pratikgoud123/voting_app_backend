/*
 * Author Name: Pratik Goud
 * Date: 21-03-2023
 * Created With: IntelliJ IDEA Community Edition
 */
package com.example.voting_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    private String emailId;
    private String userName;
    private String password;
    private long phoneNumber;
    private String role;
    private boolean hasVoted;

    @JsonBackReference
    @ManyToOne
    private Candidate candidate;

    public User(String emailId, String userName, String password, long phoneNumber, String role, boolean hasVoted) {
        this.emailId = emailId;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.hasVoted = hasVoted;
    }
}
