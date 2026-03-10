package com.torpe.mcp_client.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class NotificationService {

    private final ChatClient client;

    public NotificationService(ChatClient client) {
        this.client = client;
    }

    @Scheduled(cron = "0 6 * * 1")
    public void MondayNotification() {
        LocalDate today = LocalDate.now();

        String result = client.prompt().user("""
        Você é um agente assistente pessoal.

        Executará a função de gerar um relatório da semana que está se iniciando, indicando as atividades que devem ser executadas.
        Você deve responder de maneira natural, sucinta e sem emojis.

        Exemplo:
        Esta semana você tem 3 tarefas planejadas: estudar Spring na terça-feira, dentista na quinta-feira às 15 horas, reunião de planejamento na quarta-feira.

        Hoje é segunda-feira %s.
        Use as ferramentas às quais você tem acesso.
        """.formatted(today)).call().content();

        System.out.println("Week start notification: " + result);
    }

    @Scheduled(cron = "0 19 * * 5")
    public void FridayNotification() {
        LocalDate today = LocalDate.now();

        String result = client.prompt().user("""
        Você é um agente assistente pessoal.

        Executará a função de gerar um relatório da semana que se passou.
        Você deve responder de maneira natural, sucinta e sem emojis.

        Exemplos:
         - Esta semana você terminou a maioria de suas atividades, porém a atividade "Reunião de alinhamento com o PO" não foi finalizada.
         - Esta semana você não teve nenhuma atividade.
         - Todas as atividades desta semana foram finalizadas.

        Hoje é sexta-feira %s.
        Use as ferramentas às quais você tem acesso.
        """.formatted(today)).call().content();

        System.out.println("Friday notification: " + result);
    }

}
