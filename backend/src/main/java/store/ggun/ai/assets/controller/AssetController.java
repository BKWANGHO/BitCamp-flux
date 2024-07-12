package store.ggun.ai.assets.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.ggun.ai.assets.domain.AssetModel;
import store.ggun.ai.assets.service.AssetService;
import store.ggun.ai.common.domain.Messenger;

@Log4j2
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/assets")
public class AssetController {


    private final AssetService assetService;

   
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<AssetModel> getAllAssets() {
        return assetService.getAllAssets();
    }

    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<AssetModel> getAssetById(@PathVariable("assetId") String assetId) {
        return assetService.getAssetDetailById(assetId);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AssetModel> createAsset(@RequestBody AssetModel asset) {
        return assetService.addAsset(new AssetModel(
                asset.getAssetId(),
                asset.getUser(),
                asset.getTotalAsset(),
                asset.getStockAsset(),
                asset.getCashAsset(),
                asset.getLastUpdated(),
                asset.getRegDate(),
                asset.getModDate()
        ));
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<AssetModel> updateAsset(@PathVariable("id") String id, @RequestBody AssetModel asset) {
        return assetService.updateAsset(id, asset);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAsset(@PathVariable("id") String id) {
        return assetService.deleteAsset(id);
    }



}