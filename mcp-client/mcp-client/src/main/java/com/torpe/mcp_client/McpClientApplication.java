package com.torpe.mcp_client;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class McpClientApplication implements  Runnable {
	@Autowired
	ToolCallbackProvider tools;

	public static void main(String[] args) {
		SpringApplication.run(McpClientApplication.class, args);
	}

	@Override
	public void run() {
		Arrays.stream(tools.getToolCallbacks()).forEach( t -> {
			System.out.println("Found tool:" + t.getToolDefinition());
		});
	}
}
