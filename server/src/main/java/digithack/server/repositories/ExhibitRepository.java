package digithack.server.repositories;

import digithack.server.entities.Exhibit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ExhibitRepository extends CrudRepository<Exhibit, Long> {
}
