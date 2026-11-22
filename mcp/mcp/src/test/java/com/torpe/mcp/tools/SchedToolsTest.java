package com.torpe.mcp.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.torpe.mcp.dto.NotionCreatePageRequest;
import com.torpe.mcp.service.SchedService;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
class SchedToolsTest {

    private final CapturingSchedService schedService = new CapturingSchedService();
    private final SchedTools schedTools = new SchedTools(schedService);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createTask_serializesDescriptionAsParagraphAndPreservesExistingProperties() throws Exception {
        schedTools.createTask("Estudar Spring Boot", "2026-11-23", "Estudar Spring Security, OAuth2 e JWT.");

        NotionCreatePageRequest request = capturedRequest();
        JsonNode json = objectMapper.valueToTree(request);

        assertEquals("Estudar Spring Boot", json.at("/properties/Nome/title/0/text/content").asText());
        assertEquals("2026-11-23", json.at("/properties/Data/date/start").asText());
        assertEquals("Not started", json.at("/properties/Status/status/name").asText());
        assertFalse(json.at("/properties/Tags/checkbox").asBoolean());
        assertEquals("block", json.at("/children/0/object").asText());
        assertEquals("paragraph", json.at("/children/0/type").asText());
        assertEquals("text", json.at("/children/0/paragraph/rich_text/0/type").asText());
        assertEquals("Estudar Spring Security, OAuth2 e JWT.",
                json.at("/children/0/paragraph/rich_text/0/text/content").asText());
    }

    @Test
    void createTask_omitsChildrenWhenDescriptionIsMissingOrBlank() throws Exception {
        schedTools.createTask("Sem descrição", "2026-11-23", null);
        JsonNode missingDescription = objectMapper.valueToTree(capturedRequest());
        assertFalse(missingDescription.has("children"));

        schedTools.createTask("Descrição vazia", "2026-11-23", "  ");
        JsonNode blankDescription = objectMapper.valueToTree(capturedRequest());
        assertFalse(blankDescription.has("children"));
    }

    @Test
    void createTask_splitsLongDescriptionIntoValidRichTextEntriesInOrder() throws Exception {
        String description = IntStream.range(0, 2001)
                .mapToObj(i -> i == 1999 ? "😀" : "x")
                .collect(Collectors.joining());

        schedTools.createTask("Descrição longa", "2026-11-23", description);

        JsonNode json = objectMapper.valueToTree(capturedRequest());
        JsonNode richText = json.at("/children/0/paragraph/rich_text");
        assertEquals(2, richText.size());
        assertTrue(richText.get(0).at("/text/content").asText().codePointCount(0,
                richText.get(0).at("/text/content").asText().length()) <= 2000);
        assertEquals(description,
                richText.findValues("content").stream().map(JsonNode::asText).collect(Collectors.joining()));
    }

    private NotionCreatePageRequest capturedRequest() {
        return schedService.requests.get(schedService.requests.size() - 1);
    }

    private static class CapturingSchedService extends SchedService {
        private final List<NotionCreatePageRequest> requests = new ArrayList<>();

        private CapturingSchedService() {
            super(null, null);
        }

        @Override
        public void createPage(NotionCreatePageRequest request) {
            requests.add(request);
        }
    }
}
