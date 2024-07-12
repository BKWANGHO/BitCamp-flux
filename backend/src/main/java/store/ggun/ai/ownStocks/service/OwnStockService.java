package store.ggun.ai.ownStocks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.ggun.ai.ownStocks.domain.OwnStockModel;
import store.ggun.ai.ownStocks.repository.OwnStockRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnStockService {
    private final OwnStockRepository ownStocksRepository;

    public Flux<OwnStockModel> getAllOwnStocks() {
        return ownStocksRepository.findAll();
    }


    public Mono<OwnStockModel> getOwnStockDetailById(String id) {
        return ownStocksRepository.findById(id);
    }

    public Mono<OwnStockModel> addOwnStock(OwnStockModel ownStocks) {
        return ownStocksRepository.save(ownStocks);
    }
    public Mono<OwnStockModel> updateOwnStock(String id, OwnStockModel ownStocks) {
        return ownStocksRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalOwnStock -> {
                    if (optionalOwnStock.isPresent()) {
                        ownStocks.setOwnStockId(id);
                        return ownStocksRepository.save(ownStocks);
                    }

                    return Mono.empty();
                });
    }

    public Mono<Void> deleteOwnStock(String id) {
        return ownStocksRepository.deleteById(id);
    }
}
