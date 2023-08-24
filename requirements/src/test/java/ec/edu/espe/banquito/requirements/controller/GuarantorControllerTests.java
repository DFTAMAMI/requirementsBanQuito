package ec.edu.espe.banquito.requirements.controller;

import static org.mockito.Mockito.when;

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

import ec.edu.espe.banquito.requirements.controller.DTO.GuarantorRS;
import ec.edu.espe.banquito.requirements.service.GuarantorService;


@WebMvcTest(GuarantorController.class)
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
public class GuarantorControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuarantorService guarantorService;

    @Test
    public void GuarantorController_ObtainGuarantors_ReturnsGuarantorsRS() throws Exception {
        List<GuarantorRS> expectedGuarantors = new ArrayList<>();
        expectedGuarantors.add(new GuarantorRS(null, "91-1501003", "GCU", "Berke Hum"));

        when(guarantorService.getAllGuarantors()).thenReturn(expectedGuarantors);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/requirements/guarantor")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.is("Berke Hum")));
    }
}
