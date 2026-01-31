package com.torpe.mcp.utils;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.torpe.mcp.dto.NotionQueryResponse;
import com.torpe.mcp.dto.TaskDto;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    public static List<TaskDto> notionResponseToTask(NotionQueryResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(response.getResults());
        List<TaskDto> tasks = new ArrayList<>();


        for (JsonNode item : node) {
            JsonNode properties = item.get("properties");

            String title = properties.at("/Nome/title/0/plain_text").asText();
            String startDate = properties.at("/Data/date/start").asText();
            boolean isDone = properties.at("/Tags/checkbox").asBoolean();

            tasks.add(new TaskDto(startDate, isDone, title));
        }

        return tasks;
    }

}
