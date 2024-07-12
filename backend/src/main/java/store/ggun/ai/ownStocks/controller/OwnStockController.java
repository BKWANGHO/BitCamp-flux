package store.ggun.ai.ownStocks.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.ggun.ai.ownStocks.domain.OwnStockModel;
import store.ggun.ai.ownStocks.service.OwnStockService;

@Log4j2
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/ownStocks")
public class OwnStockController {


    private final OwnStockService ownStockService;


    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<OwnStockModel> getAllOwnStocks() {
        return ownStockService.getAllOwnStocks();
    }

    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<OwnStockModel> getOwnStockById(@PathVariable("ownStockId") String ownStockId) {
        return ownStockService.getOwnStockDetailById(ownStockId);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<OwnStockModel> createOwnStock(@RequestBody OwnStockModel ownStock) {
        return ownStockService.addOwnStock(new OwnStockModel(
                ownStock.getOwnStockId(),
                ownStock.getAccount(),
                ownStock.getOrdDt(),
                ownStock.getPdno(),
                ownStock.getPrdtName(),
                ownStock.getPdQty(),
                ownStock.getAvgPrvs(),
                ownStock.getTradeType(),
                ownStock.getRegDate(),
                ownStock.getModDate()
        ));
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<OwnStockModel> updateOwnStock(@PathVariable("id") String id, @RequestBody OwnStockModel ownStock) {
        return ownStockService.updateOwnStock(id, ownStock);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteOwnStock(@PathVariable("id") String id) {
        return ownStockService.deleteOwnStock(id);
    }



}
