package com.torpe.mcp_client.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PromptServiceTest {

    private ResourceLoader resourceLoader;
    private Resource resource;
    private PromptService promptService;

    @BeforeEach
    void setUp() {
        resourceLoader = mock(ResourceLoader.class);
        resource = mock(Resource.class);
        promptService = new PromptService(resourceLoader);
    }

    @Test
    void shouldLoadPromptFromClasspath() throws IOException {
        String promptName = "chat";
        String expectedPath = "classpath:prompts/chat.md";
        String expectedContent = "You are a helpful assistant.";

        when(resourceLoader.getResource(expectedPath)).thenReturn(resource);
        when(resource.getInputStream()).thenReturn(
                new ByteArrayInputStream(expectedContent.getBytes(StandardCharsets.UTF_8))
        );

        String result = promptService.loadPrompt(promptName);

        assertEquals(expectedContent, result);
        verify(resourceLoader, times(1)).getResource(expectedPath);
        verify(resource, times(1)).getInputStream();
    }
}
