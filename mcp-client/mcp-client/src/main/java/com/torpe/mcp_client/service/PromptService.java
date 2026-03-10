package com.torpe.mcp_client.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PromptService {

    private final ResourceLoader resourceLoader;

    public PromptService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String loadPrompt(String name) throws IOException {
        Resource resource = resourceLoader.getResource(
                "classpath:prompts/" + name + ".md"
        );

        return new String(resource.getInputStream().readAllBytes());
    }
}
