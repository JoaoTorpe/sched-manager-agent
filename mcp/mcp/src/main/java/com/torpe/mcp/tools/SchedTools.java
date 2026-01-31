package com.torpe.mcp.tools;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.torpe.mcp.dto.NotionQueryResponse;
import com.torpe.mcp.dto.TaskDto;
import com.torpe.mcp.service.SchedService;
import com.torpe.mcp.utils.JsonUtils;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
public class SchedTools {

    private final SchedService schedService;

    public SchedTools(SchedService schedService) {
        this.schedService = schedService;
    }

    @Tool(description = "Query Notion database for events between two dates (inclusive).")
    public List<TaskDto> queryBetweenDates(@ToolParam(description = "start date (YYYY-MM-DD)") LocalDate startDate, @ToolParam(description = "end date (YYYY-MM-DD)") LocalDate endDate) throws JsonProcessingException {
        NotionQueryResponse response = schedService.queryBetweenDates(startDate, endDate);
        return JsonUtils.notionResponseToTask(response);
    }

    @Tool(description = "Query Notion database for events on date.")
    public List<TaskDto> queryForDate(@ToolParam(description = "date (YYYY-MM-DD)") LocalDate date) {
        NotionQueryResponse response = schedService.queryForDate(date);
        return JsonUtils.notionResponseToTask(response);
    }
}

