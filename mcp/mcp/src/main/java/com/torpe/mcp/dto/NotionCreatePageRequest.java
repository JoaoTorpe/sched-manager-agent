package com.torpe.mcp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NotionCreatePageRequest {

    private Parent parent;
    private Properties properties;

    public NotionCreatePageRequest() {
    }

    public NotionCreatePageRequest(Parent parent, Properties properties) {
        this.parent = parent;
        this.properties = properties;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public static class Parent {
        private String type;
        @JsonProperty("data_source_id")
        private String dataSourceId;

        public Parent() {
        }

        public Parent(String type, String dataSourceId) {
            this.type = type;
            this.dataSourceId = dataSourceId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDataSourceId() {
            return dataSourceId;
        }

        public void setDataSourceId(String dataSourceId) {
            this.dataSourceId = dataSourceId;
        }
    }

    public static class Properties {
        @JsonProperty("Nome")
        private TitleProperty nome;
        @JsonProperty("Data")
        private DateProperty data;

        public Properties() {
        }

        public Properties(TitleProperty nome, DateProperty data) {
            this.nome = nome;
            this.data = data;
        }

        public TitleProperty getNome() {
            return nome;
        }

        public void setNome(TitleProperty nome) {
            this.nome = nome;
        }

        public DateProperty getData() {
            return data;
        }

        public void setData(DateProperty data) {
            this.data = data;
        }
    }

    public static class TitleProperty {
        private List<TitleContent> title;

        public TitleProperty() {
        }

        public TitleProperty(List<TitleContent> title) {
            this.title = title;
        }

        public List<TitleContent> getTitle() {
            return title;
        }

        public void setTitle(List<TitleContent> title) {
            this.title = title;
        }
    }

    public static class TitleContent {
        private TextContent text;

        public TitleContent() {
        }

        public TitleContent(TextContent text) {
            this.text = text;
        }

        public TextContent getText() {
            return text;
        }

        public void setText(TextContent text) {
            this.text = text;
        }
    }

    public static class TextContent {
        private String content;

        public TextContent() {
        }

        public TextContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class DateProperty {
        private DateValue date;

        public DateProperty() {
        }

        public DateProperty(DateValue date) {
            this.date = date;
        }

        public DateValue getDate() {
            return date;
        }

        public void setDate(DateValue date) {
            this.date = date;
        }
    }

    public static class DateValue {
        private String start;

        public DateValue() {
        }

        public DateValue(String start) {
            this.start = start;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }
    }
}
