package com.ayumitram.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "remedies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Remedy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String herbName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String benefits;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String usageInstruction;

    @Column(columnDefinition = "TEXT")
    private String precautions;
}