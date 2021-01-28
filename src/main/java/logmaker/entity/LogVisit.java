package logmaker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "log_visit")
public class LogVisit {
    /**
     * 테이블 명세
     * id PK
     * memNo 회원고유번호
     * memId 회원아이디
     * birthYear 생년월일
     * sexFl 성별
     * visitSiteKey 방문고유세션
     * visitIP 방문IP
     * mallSno 상점번호
     * visitReferer 방문referer
     * visitInflow 유입경로
     * visitDevice 방문 디바이스 (pc, mobile)
     * visitOS 방문OS
     * visitBrowser 방문browser
     * visitYear 방문년도
     * visitMonth 방문월
     * visitDay 방문일
     * visitHour 방문시간
     * visitPageView 방문페이지뷰
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
    private String visitNo;

    @Column
    private String visitSiteKey;

    @Column
    private String visitIP;

    @Column
    private Integer mallSno;

    @Column
    private String visitReferer;

    @Column
    private String visitInflow;

    @Column
    private String visitDevice;

    @Column
    private String visitOS;

    @Column
    private String visitBrowser;

    @Column
    private Integer visitYear;

    @Column
    private Integer visitMonth;

    @Column
    private Integer visitDay;

    @Column
    private Integer visitHour;

    @Column
    private Integer visitPageView;

    @Column
    private String regDt;

    @Column
    private String modDt;
}
