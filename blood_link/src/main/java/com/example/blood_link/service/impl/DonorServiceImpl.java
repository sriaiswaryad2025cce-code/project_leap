package com.example.blood_link.service.impl;

import com.example.blood_link.model.Donor;
import com.example.blood_link.repository.DonorRepository;
import com.example.blood_link.service.DonorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorServiceImpl implements DonorService {

    private final DonorRepository donorRepository;

    public DonorServiceImpl(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    @Override
    public Donor addDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    @Override
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    @Override
    public List<Donor> getByBloodGroup(String bloodGroup) {
        return donorRepository.findByBloodGroup(bloodGroup);
    }

    @Override
    public List<Donor> getByCity(String city) {
        return donorRepository.findByCity(city);
    }

    @Override
    public List<Donor> searchDonors(String bloodGroup, String city) {
        return donorRepository.findByBloodGroupAndCity(bloodGroup, city);
    }

    // PUT - Update donor
    @Override
    public Donor updateDonor(Long id, Donor donor) {

        Donor existingDonor = donorRepository.findById(id).orElse(null);

        if (existingDonor == null) {
            return null;
        }

        existingDonor.setName(donor.getName());
        existingDonor.setBloodGroup(donor.getBloodGroup());
        existingDonor.setCity(donor.getCity());
        existingDonor.setLastDonationDate(donor.getLastDonationDate());
        existingDonor.setAvailable(donor.isAvailable());

        return donorRepository.save(existingDonor);
    }

    // DELETE - Delete donor
    @Override
    public void deleteDonor(Long id) {
        donorRepository.deleteById(id);
    }
}