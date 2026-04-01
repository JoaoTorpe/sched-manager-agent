package com.torpe.mcp.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.torpe.mcp.client.NotionFeignClient;
import com.torpe.mcp.config.NotionProperties;
import com.torpe.mcp.dto.*;
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

    public void deleteTask(String taskId) {
        NotionDeletePageRequest request = new NotionDeletePageRequest();
        request.setInTrash(true);

        notionFeignClient.deletePage(
                formatBearer(notionProperties.getToken()),
                notionProperties.getVersion(),
                taskId,
                request
        );
    }


    public void createPage(NotionCreatePageRequest request) {
        NotionCreatePageRequest.Parent parent = new NotionCreatePageRequest.Parent();
        parent.setType("data_source_id");
        parent.setDataSourceId(notionProperties.getDataSourceId());
        request.setParent(parent);

        notionFeignClient.createPage(
                formatBearer(notionProperties.getToken()),
                notionProperties.getVersion(),
                request
        );
    }


    public void markTaskDone(String taskId, boolean done) {
        NotionUpdatePageRequest request = new NotionUpdatePageRequest();
        NotionUpdatePageRequest.Properties properties = new NotionUpdatePageRequest.Properties();
        properties.setTags(new NotionUpdatePageRequest.CheckboxProperty(done));
        request.setProperties(properties);

        notionFeignClient.updatePageStatus(
                formatBearer(notionProperties.getToken()),
                notionProperties.getVersion(),
                taskId,
                request
        );
    }


}
