package com.crymuzz.evotingapispring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "candidates")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String image;

    @OneToOne
    @JoinColumn(name = "party_id", nullable = false)
    private PartyEntity party;

    @ManyToOne
    @JoinColumn(name = "election_id", nullable = false)
    private ElectionEntity election;

}
