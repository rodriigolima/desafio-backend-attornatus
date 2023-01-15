package br.com.desafio.attornatus;

import br.com.desafio.attornatus.controller.PessoaController;
import br.com.desafio.attornatus.model.Endereco;
import br.com.desafio.attornatus.model.Pessoa;
import br.com.desafio.attornatus.repository.PessoaRepository;
import br.com.desafio.attornatus.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PessoaControllerTest {
    private static  MockHttpServletRequest request;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Mock
    PessoaService pessoaService;
    
    @Autowired
    private JdbcTemplate jdbc;
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private PessoaController pessoaController;
    
    private MockMvc mockMvc;
    
    @Autowired
    private Pessoa pessoa;
    
    @Autowired
    private Endereco endereco;
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Value("${sql.script.create.pessoa}")
    private String sqlAddPessoa;

    @Value("${sql.script.delete.pessoa}")
    private String sqlDeletePessoa;
    
    public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;
    
    @BeforeAll
    public static void setup() {
        request = new MockHttpServletRequest();

        request.setParameter("nome", "Rodrigo Lima");
        request.setParameter("data_nascimento", "1993-12-21");
    }
    
    @BeforeEach
    public void setupDatabase() {
        mockMvc = MockMvcBuilders.standaloneSetup(pessoaController).build();
        
        jdbc.execute(sqlAddPessoa);
    }
    
    @Test
    @SqlGroup({ @Sql(scripts = "/insertData.sql", config = @SqlConfig(commentPrefix = "`"))})
    public void findAllPessoaHttpRequest() throws Exception{
 
 
        pessoa.setNome("Rodrigo Lima");
        pessoa.setDataNascimento(LocalDate.parse("1993-12-21"));

        entityManager.persist(pessoa);
        entityManager.flush();
        

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pessoas")).andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(6)));
    }

    @Test
    @SqlGroup({ @Sql(scripts = "/insertData.sql", config = @SqlConfig(commentPrefix = "`"))})
    public void findPessoaByIdHttpRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/pessoas/{id}", 2))
                .andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8));
    }

    @Test
    public void createPessoaHttpRequest() throws Exception {
        
        pessoa.setNome("Rodrigo");
        pessoa.setDataNascimento(LocalDate.parse("1993-12-21"));

        

        mockMvc.perform(post("/api/pessoas").contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(pessoa)).accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());

        Pessoa verifyPessoa = pessoaRepository.findByNome("Rodrigo");

        assertNotNull(verifyPessoa, "Product Should be valid.");
    }

    @Test
    public void updatePessoaHttpRequest() throws Exception {

        pessoa.setId(1L);
        pessoa.setNome("Rodrigo");
        pessoa.setDataNascimento(LocalDate.parse("1993-12-21"));



        Map<String, Object> params = new HashMap<>();
        params.put("name", "Rodrigo Limaaa");
        params.put("dataNascimento", "1993-12-21");


        String requestBody = objectMapper.writeValueAsString(params);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/pessoas/{id}",
                        1).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(requestBody)).andExpect(status().isOk());

    }

    

    @AfterEach
    public void setupAferTransaction() {
        jdbc.execute(sqlDeletePessoa);
    }
}
