package com.ayumitram.service;

import com.ayumitram.entity.Remedy;
import java.util.List;

public interface RemedyService {
    Remedy addRemedy(Remedy remedy);
    List<Remedy> getAllRemedies();
    Remedy getRemedyById(Long id);
    Remedy updateRemedy(Long id, Remedy remedyDetails);
    void deleteRemedy(Long id);
}