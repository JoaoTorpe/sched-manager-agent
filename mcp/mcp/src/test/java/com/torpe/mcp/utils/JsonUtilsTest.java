package com.torpe.mcp.utils;

import com.torpe.mcp.dto.NotionQueryResponse;
import com.torpe.mcp.dto.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonUtilsTest {

    @Test
    void notionResponseToTask_mapsNotionFieldsToTaskDto() {
        NotionQueryResponse response = new NotionQueryResponse();
        NotionQueryResponse.Result result = new NotionQueryResponse.Result();
        result.setId("abc-123");
        result.setProperties(buildProperties("Tarefa para query teste", "2026-01-28", false));
        response.setResults(List.of(result));

        List<TaskDto> tasks = JsonUtils.notionResponseToTask(response);

        assertEquals(1, tasks.size());
        TaskDto task = tasks.get(0);
        assertEquals("Tarefa para query teste", task.getTitle());
        assertEquals("2026-01-28", task.getStartDate());
        assertFalse(task.isDone());
    }

    @Test
    void notionResponseToTask_returnsDefaultsWhenNestedPropertiesAreMissing() {
        NotionQueryResponse response = new NotionQueryResponse();
        NotionQueryResponse.Result result = new NotionQueryResponse.Result();
        result.setId("missing-fields");
        result.setProperties(new HashMap<>());
        response.setResults(List.of(result));

        List<TaskDto> tasks = JsonUtils.notionResponseToTask(response);

        assertEquals(1, tasks.size());
        TaskDto task = tasks.get(0);
        assertEquals("", task.getTitle());
        assertEquals("", task.getStartDate());
        assertFalse(task.isDone());
    }

    @Test
    void notionResponseToTask_mapsMultipleResultsInOrder() {
        NotionQueryResponse response = new NotionQueryResponse();

        NotionQueryResponse.Result first = new NotionQueryResponse.Result();
        first.setId("1");
        first.setProperties(buildProperties("Primeira", "2026-02-01", true));

        NotionQueryResponse.Result second = new NotionQueryResponse.Result();
        second.setId("2");
        second.setProperties(buildProperties("Segunda", "2026-02-02", false));

        response.setResults(List.of(first, second));

        List<TaskDto> tasks = JsonUtils.notionResponseToTask(response);

        assertEquals(2, tasks.size());

        assertEquals("Primeira", tasks.get(0).getTitle());
        assertEquals("2026-02-01", tasks.get(0).getStartDate());
        assertTrue(tasks.get(0).isDone());

        assertEquals("Segunda", tasks.get(1).getTitle());
        assertEquals("2026-02-02", tasks.get(1).getStartDate());
        assertFalse(tasks.get(1).isDone());
    }

    private Map<String, Object> buildProperties(String title, String startDate, boolean tagsChecked) {
        Map<String, Object> titleText = Map.of("plain_text", title);
        Map<String, Object> nome = Map.of("title", List.of(titleText));

        Map<String, Object> date = Map.of("start", startDate);
        Map<String, Object> data = Map.of("date", date);

        Map<String, Object> tags = Map.of("checkbox", tagsChecked);

        Map<String, Object> properties = new HashMap<>();
        properties.put("Nome", nome);
        properties.put("Data", data);
        properties.put("Tags", tags);
        return properties;
    }
}
