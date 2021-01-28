package logmaker.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "scheduled_date")
public class ScheduledDate {
    /**
     * 테이블 명세
     * id PK
     * lastScheduledDate 마지막 스케쥴 날짜
     * lastScheduledHour 마지막 스케쥴 시간
     * createdAt 등록일
     * updatedAt 최종업데이트일
     */
    public ScheduledDate() {}

    @Builder
    public ScheduledDate(String lastScheduledDate, Integer lastScheduledHour) {
        this.lastScheduledDate = lastScheduledDate;
        this.lastScheduledHour = lastScheduledHour;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String lastScheduledDate;

    @Column
    private Integer lastScheduledHour;
}
