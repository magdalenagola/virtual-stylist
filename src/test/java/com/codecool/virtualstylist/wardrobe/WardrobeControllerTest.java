package com.codecool.virtualstylist.wardrobe;

import com.codecool.virtualstylist.VirtualStylistApplication;
import com.codecool.virtualstylist.user.UserForLoginDTO;
import com.codecool.virtualstylist.user.UserForRegistrationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = VirtualStylistApplication.class)
@ActiveProfiles("test")
class WardrobeControllerTest {
    String accessToken;
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        accessToken = obtainAccessToken("userFor@test.pl", "test");
    }

    @Test
    void shouldReturn201WhenValidPostInput() throws Exception {
        // Arrange
        ClothForCreationDTO clothForCreationDTO = arrangeClothForCreation();
        // Act Assert
        mockMvc.perform(post("/wardrobe")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clothForCreationDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturn200WithContentWhenGetAllRequested() throws Exception {
        // Act Assert
        mockMvc.perform(get("/wardrobe")
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn404WhenGetByIdWhichNotExistRequested() throws Exception {
        // Act Assert
        mockMvc.perform(get("/wardrobe/{id}", 0)
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isNotFound());
    }

    private ClothForCreationDTO arrangeClothForCreation() {
        ClothForCreationDTO clothForCreationDTO = new ClothForCreationDTO();
        clothForCreationDTO.setImageName("imageName");
        clothForCreationDTO.setClothType("BLOUSE");
        clothForCreationDTO.setColor("WHITE");
        clothForCreationDTO.setStyle("BOHO");
        clothForCreationDTO.setSize("S");
        clothForCreationDTO.setHasPattern(true);
        return clothForCreationDTO;
    }
    private String obtainAccessToken(String username, String password) throws Exception {

        UserForLoginDTO userForLoginDTO = new UserForLoginDTO();
        userForLoginDTO.setLogin(username);
        userForLoginDTO.setPassword(password);

        ResultActions result
                = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userForLoginDTO))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("token").toString();
    }
}