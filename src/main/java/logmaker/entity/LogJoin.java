package logmaker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "log_join")
public class LogJoin {
    /**
     * 테이블 명세
     * id PK
     * memNo 회원고유번호
     * birthYear 생년월일
     * sexFl 성별
     * entryDt 가입일
     * sleepFl 휴면회원 여부
     * sleepWakeDt 휴면해제일
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
    private String entryDt;
}
