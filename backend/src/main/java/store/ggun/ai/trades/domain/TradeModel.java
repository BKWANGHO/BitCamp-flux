package store.ggun.ai.trades.domain;

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
@Document(collection = "trades")
public class TradeModel {

    @Id
    String tradeId;
    String account;
    String ordDt;
    String odno;
    String ordDvsnName;
    String ordDvsnCd;
    String sllBuyDvsnCd;
    String sllBuyDvsnCdName;
    String pdno;
    String prdtName;
    String ordTmd;
    String ordQty;
    String totCcldQty;
    String ccldPrvs;
    String totCcldAmt;
    String tradeType;
    String purchaseFee;
    String sellingFee;
    String purchaseTax ;
    String sellingTotal;
    String standardFee;
    Date regDate ;
    Date modDate ;

}
