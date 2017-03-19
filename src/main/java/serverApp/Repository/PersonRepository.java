package serverApp.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import serverApp.Model.Person;

public interface PersonRepository extends MongoRepository<Person, String> {

}
