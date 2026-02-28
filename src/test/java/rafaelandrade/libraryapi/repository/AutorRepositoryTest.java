package rafaelandrade.libraryapi.repository;

import rafaelandrade.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rafaelandrade.libraryapi.model.Genero;
import rafaelandrade.libraryapi.model.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("7ed4aa68-5d85-4ad7-99de-4f4874df3ea8");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()){

            Autor autorEncontrado =  possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));

            repository.save(autorEncontrado);

        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("7ed4aa68-5d85-4ad7-99de-4f4874df3ea8");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("abc082bf-1d23-4767-b3d9-9f322856ca6a");
        var maria = repository.findById(id).get();
        repository.delete(maria);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Americana");
        autor.setDataNascimento(LocalDate.of(1970, 8, 5));

        Livro livro = new Livro();
        livro.setIsbn("20847-84874");
        livro.setPreco(BigDecimal.valueOf(204));
        livro.setGenero(Genero.MISTERIO);
        livro.setTitulo("O roubo da casa assombrada");
        livro.setDataPublicacao(LocalDate.of(1999, 1, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("99999-84874");
        livro2.setPreco(BigDecimal.valueOf(650));
        livro2.setGenero(Genero.MISTERIO);
        livro2.setTitulo("O roubo da casa assombrada");
        livro2.setDataPublicacao(LocalDate.of(2000, 1, 2));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

//        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosAutor(){
        var id = UUID.fromString("819079b9-b03f-4f01-8f48-8cee8defc23a");
        var autor = repository.findById(id).get();

        // buscar os livros do autor

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }
}