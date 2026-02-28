package rafaelandrade.libraryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rafaelandrade.libraryapi.model.Autor;
import rafaelandrade.libraryapi.repository.AutorRepository;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		var context = SpringApplication.run(Application.class, args);

		AutorRepository repository = context.getBean(AutorRepository.class);

		exemploSalvarRegistro(repository);
	}

	public static void exemploSalvarRegistro(AutorRepository autorRepository){
		Autor autor = new Autor();
		autor.setNome("José");
		autor.setNacionalidade("Brasil");
		autor.setDataNascimento(LocalDate.of(1950, 3, 3));

		var autorSalvo = autorRepository.save(autor);
	}
}
