package ec.edu.espe.banquito.requirements.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ec.edu.espe.banquito.requirements.controller.DTO.GuarantorRS;
import ec.edu.espe.banquito.requirements.model.Guarantor;
import ec.edu.espe.banquito.requirements.repository.GuarantorRepository;

@RunWith(MockitoJUnitRunner.class)
public class GuarantorServiceTests {
    @Mock
    private GuarantorRepository guarantorRepository;

    @InjectMocks
    private GuarantorService guarantorService;

    @Test
    public void testObtainExistingGuarantorByCode() {
        Guarantor simulatedGuarantor = new Guarantor();
        simulatedGuarantor.setId(1);
        simulatedGuarantor.setName("John Doe");
        simulatedGuarantor.setCode("12345");

        when(guarantorRepository.findByCode("12345")).thenReturn(simulatedGuarantor);

        GuarantorRS result = guarantorService.obtainByCode("12345");

        verify(guarantorRepository).findByCode("12345");

        assertNotNull(result);
        assertEquals(1, result.getId().intValue());
        assertEquals("John Doe", result.getName());
    }

    @Test
    public void testObtainNonExistingGuarantorByCode() {
        when(guarantorRepository.findByCode("67890")).thenReturn(null);

        GuarantorRS result = guarantorService.obtainByCode("67890");

        verify(guarantorRepository).findByCode("67890");
        assertNull(result);
    }

    @Test
    public void testObtainExistingGuarantorByName() {
        Guarantor simulatedGuarantor = new Guarantor();
        simulatedGuarantor.setId(1);
        simulatedGuarantor.setName("John Doe");
        simulatedGuarantor.setCode("12345");

        when(guarantorRepository.findByName("John Doe")).thenReturn(simulatedGuarantor);

        GuarantorRS result = guarantorService.obtainByName("John Doe");

        verify(guarantorRepository).findByName("John Doe");

        assertNotNull(result);
        assertEquals(1, result.getId().intValue());
        assertEquals("12345", result.getCode());
    }

    @Test
    public void testObtainNonExistingGuarantorByName() {
        when(guarantorRepository.findByName("John Doe")).thenReturn(null);

        GuarantorRS result = guarantorService.obtainByName("John Doe");

        verify(guarantorRepository).findByName("John Doe");
        assertNull(result);
    }

    @Test
    public void testGetAllGuarantors() {
        Guarantor simulatedGuarantor1 = new Guarantor();
        simulatedGuarantor1.setId(1);
        simulatedGuarantor1.setName("Guarantor 1");

        Guarantor simulatedGuarantor2 = new Guarantor();
        simulatedGuarantor2.setId(2);
        simulatedGuarantor2.setName("Guarantor 2");

        when(guarantorRepository.findAll()).thenReturn(Arrays.asList(simulatedGuarantor1, simulatedGuarantor2));

        List<GuarantorRS> result = guarantorService.getAllGuarantors();

        verify(guarantorRepository).findAll();

        assertEquals(2, result.size());

        GuarantorRS resultGuarantor1 = result.get(0);
        assertEquals(1, resultGuarantor1.getId().intValue());
        assertEquals("Guarantor 1", resultGuarantor1.getName());

        GuarantorRS resultGuarantor2 = result.get(1);
        assertEquals(2, resultGuarantor2.getId().intValue());
        assertEquals("Guarantor 2", resultGuarantor2.getName());
    }
}