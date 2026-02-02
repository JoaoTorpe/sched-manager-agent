package com.torpe.mcp_client.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient client;

    public ChatController(ChatClient client) {
        this.client = client;
    }

    @PostMapping
    public String chat(@RequestBody String question) {
        LocalDate today = LocalDate.now();

        return client.prompt()
                .user("""
                        Voce é um agente assistente pessoal, responda a pergunta 
                        de maneira humana, sucinta e sem mojis.
                        
                        Exemplo de resposta: No dia 2 de fevereiro, você tem 3 tarefas: Estudar Spring , Academia (concluída) e Reunião.
                        
                        Hoje é %S
                        Pergunta: %S
                        """.formatted(today, question))
                .call()
                .content();
    }
}
