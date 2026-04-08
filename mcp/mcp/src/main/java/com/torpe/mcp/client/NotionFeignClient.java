package com.torpe.mcp.client;

import com.torpe.mcp.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "notion-api", url = "https://api.notion.com/v1")
public interface NotionFeignClient {

    /**
     * Consulta dados entre duas datas (on_or_after e on_or_before)
     *
     * @param sourceId ID da fonte de dados do Notion
     * @param authorization Bearer token
     * @param notionVersion Versão da API do Notion
     * @param request Corpo da requisição com o filtro de datas
     * @return Resposta da API do Notion
     */
    @PostMapping("/data_sources/{sourceId}/query")
    NotionQueryResponse queryBetweenDates(
            @PathVariable("sourceId") String sourceId,
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("Notion-Version") String notionVersion,
            @RequestBody NotionQueryRequest request
    );

    /**
     * Consulta dados a partir de uma data (on_or_after)
     *
     * @param sourceId ID da fonte de dados do Notion
     * @param authorization Bearer token
     * @param notionVersion Versão da API do Notion
     * @param request Corpo da requisição com o filtro de data
     * @return Resposta da API do Notion
     */
    @PostMapping("/data_sources/{sourceId}/query")
    NotionQueryResponse queryFromDate(
            @PathVariable("sourceId") String sourceId,
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("Notion-Version") String notionVersion,
            @RequestBody NotionQueryRequest request
    );

    /**
     * Cria uma nova página no Notion
     *
     * @param authorization Bearer token
     * @param notionVersion Versão da API do Notion
     * @param request Corpo da requisição com parent e properties da nova página
     */
    @PostMapping("/pages")
    void createPage(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("Notion-Version") String notionVersion,
            @RequestBody NotionCreatePageRequest request
    );


    @PatchMapping("/pages/{pageId}")
    void deletePage(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("Notion-Version") String notionVersion,
            @PathVariable("pageId") String pageId,
            @RequestBody NotionDeletePageRequest request
    );

    @PatchMapping("/pages/{pageId}")
    void updatePageStatus(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("Notion-Version") String notionVersion,
            @PathVariable("pageId") String pageId,
            @RequestBody NotionUpdatePageRequest request
    );

}
