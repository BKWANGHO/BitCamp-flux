package store.ggun.ai.trades.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import store.ggun.ai.trades.domain.TradeModel;

@Repository
public interface TradeRepository extends ReactiveMongoRepository<TradeModel, String> {
}
