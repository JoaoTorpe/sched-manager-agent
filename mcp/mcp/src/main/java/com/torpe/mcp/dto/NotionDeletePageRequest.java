package com.torpe.mcp.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class NotionDeletePageRequest {

    @JsonProperty("in_trash")
    private Boolean inTrash;

    public Boolean getInTrash() {
        return inTrash;
    }

    public void setInTrash(Boolean inTrash) {
        this.inTrash = inTrash;
    }
}
