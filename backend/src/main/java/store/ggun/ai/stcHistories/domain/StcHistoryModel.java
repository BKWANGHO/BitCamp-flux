package store.ggun.ai.stcHistories.domain;

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
@Document(collection = "stc_histories")
public class StcHistoryModel {

    @Id
    String stcHistoryId;
    String stockCode;
    String stock;
    String openPrice;
    String highPrice;
    String lowPrice;
    String closePrice;
    String adjClose;
    String volume;
    Date regDate;
    Date modDate;



}
