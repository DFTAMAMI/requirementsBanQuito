package ec.edu.espe.banquito.requirements.controller;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ec.edu.espe.banquito.requirements.controller.DTO.AssetRS;
import ec.edu.espe.banquito.requirements.service.AssetService;

@WebMvcTest(AssetController.class)
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
public class AssetControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssetService assetService;

    @Test
    public void AssetController_ObtainAssets_ReturnsAssetsRS() throws Exception {
        List<AssetRS> expectedAssets = new ArrayList<>();
        expectedAssets.add(new AssetRS(null, new BigDecimal("7070.80"), "11-3460078", "GCU", "USD"));

        when(assetService.getAllAssets()).thenReturn(expectedAssets);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/requirements/assets")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].guarantorType", CoreMatchers.is("GCU")));
    }
}
