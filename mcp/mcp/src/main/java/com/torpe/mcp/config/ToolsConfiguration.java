package com.torpe.mcp.config;

import com.torpe.mcp.tools.SchedTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ToolsConfiguration {

    @Bean
    public ToolCallbackProvider getTools(SchedTools tools){
        return  MethodToolCallbackProvider.builder().toolObjects(tools).build();
    }

}
