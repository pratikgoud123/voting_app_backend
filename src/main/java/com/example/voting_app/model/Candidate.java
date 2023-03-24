/*
 * Author Name: Pratik Goud
 * Date: 21-03-2023
 * Created With: IntelliJ IDEA Community Edition
 */
package com.example.voting_app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidateId;
    @Column(name = "candidate_name")
    private String candidateName;
    @Column(name = "vote_counts")
    private int voteCounts;

    @JsonManagedReference
    @OneToMany(mappedBy = "candidate")
    private List<User> users;

    public Candidate(String candidateName, int voteCounts){
        this.candidateName=candidateName;
        this.voteCounts=voteCounts;
    }
}
