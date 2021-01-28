package logmaker.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import logmaker.entity.*;
import logmaker.lib.Requester;
import logmaker.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@Service
public class LogSaveService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private ObjectMapper objectMapper = new ObjectMapper();
    private final String startDate = "2019-01-01";
    private final Integer startHour = 0;

    @Value("${api.url}")
    private String API_URL;

    @Autowired
    private ScheduledDateRepository scheduledDateRepository;

    @Autowired
    private LogJoinRepository logJoinRepository;

    @Autowired
    private LogHackOutRepository logHackOutRepository;

    @Autowired
    private LogSleepRepository logSleepRepository;

    @Autowired
    private LogVisitRepository logVisitRepository;

    @Autowired
    private LogOrderSalesRepository logOrderSalesRepository;

    @Autowired
    private LogOrderGoodsRepository logOrderGoodsRepository;

    private ScheduledDate requestDate() {
        List<ScheduledDate> scheduledDates = scheduledDateRepository.findAll();

        if(scheduledDates.isEmpty()) {
            return ScheduledDate.builder().lastScheduledDate(startDate).lastScheduledHour(startHour).build();
        } else {
            ScheduledDate scheduledDate = scheduledDates.get(0);

            /*
            if(scheduledDate.getLastScheduledHour() == 23) {
                String[] nextDates = scheduledDate.getLastScheduledDate().split("-");
                Integer year = Integer.parseInt(nextDates[0]);
                Integer month = Integer.parseInt(nextDates[1]);
                Integer dayOfMonth = Integer.parseInt(nextDates[2]);
                String nextDate = LocalDate.of(year, month, dayOfMonth).plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                scheduledDate.setLastScheduledDate(nextDate);
                scheduledDate.setLastScheduledHour(0);
            } else {
                scheduledDate.setLastScheduledHour(scheduledDate.getLastScheduledHour() + 1);
            }
            */

            String[] nextDates = scheduledDate.getLastScheduledDate().split("-");
            Integer year = Integer.parseInt(nextDates[0]);
            Integer month = Integer.parseInt(nextDates[1]);
            Integer dayOfMonth = Integer.parseInt(nextDates[2]);
            String nextDate = LocalDate.of(year, month, dayOfMonth).plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            scheduledDate.setLastScheduledDate(nextDate);

            return scheduledDate;
        }
    }

    private HashMap<String, Object> requestLogData(ScheduledDate scheduledDate) {
        logger.debug("scheduledDate is " + scheduledDate.getLastScheduledDate() + " and " + scheduledDate.getLastScheduledHour() + " hour");

        String search = "?date=" + scheduledDate.getLastScheduledDate() + "&hour=" + scheduledDate.getLastScheduledHour();

        Requester requester = Requester.getInstance();
        HashMap<String, Object> requestData = null;

        try {
            requestData = objectMapper.readValue(requester.get(API_URL + "test" + search), new TypeReference<HashMap<String, Object>>(){});
        } catch(Exception e) {
            logger.debug("error messages is " + e.getMessage());
        }

        return requestData;
    }

    @Transactional
    private void saveLogs(HashMap<String, Object> requestData) {
        List<LogJoin> joinLogs = objectMapper.convertValue(requestData.get("join"), new TypeReference<List<LogJoin>>(){});
        List<LogHackOut> hackLogs = objectMapper.convertValue(requestData.get("hackOut"), new TypeReference<List<LogHackOut>>(){});
        List<LogSleep> sleepLogs = objectMapper.convertValue(requestData.get("sleep"), new TypeReference<List<LogSleep>>(){});
        List<LogVisit> visitLogs = objectMapper.convertValue(requestData.get("visit"), new TypeReference<List<LogVisit>>(){});
        List<LogOrderGoods> orderGoodsLogs = objectMapper.convertValue(requestData.get("orderGoods"), new TypeReference<List<LogOrderGoods>>(){});
        List<LogOrderSales> orderSalesLogs = objectMapper.convertValue(requestData.get("orderSales"), new TypeReference<List<LogOrderSales>>(){});

        if(!joinLogs.isEmpty()) {
            logJoinRepository.saveAll(joinLogs);
        }

        if(!hackLogs.isEmpty()) {
            logHackOutRepository.saveAll(hackLogs);
        }

        if(!sleepLogs.isEmpty()) {
            logSleepRepository.saveAll(sleepLogs);
        }

        // orderStatus f1: 결제시도(초기주문), f2: 고객결제중단, f3: 결제실패(PG 결제 완료 결제실패 처리)

        if(!visitLogs.isEmpty()) {
            /*
            visitLogs.stream().forEach(i -> {
                try {
                    URI uri = new URI(i.getVisitReferer());
                    if(uri.getHost().indexOf("dalbame.com") != -1) {
                        if(uri.getPath() != null && !uri.getPath().equals("") && !uri.getPath().equals("/") && !uri.getPath().equals("/main/index.php")) {
                            logger.debug("referer path is " + uri.getPath());
                        }
                    }
                } catch(Exception e) {

                }
            });
            */
            logVisitRepository.saveAll(visitLogs);
        }

        if(!orderGoodsLogs.isEmpty()) {
            logOrderGoodsRepository.saveAll(orderGoodsLogs);
        }

        if(!orderSalesLogs.isEmpty()) {
            logOrderSalesRepository.saveAll(orderSalesLogs);
        }

        logger.debug("join logs size is " + joinLogs.size());
        logger.debug("hackOut logs size is " + hackLogs.size());
        logger.debug("sleep logs size is " + sleepLogs.size());
        logger.debug("visit logs size is " + visitLogs.size());
        logger.debug("orderGoods logs size is " + orderGoodsLogs.size());
        logger.debug("orderSales logs size is " + orderSalesLogs.size());
    }

    private void updateScheduledDate(ScheduledDate scheduledDate) {
        scheduledDateRepository.save(scheduledDate);
    }

    @Transactional
    public void execute() {
        /**
         * 순서
         * 1. 요청날짜 및 시간설정
         * 2. API 데이터 요청
         * 3. 로그 데이터 저장
         * 4. 마지막 요청날짜 및 시간갱신
         */
        ScheduledDate scheduledDate = requestDate();
        requestLogData(scheduledDate);
//        HashMap<String, Object> requestData = requestLogData(scheduledDate);
//        saveLogs(requestData);
        updateScheduledDate(scheduledDate);
    }
}
