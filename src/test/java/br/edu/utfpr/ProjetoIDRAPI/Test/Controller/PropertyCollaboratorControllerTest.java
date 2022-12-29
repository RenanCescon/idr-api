package br.edu.utfpr.ProjetoIDRAPI.Test.Controller;

import br.edu.utfpr.ProjetoIDRAPI.model.Property;
import br.edu.utfpr.ProjetoIDRAPI.model.PropertyCollaborator;
import br.edu.utfpr.ProjetoIDRAPI.model.User;
import br.edu.utfpr.ProjetoIDRAPI.repository.PropertyCollaboratorRepository;
import br.edu.utfpr.ProjetoIDRAPI.service.PropertyCollaboratorService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PropertyCollaboratorControllerTest {
    private static final String API = "/propertyCollaborators";

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    PropertyCollaboratorRepository collaboratorRepository;

    @BeforeEach()
    private void cleanup() {
        collaboratorRepository.deleteAll();
        testRestTemplate.getRestTemplate().getInterceptors().clear();
    }

}
