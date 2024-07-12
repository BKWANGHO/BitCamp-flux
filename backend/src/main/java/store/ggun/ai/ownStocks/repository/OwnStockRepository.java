package store.ggun.ai.ownStocks.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import store.ggun.ai.ownStocks.domain.OwnStockModel;

@Repository
public interface OwnStockRepository extends ReactiveMongoRepository<OwnStockModel, String> {
}
