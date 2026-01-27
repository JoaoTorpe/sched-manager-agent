package com.torpe.mcp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.torpe.mcp.dto.NotionQueryResponse;
import com.torpe.mcp.service.SchedService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/notion")
public class NotionController {

    private final SchedService schedService;

    public NotionController(SchedService schedService) {
        this.schedService = schedService;
    }

    @GetMapping("/query/between-dates")
    public ResponseEntity<NotionQueryResponse> queryBetweenDates(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws JsonProcessingException {
        NotionQueryResponse response = schedService.queryBetweenDates(startDate, endDate);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/query/from-date")
    public ResponseEntity<NotionQueryResponse> queryFromDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        NotionQueryResponse response = schedService.queryForDate(date);
        return ResponseEntity.ok(response);
    }
}

