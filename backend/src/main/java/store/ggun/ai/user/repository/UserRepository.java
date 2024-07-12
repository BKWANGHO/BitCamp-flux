package store.ggun.ai.user.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.ggun.ai.user.domain.UserModel;


@Repository
public interface UserRepository extends ReactiveMongoRepository<UserModel, String> {
    Flux<UserModel> findByLastName(String lastName);

    Mono<UserModel> findByEmail(String email);


}