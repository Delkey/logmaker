package logmaker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "log_sleep")
public class LogSleep {
    /**
     * 테이블 명세
     * id PK
     * memNo 회원고유번호
     * birthYear 생년월일
     * sexFl 성별
     * sleepDt 휴면전환일
     * entryDt 가입일
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
    private String sleepDt;

    @Column
    private String entryDt;
}
