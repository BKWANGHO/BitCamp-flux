package store.ggun.ai.assets.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import store.ggun.ai.assets.domain.AssetModel;

@Repository
public interface AssetRepository extends ReactiveMongoRepository<AssetModel, String> {
}
