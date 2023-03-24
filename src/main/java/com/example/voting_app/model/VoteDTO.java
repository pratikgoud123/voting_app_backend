package com.example.voting_app.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VoteDTO {
    private String emailId;
    private int candidateId;
}
