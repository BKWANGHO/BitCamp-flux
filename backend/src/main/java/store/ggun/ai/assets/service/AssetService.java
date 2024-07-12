package store.ggun.ai.assets.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.ggun.ai.assets.domain.AssetModel;
import store.ggun.ai.assets.repository.AssetRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssetService {


    private final AssetRepository assetsRepository;

    public Flux<AssetModel> getAllAssets() {
        return assetsRepository.findAll();
    }


    public Mono<AssetModel> getAssetDetailById(String id) {
        return assetsRepository.findById(id);
    }

    public Mono<AssetModel> addAsset(AssetModel assets) {
        return assetsRepository.save(assets);
    }

    public Mono<AssetModel> updateAsset(String id, AssetModel assets) {
        return assetsRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalAssets -> {
                    if (optionalAssets.isPresent()) {
                        assets.setAssetId(id);
                        return assetsRepository.save(assets);
                    }

                    return Mono.empty();
                });
    }

    public Mono<Void> deleteAsset(String id) {
        return assetsRepository.deleteById(id);
    }
}
