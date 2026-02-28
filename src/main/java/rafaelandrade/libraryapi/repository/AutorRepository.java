package rafaelandrade.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rafaelandrade.libraryapi.model.Autor;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
}
