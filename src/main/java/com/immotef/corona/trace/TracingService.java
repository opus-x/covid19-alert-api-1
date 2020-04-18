package com.immotef.corona.trace;

import com.immotef.corona.meets.MeetEvent;
import com.immotef.corona.meets.MeetEventsRepository;
import com.immotef.corona.users.User;
import com.immotef.corona.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class TracingService {

    private final MeetEventsRepository meetEventsRepository;
    private final UsersRepository usersRepository;

    private static final int daysCount = 15; //add one on top of the number
    private static final int depth = 1;

    @Autowired
    public TracingService(MeetEventsRepository meetEventsRepository,
                          UsersRepository usersRepository) {
        this.meetEventsRepository = meetEventsRepository;
        this.usersRepository = usersRepository;
    }

    // Non depth implementation will write depth impl after poc
    public List<TraceItem> trace(long userId) {

        Map<String, List<TraceItem>> meetingsForUsers =
                meetEventsRepository.findByUserIdAndEndTimeAfter(userId, LocalDateTime.now().minusDays(daysCount))
                .stream()
                .map(event -> {
                    Optional<User> user = usersRepository.findById(event.getSeenUserId());
                    return TraceItem.builder()
                            .seenUserId(user.map(User::getExternalId).orElse(""))
                            .lastSeen(event.getEndTime())
                            .duration(durationInMinutes(event.getCloseDistanceLastingTime()))
                            .distance(event.getCloseDistance())
                            .daysPassed(event.getDaysPassed())
                            .isInfected(user.map(it -> it.getReportId() != null).orElse(false))
                            .build();
                        }
                )
//                        .filter(event -> event.isInfected) //TODO maybe return only infected?
                        .collect(Collectors.groupingBy(TraceItem::getSeenUserId));

        List<TraceItem> result =  new ArrayList<>();
        for (List<TraceItem> list : meetingsForUsers.values()) {
            result.addAll(list);
        }

        return result;
//        return meetingsForUsers.values() //TODO implement in stream way
//                .stream()
//                .map(traceItems -> traceItems.stream().min(Comparator.comparing(TraceItem::getLastSeen)).get())
//                .sorted(Comparator.comparing(TraceItem::getLastSeen))
//                .collect(Collectors.toList());
    }

    private long durationInMinutes(Integer closeDistanceLastingTime) {
        return  (long)Math.ceil((double)closeDistanceLastingTime / 60);
    }

    public TraceResponse traceForIdentifier(long id) {

        List<TraceItem> traceItems = trace(id);
        int riskLevel = calculateRiskWithItems(traceItems);
        return new TraceResponse(traceItems,riskLevel);
    }

    //TODO prepare logic for risk calculation
    private int calculateRiskWithItems(List<TraceItem> items) {
        int calculateNumberOfInfected = calculateNumberOfInfected(items);

        return riskLevelWithNumber(calculateNumberOfInfected);
    }

    private int riskLevelWithNumber(int calculateNumberOfInfected) {
        int riskLevel = 0;
        if(calculateNumberOfInfected > 0){
            riskLevel = 50;//TODO mid level
        }
        return riskLevel;
    }

    private int calculateNumberOfInfected(List<TraceItem> items) {
        Set<String> infected =  new HashSet<String>();
        items.forEach((item)->{
            if(item.isInfected)
            {
                infected.add(item.seenUserId);
            }
        });
        return infected.size();
    }

    public StatusResponse statusForIdentifier(long userId) {
        StatusResponse statusResponse = new StatusResponse();

        User user = usersRepository.findById(userId).get();
        if(user != null) {
            statusResponse.reportedSelfInfection = user.getReportId() != null;
            statusResponse.reportedRecovered = user.getDateTimeRecovered() != null;
        }

        if(isAppVersionWithoutMeetingUpload()){
            String lastRequestedS ="2020-03-26 00:00";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime lastRequested = LocalDateTime.parse(lastRequestedS, formatter);
            statusResponse.metInfectedIds = usersRepository.findInfectedUsersReportedAfter(lastRequested).stream()
                    .map( n -> n.getExternalId() )
                    .collect(Collectors.toList());
            return statusResponse;
        }


        List<TraceItem> traceItems = trace(userId);
        int numberOfInfected = calculateNumberOfInfected(traceItems);

        statusResponse.riskLevel =  riskLevelWithNumber(numberOfInfected);
        statusResponse.items = traceItems;
        statusResponse.numberOfInfectedMet = numberOfInfected;
        return statusResponse;
    }

    private boolean isAppVersionWithoutMeetingUpload() {
        return false;
    }

    public StatusResponse statusWithoutMeetingUploadForIdentifier(long userId) {
        StatusResponse statusResponse = new StatusResponse();

        User user = usersRepository.findById(userId).get();
        if(user != null) {
            statusResponse.reportedSelfInfection = user.getReportId() != null;
            statusResponse.reportedRecovered = user.getDateTimeRecovered() != null;
        }

            String lastRequestedS ="2020-03-26 00:00";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime lastRequested = LocalDateTime.parse(lastRequestedS, formatter);
            statusResponse.metInfectedIds = usersRepository.findInfectedUsersReportedAfter(lastRequested).stream()
                    .map( n -> n.getExternalId() )
                    .collect(Collectors.toList());
            return statusResponse;
        }

}
