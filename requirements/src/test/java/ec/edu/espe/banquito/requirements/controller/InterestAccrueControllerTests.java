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

import ec.edu.espe.banquito.requirements.controller.DTO.InterestAccrueRS;
import ec.edu.espe.banquito.requirements.service.InterestAccrueService;

@WebMvcTest(InterestAccrueController.class)
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
public class InterestAccrueControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InterestAccrueService interestAccrueService;

    @Test
    public void InterestAccrualController_ObtainInterestAccrual_ReturnsInterestAccrualRS() throws Exception {
        List<InterestAccrueRS> expectedGuarantors = new ArrayList<>();
        expectedGuarantors.add(new InterestAccrueRS(null, new BigDecimal("0.12"), "SYMPLE", new BigDecimal("0.0"), "MONTHLY"));

        when(interestAccrueService.getAllInterestAccrue()).thenReturn(expectedGuarantors);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/requirements/interest")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].interestType", CoreMatchers.is("SYMPLE")));
    }
}
