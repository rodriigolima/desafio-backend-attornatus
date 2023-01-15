package br.com.desafio.attornatus;

import br.com.desafio.attornatus.model.Pessoa;
import br.com.desafio.attornatus.repository.PessoaRepository;
import br.com.desafio.attornatus.service.PessoaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PessoaServiceTest {
    
   
    @Autowired
    private JdbcTemplate jdbc;
    
    @Mock
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private PessoaService pessoaService;
    
    @Value("${sql.script.create.pessoa}")
    private String sqlAddPessoa;
    
    @Value("${sql.script.delete.pessoa}")
    private String sqlDeletePessoa;
    
    @BeforeEach
    public void setupDatabase() {
        jdbc.execute(sqlAddPessoa);
    }
    
    
    @Test
    public void createPessoaService(){
        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Rodrigo Lima");
        pessoa.setDataNascimento(LocalDate.parse("1993-12-21"));

        
        when(pessoaRepository.findByNomeContaining(pessoa.getNome())).thenReturn(null);
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        
        Pessoa _pessoa = pessoaService.create(pessoa);
        
        assertNotNull(_pessoa);
        assertEquals("Rodrigo Lima", _pessoa.getNome());
    }

    @SqlGroup({ @Sql(scripts = "/insertData.sql", config = @SqlConfig(commentPrefix = "`"))})
    @Test
    public void findAllPessoaServiceIfPessoaisNull() {
        List<Pessoa> pessoas = pessoaService.findAll(null);
        
        assertEquals(5, pessoas.size());
    }
    @SqlGroup({ @Sql(scripts = "/insertData.sql", config = @SqlConfig(commentPrefix = "`"))})
    @Test
    public void findAllPessoaServiceIfPessoaisNotNull() {
        List<Pessoa> pessoas = pessoaService.findAll("João Paulo");

        assertEquals(1, pessoas.size());
    }

    
    @Test
    public void updatePessoaService() {
       
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Rodrigo");
        pessoa.setDataNascimento(LocalDate.parse("1993-12-21"));

        pessoaService.update(1, pessoa);

        assertNotNull(pessoa);
        
        assertNotEquals("Rodrigo Lima", pessoa.getNome());
        
        assertEquals(LocalDate.parse("1993-12-21"), pessoa.getDataNascimento());
    }

    
    @Test
    public void findPessoaByIdService() {
        Pessoa pessoa = pessoaService.findById(1);
        
        assertEquals(1, pessoa.getId());
        assertNotEquals(2, pessoa.getId());
        assertFalse(pessoaRepository.findById(6L).isPresent());
    }
    
    
    
    @AfterEach
    public void setupAfterTransaction(){
        jdbc.execute(sqlDeletePessoa);
    }
}
