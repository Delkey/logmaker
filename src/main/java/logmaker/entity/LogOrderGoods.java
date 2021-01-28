package logmaker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "log_orderGoods")
public class LogOrderGoods {
    /**
     * 테이블 명세
     * id PK
     * memNo 회원고유번호
     * memId 회원아이디
     * birthYear 생년월일
     * sexFl 성별
     * cateCd 카테고리코드
     * cateNm1 1차 카테고리
     * cateNm2 2차 카테고리
     * cateNm3 3차 카테고리
     * goodsNo 상품고유번호
     * goodsNm 상품명
     * paymentDt 매출통계 - 기준일(결제완료일)
     * mallSno 상점 고유번호
     * orderNo 주문번호
     * goodsCnt 주문상품갯수
     * goodsPrice 상품판매금액
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
    private String cateCd;

    @Column
    private String cateNm1;

    @Column
    private String cateNm2;

    @Column
    private String cateNm3;

    @Column
    private Integer goodsNo;

    @Column
    private String goodsNm;

    @Column
    private String paymentDt;

    @Column
    private String orderNo;

    @Column
    private Integer goodsCnt;

    @Column
    private Double goodsPrice;

    @Column
    private String regDt;

    @Column
    private String modDt;
}
