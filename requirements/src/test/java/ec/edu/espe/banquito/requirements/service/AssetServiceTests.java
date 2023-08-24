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

import ec.edu.espe.banquito.requirements.controller.DTO.AssetRS;
import ec.edu.espe.banquito.requirements.model.Asset;
import ec.edu.espe.banquito.requirements.repository.AssetRepository;

@RunWith(MockitoJUnitRunner.class)
public class AssetServiceTests {
    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private AssetService assetService;

    @Test
    public void testObtainExistingAsset() {
        Asset simulatedAsset = new Asset();
        simulatedAsset.setId(5);
        simulatedAsset.setAmount(new BigDecimal("7070.80"));

        when(assetRepository.findById(5)).thenReturn(Optional.of(simulatedAsset));

        AssetRS result = assetService.obtain(5);
        verify(assetRepository).findById(5);

        assertNotNull(result);
        assertEquals(5, result.getId().intValue());
        assertEquals(new BigDecimal("7070.80"), result.getAmount());
    }

    @Test
    public void testGetAllAssets() {
        Asset simulatedAsset1 = new Asset();
        simulatedAsset1.setId(1);
        simulatedAsset1.setAmount(new BigDecimal("123.45"));

        Asset simulatedAsset2 = new Asset();
        simulatedAsset2.setId(2);
        simulatedAsset2.setAmount(new BigDecimal("678.90"));

        List<Asset> simulatedAssets = new ArrayList<>();
        simulatedAssets.add(simulatedAsset1);
        simulatedAssets.add(simulatedAsset2);

        when(assetRepository.findAll()).thenReturn(simulatedAssets);

        List<AssetRS> result = assetService.getAllAssets();

        verify(assetRepository).findAll();

        assertEquals(2, result.size());

        AssetRS resultAsset1 = result.get(0);
        assertEquals(1, resultAsset1.getId().intValue());
        assertEquals(new BigDecimal("123.45"), resultAsset1.getAmount());

        AssetRS resultAsset2 = result.get(1);
        assertEquals(2, resultAsset2.getId().intValue());
        assertEquals(new BigDecimal("678.90"), resultAsset2.getAmount());
    }

    @Test
    public void testObtainNonExistingAsset() {
        when(assetRepository.findById(2)).thenReturn(Optional.empty());

        AssetRS result = assetService.obtain(2);

        verify(assetRepository).findById(2);

        assertNull(result);
    }

}
