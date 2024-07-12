package store.ggun.ai.ownStocks.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "own_stocks")
public class OwnStockModel {

    @Id
    String ownStockId;
    String account;
    String ordDt;
    String pdno;
    String prdtName;
    String pdQty;
    String avgPrvs;
    String tradeType;
    Date regDate;
    Date modDate;


}
