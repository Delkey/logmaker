package logmaker.scheduled;

import logmaker.service.LogSaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HourScheduled {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
	private LogSaveService logSaveService;

	@Scheduled(fixedDelay = 5000)
	public void working() {
		logger.debug("HourScheduled > working() start...");
		logSaveService.execute();
		logger.debug("HourScheduled > working() end...");
    }
}
