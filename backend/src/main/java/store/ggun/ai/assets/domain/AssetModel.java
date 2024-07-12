package store.ggun.ai.assets.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import store.ggun.ai.user.domain.RoleModel;

import java.util.Date;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "assets")
public class AssetModel {

    @Id String assetId;
    String user;
    String totalAsset;
    String stockAsset;
    String cashAsset;
    String lastUpdated;
    Date regDate;
    Date modDate;





}