package examplejava.repo;

import examplejava.model.actorModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface actorRepo extends CrudRepository<actorModel,Integer>{
}
