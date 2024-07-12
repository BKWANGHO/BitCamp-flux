package store.ggun.ai.trades.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.ggun.ai.stcHistories.service.StcHistoryService;
import store.ggun.ai.trades.domain.TradeModel;
import store.ggun.ai.trades.service.TradeService;

@Log4j2
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/trades")
public class TradeController {

    private final TradeService tradeService;
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<TradeModel> getAllTrades() {
        return tradeService.getAllTrades();
    }

    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<TradeModel> getTradeById(@PathVariable("tradeId") String tradeId) {
        return tradeService.getTradeDetailById(tradeId);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TradeModel> createTrade(@RequestBody TradeModel trade) {
        return tradeService.addTrade(new TradeModel(
                trade.getTradeId(),
                trade.getAccount(),
                trade.getOrdDt(),
                trade.getOdno(),
                trade.getOrdDvsnName(),
                trade.getOrdDvsnCd(),
                trade.getSllBuyDvsnCd(),
                trade.getSllBuyDvsnCdName(),
                trade.getPdno(),
                trade.getPrdtName(),
                trade.getOrdTmd(),
                trade.getOrdQty(),
                trade.getTotCcldQty(),
                trade.getCcldPrvs(),
                trade.getTotCcldAmt(),
                trade.getTradeType(),
                trade.getPurchaseFee(),
                trade.getSellingFee(),
                trade.getPurchaseTax(),
                trade.getSellingTotal(),
                trade.getStandardFee(),
                trade.getRegDate(),
                trade.getModDate()
        ));
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<TradeModel> updateTrade(@PathVariable("id") String id, @RequestBody TradeModel trade) {
        return tradeService.updateTrade(id, trade);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTrade(@PathVariable("id") String id) {
        return tradeService.deleteTrade(id);
    }



}
