package br.com.desafio.attornatus;

import br.com.desafio.attornatus.model.Endereco;
import br.com.desafio.attornatus.model.Pessoa;
import br.com.desafio.attornatus.repository.EnderecoRepository;
import br.com.desafio.attornatus.repository.PessoaRepository;
import br.com.desafio.attornatus.service.EnderecoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class EnderecoServiceTest {
    
    @Autowired
    private JdbcTemplate jdbc;
    
    @InjectMocks
    private EnderecoService enderecoService;
    
    @Mock
    private PessoaRepository pessoaRepository;
    
    @Mock
    private EnderecoRepository enderecoRepository;
    
    @Value("${sql.script.create.pessoa.endereco}")
    private String sqlAddEndereco;
    
    @Value("${sql.spript.delete.pessoa.endereco}")
    private String sqlDeleteEndereco;
    
    @Value("${sql.script.create.pessoa}")
    private String sqlAddPessoa;

    @Value("${sql.script.delete.pessoa}")
    private String sqlDeletePessoa;


    @BeforeEach
    public void setupDatabase() {
        jdbc.execute(sqlAddPessoa);
        jdbc.execute(sqlAddEndereco);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findEnderecoById() {
  
        long id = 1L;
        List<Endereco> expectedEnderecoList = mockEnderecoList();
        when(pessoaRepository.existsById(id)).thenReturn(true);
        when(enderecoRepository.findEnderecosByPessoasId(id)).thenReturn(expectedEnderecoList);

        
        List<Endereco> actualEnderecoList = enderecoService.findById(id);

        
        assertEquals(expectedEnderecoList, actualEnderecoList);
    }

    private List<Endereco> mockEnderecoList() {
        var endereco1 = new Endereco();
        var endereco2 = new Endereco();
        
        return Arrays.asList(endereco1, endereco2);
    }
    
    // o method create nao está encontrando o id do endereco
    //@Test
    public void createEnderecoService() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        Endereco endereco = new Endereco();
        endereco.setId(2L);
        pessoa.addEndereco(endereco);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);
        
        
        Endereco enderecoResponse = enderecoService.create(1L, endereco);
        
        long expectedId = endereco.getId();
        assertEquals(expectedId, enderecoResponse.getId().longValue());
        
    }
    
    
   
    
    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute(sqlDeletePessoa);
        jdbc.execute(sqlDeleteEndereco);
    }
}
