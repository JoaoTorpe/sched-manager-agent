package com.torpe.mcp.utils;

import com.torpe.mcp.dto.NotionQueryRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class RequestUtils {


    public static NotionQueryRequest buildBetweenDatesRequest(LocalDate startDate, LocalDate endDate) {
        String startDateStr = startDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String endDateStr = endDate.format(DateTimeFormatter.ISO_LOCAL_DATE);

        NotionQueryRequest.And[] conditions = new NotionQueryRequest.And[2];

        conditions[0] = new NotionQueryRequest.And(
                "Data",
                new HashMap<>(Map.of("on_or_after",startDateStr))
        );

        conditions[1] = new NotionQueryRequest.And(
                "Data",
                new HashMap<>(Map.of("on_or_before",endDateStr))
        );

        NotionQueryRequest.Filter filter = new NotionQueryRequest.Filter();
        filter.setAnd(conditions);

        return new NotionQueryRequest(filter);
    }


    public static NotionQueryRequest buildFromDateRequest(LocalDate date) {
        String dateStr = date.format(DateTimeFormatter.ISO_LOCAL_DATE);

        NotionQueryRequest.And[] conditions = new NotionQueryRequest.And[2];

        conditions[0] = new NotionQueryRequest.And(
                "Data",
                new HashMap<>(Map.of("on_or_after",dateStr))
        );

        conditions[1] = new NotionQueryRequest.And(
                "Data",
                new HashMap<>(Map.of("on_or_before",dateStr))
        );

        NotionQueryRequest.Filter filter = new NotionQueryRequest.Filter();
        filter.setAnd(conditions);

        return new NotionQueryRequest(filter);
    }

    public static String formatBearer(String token){
        return "Bearer "+token;
    }
}
