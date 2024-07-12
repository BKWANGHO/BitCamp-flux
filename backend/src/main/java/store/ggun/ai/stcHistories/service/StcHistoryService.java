package store.ggun.ai.stcHistories.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.ggun.ai.stcHistories.domain.StcHistoryModel;
import store.ggun.ai.stcHistories.repository.StcHistoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StcHistoryService {
    private final StcHistoryRepository stcHistorysRepository;

    public Flux<StcHistoryModel> getAllStcHistories() {
        return stcHistorysRepository.findAll();
    }


    public Mono<StcHistoryModel> getStcHistoryDetailById(String id) {
        return stcHistorysRepository.findById(id);
    }

    public Mono<StcHistoryModel> addStcHistory(StcHistoryModel stcHistorys) {
        return stcHistorysRepository.save(stcHistorys);
    }
    public Mono<StcHistoryModel> updateStcHistory(String id, StcHistoryModel stcHistorys) {
        return stcHistorysRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalStcHistorys -> {
                    if (optionalStcHistorys.isPresent()) {
                        stcHistorys.setStcHistoryId(id);
                        return stcHistorysRepository.save(stcHistorys);
                    }

                    return Mono.empty();
                });
    }

    public Mono<Void> deleteStcHistory(String id) {
        return stcHistorysRepository.deleteById(id);
    }
}
