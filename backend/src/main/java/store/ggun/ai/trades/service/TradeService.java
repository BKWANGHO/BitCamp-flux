package store.ggun.ai.trades.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.ggun.ai.trades.domain.TradeModel;
import store.ggun.ai.trades.repository.TradeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TradeService {
    private final TradeRepository tradeRepository;

    public Flux<TradeModel> getAllTrades() {
        return tradeRepository.findAll();
    }


    public Mono<TradeModel> getTradeDetailById(String id) {
        return tradeRepository.findById(id);
    }
    public Mono<TradeModel> addTrade(TradeModel trade) {
        return tradeRepository.save(trade);
    }

    public Mono<TradeModel> updateTrade(String id, TradeModel trade) {
        return tradeRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalTrades -> {
                    if (optionalTrades.isPresent()) {
                        trade.setTradeId(id);
                        return tradeRepository.save(trade);
                    }

                    return Mono.empty();
                });
    }

    public Mono<Void> deleteTrade(String id) {
        return tradeRepository.deleteById(id);
    }
}
