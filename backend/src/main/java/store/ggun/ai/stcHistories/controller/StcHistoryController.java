package store.ggun.ai.stcHistories.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.ggun.ai.stcHistories.domain.StcHistoryModel;
import store.ggun.ai.stcHistories.service.StcHistoryService;

@Log4j2
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/stcHistories")
public class StcHistoryController {
    private final StcHistoryService stcHistoryService;


    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<StcHistoryModel> getAllStcHistories() {
        return stcHistoryService.getAllStcHistories();
    }

    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<StcHistoryModel> getStcHistoryById(@PathVariable("stcHistoryId") String stcHistoryId) {
        return stcHistoryService.getStcHistoryDetailById(stcHistoryId);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<StcHistoryModel> createStcHistory(@RequestBody StcHistoryModel stcHistory) {
        return stcHistoryService.addStcHistory(new StcHistoryModel(
                stcHistory.getStcHistoryId(),
                stcHistory.getStockCode(),
                stcHistory.getStock(),
                stcHistory.getOpenPrice(),
                stcHistory.getHighPrice(),
                stcHistory.getLowPrice(),
                stcHistory.getClosePrice(),
                stcHistory.getAdjClose(),
                stcHistory.getVolume(),
                stcHistory.getRegDate(),
                stcHistory.getModDate()
        ));
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<StcHistoryModel> updateStcHistory(@PathVariable("id") String id, @RequestBody StcHistoryModel stcHistory) {
        return stcHistoryService.updateStcHistory(id, stcHistory);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteStcHistory(@PathVariable("id") String id) {
        return stcHistoryService.deleteStcHistory(id);
    }




}
