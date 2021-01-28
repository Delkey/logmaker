package logmaker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "log_orderSales")
public class LogOrderSales {
    /**
     * 테이블 명세
     * id PK
     * memNo 회원고유번호
     * memId 회원아이디
     * birthYear 생년월일
     * sexFl 성별
     * orderYMD 매출통계 - 기준일(결제완료일, 환불완료일)
     * mallSno 상점 고유번호
     * kind 상태 - 주문, 환불
     * type 종류 - 상품, 배송비
     * scmNo 공급사 고유번호
     * purchaseNo 매입처코드
     * orderIP 주문 IP
     * orderNo 주문번호
     * relationSno 관련 고유번호 - 주문상품고유번호, 주문배송고유번호, 주문환불고유번호
     * orderHour 매출통계 - 기준 시간 *결제완료시간, 환불완료시간)
     * orderDevice 주문처리디바이스 - pc, 모바일, 수기등록
     * orderMemberFl 회원주문여부 - y회원, n비회원
     * orderTaxFl 과세주문여부 - y과세, n비과세
     * orderGender 성별여부 - male남, female여, etc미확인
     * orderAge 연령 - 10 ~ 70, 없으면 0
     * orderArea 배송지역
     * orderSettleKind 주문결제방법
     * goodsCnt 주문상품갯수
     * goodsPrice 주문판매금액
     * costPrice 주문매입금액
     * goodsDcPrice 주문할인금액
     * divisionUseDeposit 안분된 주문상품의 예치금 사용금액
     * divisionUseMileage 안분된 주문상품의 마일리지 사용금액
     * deliveryPrice 배송비금액
     * deliveryDcPrice 배송비할인금액
     * divisionDeliveryUseDeposit 안분된 주문배송비의 예치금 사용금액
     * divisionDeliveryUseMileage 안분된 주문배송비의 마일리지 사용금액
     * refundGoodsPrice 환불상품금액
     * refundDeliveryPrice 환불배송비금액
     * refundUseDeposit 환불된 예치금금액
     * refundUseMileage 환불된 마일리지금액
     * refundFeePrice 환불수수료
     * regDt 등록일
     * modDt 수정일
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private Integer memNo;

    @Column
    private String memId;

    @Column
    private Integer birthYear;

    @Column
    private String sexFl;

    @Column
    private String orderYMD;

    @Column
    private Integer mallSno;

    @Column
    private String kind;

    @Column
    private String type;

    @Column
    private String scmNo;

    @Column
    private String purchaseNo;

    @Column
    private String orderIP;

    @Column
    private String orderNo;

    @Column
    private String relationSno;

    @Column
    private Integer orderHour;

    @Column
    private String orderDevice;

    @Column
    private String orderMemberFl;

    @Column
    private String orderTaxFl;

    @Column
    private String orderGender;

    @Column
    private Integer orderAge;

    @Column
    private String orderArea;

    @Column
    private String orderSettleKind;

    @Column
    private Integer goodsCnt;

    @Column
    private Double goodsPrice;

    @Column
    private Double costPrice;

    @Column
    private Double goodsDcPrice;

    @Column
    private Double divisionUseDeposit;

    @Column
    private Double divisionUseMileage;

    @Column
    private Double deliveryPrice;

    @Column
    private Double deliveryDcPrice;

    @Column
    private Double divisionDeliveryUseDeposit;

    @Column
    private Double divisionDeliveryUseMileage;

    @Column
    private Double refundGoodsPrice;

    @Column
    private Double refundDeliveryPrice;

    @Column
    private Double refundUseDeposit;

    @Column
    private Double refundUseMileage;

    @Column
    private Double refundFeePrice;

    @Column
    private String regDt;

    @Column
    private String modDt;
}
