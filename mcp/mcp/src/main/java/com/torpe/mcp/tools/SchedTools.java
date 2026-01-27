package com.torpe.mcp.tools;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.torpe.mcp.dto.NotionQueryResponse;
import com.torpe.mcp.service.SchedService;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class SchedTools {

    private final SchedService schedService;

    public SchedTools(SchedService schedService){
        this.schedService = schedService;
    }

    @McpTool(description = "Query Notion database for events between two dates (inclusive).")
    public NotionQueryResponse queryBetweenDates(@McpToolParam(description = "start date (YYYY-MM-DD)") LocalDate startDate, @McpToolParam(description = "end date (YYYY-MM-DD)") LocalDate endDate) throws JsonProcessingException {
      return  schedService.queryBetweenDates(startDate, endDate);
    }

    @McpTool(description = "Query Notion database for events on date.")
    public NotionQueryResponse queryForDate(@McpToolParam(description = "date (YYYY-MM-DD)") LocalDate date) {
      return schedService.queryForDate(date);
    }
}
