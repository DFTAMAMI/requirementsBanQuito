package ec.edu.espe.banquito.requirements.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ec.edu.espe.banquito.requirements.controller.DTO.InterestAccrueRS;
import ec.edu.espe.banquito.requirements.model.InterestAccrue;
import ec.edu.espe.banquito.requirements.repository.InterestAccrueRepository;

@RunWith(MockitoJUnitRunner.class)
public class InterestAccrueTests {
    @Mock
    private InterestAccrueRepository interestRepository;

    @InjectMocks
    private InterestAccrueService interestService;

    @Test
    public void testObtainExistingAsset() {
        InterestAccrue simulatedInterest = new InterestAccrue();
        simulatedInterest.setId(5);
        simulatedInterest.setInterestRate(new BigDecimal("0.80"));

        when(interestRepository.findById(5)).thenReturn(Optional.of(simulatedInterest));

        InterestAccrueRS result = interestService.obtain(5);
        verify(interestRepository).findById(5);

        assertNotNull(result);
        assertEquals(5, result.getId().intValue());
        assertEquals(new BigDecimal("0.80"), result.getInterestRate());
    }

    @Test
    public void testGetAllAssets() {
        InterestAccrue simulatedInterest1 = new InterestAccrue();
        simulatedInterest1.setId(1);
        simulatedInterest1.setInterestRate(new BigDecimal("0.45"));

        InterestAccrue simulatedInterest2 = new InterestAccrue();
        simulatedInterest2.setId(2);
        simulatedInterest2.setInterestRate(new BigDecimal("0.90"));

        List<InterestAccrue> simulatedInterets = new ArrayList<>();
        simulatedInterets.add(simulatedInterest1);
        simulatedInterets.add(simulatedInterest2);

        when(interestRepository.findAll()).thenReturn(simulatedInterets);

        List<InterestAccrueRS> result = interestService.getAllInterestAccrue();

        verify(interestRepository).findAll();

        assertEquals(2, result.size());

        InterestAccrueRS resultInterest1 = result.get(0);
        assertEquals(1, resultInterest1.getId().intValue());
        assertEquals(new BigDecimal("0.45"), resultInterest1.getInterestRate());

        InterestAccrueRS resultInterest2 = result.get(1);
        assertEquals(2, resultInterest2.getId().intValue());
        assertEquals(new BigDecimal("0.90"), resultInterest2.getInterestRate());
    }

    @Test
    public void testObtainNonExistingAsset() {
        when(interestRepository.findById(2)).thenReturn(Optional.empty());

        InterestAccrueRS result = interestService.obtain(2);

        verify(interestRepository).findById(2);

        assertNull(result);
    }
}
