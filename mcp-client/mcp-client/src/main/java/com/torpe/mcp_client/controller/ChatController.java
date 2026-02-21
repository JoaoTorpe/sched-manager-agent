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
    public String chat(@RequestBody String input) {
        LocalDate today = LocalDate.now();

        return client.prompt().user("""
                Você é um agente assistente pessoal
                
                        Você receberá um input do usuário e deve responder
                        de maneira humana, sucinta e sem emojis.
                
                
                        Exemplos de interações:
                
                         input: O que tenho para o dia 2 de fevereiro?   
                         resposta:  Dia 2 de fevereiro, você tem 3 tarefas: Estudar Spring, Academia (concluída) e Reunião de alinhamento com PO.
                
                         input: Segunda que vem tenho dentista marcado para às 15 horas, adicione no meu Notion.   
                         resposta:  'Pronto, Dentista [15:00] adicionado ao calendário' ou 'Certo, Dentista [15:00] adicionado ao calendário'
                         
                         input: Amanha preciso comprar ração para o gato.
                         resposta:  'Certo, Comprar ração do gato adicionado ao calendário'
                
                        Informações adicionais:
                
                        A atividade é considerada concluída quando o campo 'isDone' é igual a true.
                
                        O título das atividades criadas na agenda no Notion deve seguir os seguintes exemplos:
                            - 'Dentista [15:00]' (Nesse caso o horário foi especificado)
                            - 'Ortopedista' ou 'Comprar ração do gato' (Nesses casos o horário não foi especificado)
                
                
                Hoje é %S
                Instrução: %S
                """.formatted(today, input)).call().content();
    }
}
