package com.example.blood_link.controller;

import com.example.blood_link.model.Donor;
import com.example.blood_link.service.DonorService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donors")
public class DonorController {

    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    // 1. Add donor
    @PostMapping
    public Donor addDonor(@RequestBody Donor donor) {
        return donorService.addDonor(donor);
    }

    // 2. Get all donors
    @GetMapping
    public List<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }

    // 3. Search donors by blood group
    @GetMapping("/blood/{bloodGroup}")
    public List<Donor> getByBloodGroup(
            @PathVariable String bloodGroup) {

        return donorService.getByBloodGroup(bloodGroup);
    }

    // 4. Search donors by city
    @GetMapping("/city/{city}")
    public List<Donor> getByCity(
            @PathVariable String city) {

        return donorService.getByCity(city);
    }

    // 5. Search donors by blood group and city
    @GetMapping("/search")
    public List<Donor> searchDonors(
            @RequestParam String bloodGroup,
            @RequestParam String city) {

        return donorService.searchDonors(bloodGroup, city);
    }

    // 6. Update donor
    @PutMapping("/{id}")
    public Donor updateDonor(
            @PathVariable Long id,
            @RequestBody Donor donor) {

        return donorService.updateDonor(id, donor);
    }

    // 7. Delete donor
    @DeleteMapping("/{id}")
    public String deleteDonor(@PathVariable Long id) {

        donorService.deleteDonor(id);

        return "Donor deleted successfully";
    }
}