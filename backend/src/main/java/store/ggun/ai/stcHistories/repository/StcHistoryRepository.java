package store.ggun.ai.stcHistories.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import store.ggun.ai.stcHistories.domain.StcHistoryModel;

@Repository
public interface StcHistoryRepository extends ReactiveMongoRepository<StcHistoryModel, String> {
}
