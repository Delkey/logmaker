package logmaker.repository;

import logmaker.entity.ScheduledDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduledDateRepository extends JpaRepository<ScheduledDate, Integer> {}
