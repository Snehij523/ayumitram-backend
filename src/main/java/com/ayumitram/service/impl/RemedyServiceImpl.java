package com.ayumitram.service.impl;

import com.ayumitram.entity.Remedy;
import com.ayumitram.exception.ResourceNotFoundException;
import com.ayumitram.repository.RemedyRepository;
import com.ayumitram.service.RemedyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RemedyServiceImpl implements RemedyService {

    private final RemedyRepository remedyRepository;

    public RemedyServiceImpl(RemedyRepository remedyRepository) {
        this.remedyRepository = remedyRepository;
    }

    @Override
    public Remedy addRemedy(Remedy remedy) {
        return remedyRepository.save(remededy);
    }

    @Override
    public List<Remedy> getAllRemedies() {
        return remedyRepository.findAll();
    }

    @Override
    public Remedy getRemedyById(Long id) {
        return remedyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Remedy not found with id: " + id));
    }

    @Override
    public Remedy updateRemedy(Long id, Remedy remedyDetails) {
        Remedy remedy = getRemedyById(id);
        remedy.setHerbName(remedyDetails.getHerbName());
        remedy.setBenefits(remedyDetails.getBenefits());
        remedy.setUsageInstruction(remedyDetails.getUsageInstruction());
        remedy.setPrecautions(remedyDetails.getPrecautions());
        return remedyRepository.save(remedy);
    }

    @Override
    public void deleteRemedy(Long id) {
        Remedy remedy = getRemedyById(id);
        remedyRepository.delete(remedy);
    }
}