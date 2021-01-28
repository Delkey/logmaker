package logmaker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "log_hackout")
public class LogHackOut {
    /**
     * 테이블 명세
     * id PK
     * memNo 회원고유번호
     * birthYear 생년월일
     * sexFl 성별
     * reasonCd 탈퇴사유 고유코드
     * reason 탈퇴사유
     * reasonDesc 탈퇴사유 상세
     * hackDt 탈퇴일
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private Integer memNo;

    @Column
    private Integer birthYear;

    @Column
    private String sexFl;

    @Column
    private String reasonCd;

    @Column
    private String reason;

    @Column
    private String reasonDesc;

    @Column
    private String hackDt;
}
