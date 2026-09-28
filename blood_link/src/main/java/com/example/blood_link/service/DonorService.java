package com.example.blood_link.service;

import com.example.blood_link.model.Donor;

import java.util.List;

public interface DonorService {

    Donor addDonor(Donor donor);

    List<Donor> getAllDonors();

    List<Donor> getByBloodGroup(String bloodGroup);

    List<Donor> getByCity(String city);

    List<Donor> searchDonors(String bloodGroup, String city);
}