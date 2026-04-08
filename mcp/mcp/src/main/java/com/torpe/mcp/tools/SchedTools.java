package com.torpe.mcp.tools;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.torpe.mcp.dto.NotionCreatePageRequest;
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

    @Tool(description = "Delete a specific task")
    public String deleteTask(@ToolParam(description = "Task ID") String taskId) {
        schedService.deleteTask(taskId);
        return "Task with ID "+taskId+" has been deleted";
    }


    @Tool(description = "Create a new task/event in the Notion database.")
    public String createTask(
            @ToolParam(description = "task title/name") String taskName,
            @ToolParam(description = "task date in YYYY-MM-DD format") String date) {

        NotionCreatePageRequest request = new NotionCreatePageRequest();


        NotionCreatePageRequest.Properties properties = new NotionCreatePageRequest.Properties();

        NotionCreatePageRequest.TitleProperty nome = new NotionCreatePageRequest.TitleProperty();
        NotionCreatePageRequest.TitleContent titleContent = new NotionCreatePageRequest.TitleContent();
        NotionCreatePageRequest.TextContent textContent = new NotionCreatePageRequest.TextContent(taskName);
        titleContent.setText(textContent);
        nome.setTitle(List.of(titleContent));
        properties.setNome(nome);

        NotionCreatePageRequest.DateProperty data = new NotionCreatePageRequest.DateProperty();
        NotionCreatePageRequest.DateValue dateValue = new NotionCreatePageRequest.DateValue(date);
        data.setDate(dateValue);
        properties.setData(data);

        request.setProperties(properties);

        schedService.createPage(request);

        return "Task '" + taskName + "' created successfully for date " + date;
    }

    @Tool(description = "Set the task checkbox to true or false.")
    public String markTaskDone(
            @ToolParam(description = "Task ID") String taskId,
            @ToolParam(description = "checkbox value (true/false)") boolean done) {
        schedService.markTaskDone(taskId, done);
        return "Task with ID " + taskId + " checkbox set to " + done;
    }
}
