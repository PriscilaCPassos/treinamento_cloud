package br.com.treinamento.controller;

import br.com.treinamento.StartupApplicationTests;
import br.com.treinamento.dto.ClientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles("test")
public class ClientControllerTests extends StartupApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private ClientController controller;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testClientList() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/clients"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }


    @Test
    public void testGetClientById() throws Exception {
       String clientId = "65ef7ab9776279588c3177b2";

        this.mockMvc.perform( MockMvcRequestBuilders.get("/clients/{id}", clientId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
             // .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists());
    }




    @Test
    public void testCreateClient() throws Exception {
        this.mockMvc.perform( MockMvcRequestBuilders
                        .post("/clients")
                        .content(asJsonString(new ClientDTO(null, "Priscila", "02564897523", "priscila.passos@gmail.com", "9994545429")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }



    @Test
    public void testPutClient() throws Exception {
       // String clientId = "65ef7b883836e27f35eee099";

        this.mockMvc.perform( MockMvcRequestBuilders
                        .put("/clients/65ef7b883836e27f35eee099")
                        .content(asJsonString(new ClientDTO(null, "Maria", "02564857525", "maria.novaes@gmail.com", "9994545428")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }


    @Test
    public void testDeleteClient() throws Exception {
        String clientId =  "65f094382847844b678e2e93";

        this.mockMvc.perform( MockMvcRequestBuilders
                        .delete("/clients/" + clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").doesNotExist());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}