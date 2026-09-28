package com.example.blood_link;

import com.example.blood_link.dto.DonorRequestDTO;
import com.example.blood_link.dto.DonorResponseDTO;
import com.example.blood_link.exception.DuplicateResourceException;
import com.example.blood_link.exception.ResourceNotFoundException;
import com.example.blood_link.repository.DonorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * Integration tests for the BloodLink Donor service.
 */
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class BloodLinkApplicationTests {

    @Autowired
    private DonorService donorService;

    @Autowired
    private DonorRepository donorRepository;

    private DonorRequestDTO validDonorRequest;

    @BeforeEach
    void setUp() {
        validDonorRequest = DonorRequestDTO.builder()
                .firstName("Ravi")
                .lastName("Kumar")
                .dateOfBirth(LocalDate.of(1990, 5, 15))
                .gender("MALE")
                .bloodGroup("O+")
                .phoneNumber("9876543210")
                .email("ravi.kumar@example.com")
                .city("Chennai")
                .state("Tamil Nadu")
                .pincode("600001")
                .weightKg(70.0)
                .isAvailable(true)
                .build();
    }

    @Test
    @DisplayName("Context loads successfully")
    void contextLoads() {
        assertThat(donorService).isNotNull();
        assertThat(donorRepository).isNotNull();
    }

    @Test
    @DisplayName("Should register a new donor successfully")
    void shouldRegisterDonorSuccessfully() {
        DonorResponseDTO response = donorService.registerDonor(validDonorRequest);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isNotNull();
        assertThat(response.getFirstName()).isEqualTo("Ravi");
        assertThat(response.getLastName()).isEqualTo("Kumar");
        assertThat(response.getBloodGroup()).isEqualTo("O+");
        assertThat(response.getFullName()).isEqualTo("Ravi Kumar");
        assertThat(response.getIsAvailable()).isTrue();
    }

    @Test
    @DisplayName("Should throw DuplicateResourceException for duplicate email")
    void shouldThrowExceptionForDuplicateEmail() {
        donorService.registerDonor(validDonorRequest);

        DonorRequestDTO duplicate = DonorRequestDTO.builder()
                .firstName("Priya")
                .lastName("Sharma")
                .dateOfBirth(LocalDate.of(1995, 3, 10))
                .gender("FEMALE")
                .bloodGroup("A+")
                .phoneNumber("9123456789")
                .email("ravi.kumar@example.com") // duplicate email
                .city("Mumbai")
                .state("Maharashtra")
                .weightKg(55.0)
                .isAvailable(true)
                .build();

        assertThatThrownBy(() -> donorService.registerDonor(duplicate))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("ravi.kumar@example.com");
    }

    @Test
    @DisplayName("Should retrieve donor by ID")
    void shouldGetDonorById() {
        DonorResponseDTO created = donorService.registerDonor(validDonorRequest);
        DonorResponseDTO found = donorService.getDonorById(created.getId());

        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(created.getId());
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException for unknown ID")
    void shouldThrowExceptionForUnknownId() {
        assertThatThrownBy(() -> donorService.getDonorById(9999L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Should toggle donor availability")
    void shouldToggleAvailability() {
        DonorResponseDTO created = donorService.registerDonor(validDonorRequest);
        assertThat(created.getIsAvailable()).isTrue();

        DonorResponseDTO toggled = donorService.toggleAvailability(created.getId());
        assertThat(toggled.getIsAvailable()).isFalse();

        DonorResponseDTO toggledBack = donorService.toggleAvailability(created.getId());
        assertThat(toggledBack.getIsAvailable()).isTrue();
    }

    @Test
    @DisplayName("Should find donors by blood group")
    void shouldFindDonorsByBloodGroup() {
        donorService.registerDonor(validDonorRequest);

        List<DonorResponseDTO> donors = donorService.getDonorsByBloodGroup("O+");
        assertThat(donors).isNotEmpty();
        assertThat(donors).allMatch(d -> d.getBloodGroup().equals("O+"));
    }

    @Test
    @DisplayName("Should return donor statistics")
    void shouldReturnDonorStatistics() {
        donorService.registerDonor(validDonorRequest);

        Map<String, Object> stats = donorService.getDonorStatistics();
        assertThat(stats).containsKey("totalDonors");
        assertThat(stats).containsKey("availableDonors");
        assertThat(stats).containsKey("donorsByBloodGroup");
        assertThat((Long) stats.get("totalDonors")).isGreaterThan(0);
    }

    @Test
    @DisplayName("Should delete donor by ID")
    void shouldDeleteDonorById() {
        DonorResponseDTO created = donorService.registerDonor(validDonorRequest);
        donorService.deleteDonor(created.getId());

        assertThatThrownBy(() -> donorService.getDonorById(created.getId()))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
