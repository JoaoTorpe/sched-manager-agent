package com.torpe.mcp.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.torpe.mcp.client.NotionFeignClient;
import com.torpe.mcp.config.NotionProperties;
import com.torpe.mcp.dto.NotionQueryRequest;
import com.torpe.mcp.dto.NotionQueryResponse;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


import static com.torpe.mcp.utils.RequestUtils.*;

@Service
public class SchedService {

    private final NotionFeignClient notionFeignClient;
    private final NotionProperties notionProperties;

    public SchedService(NotionFeignClient notionFeignClient, NotionProperties notionProperties) {
        this.notionFeignClient = notionFeignClient;
        this.notionProperties = notionProperties;
    }

    public NotionQueryResponse queryBetweenDates(LocalDate startDate, LocalDate endDate) throws JsonProcessingException {
        NotionQueryRequest request = buildBetweenDatesRequest(startDate, endDate);

        return notionFeignClient.queryBetweenDates(
                notionProperties.getDataSourceId(),
                formatBearer(notionProperties.getToken()),
                notionProperties.getVersion(),
                request
        );
    }


    public NotionQueryResponse queryForDate(LocalDate date) {
        NotionQueryRequest request = buildFromDateRequest(date);

        return notionFeignClient.queryFromDate(
                notionProperties.getDataSourceId(),
                formatBearer(notionProperties.getToken()),
                notionProperties.getVersion(),
                request
        );
    }


}

