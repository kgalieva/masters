package repository;

import model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface UserRepository extends MongoRepository<User, BigInteger> {
}
