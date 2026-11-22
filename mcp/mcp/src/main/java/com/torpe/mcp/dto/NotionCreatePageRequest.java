package com.torpe.mcp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class NotionCreatePageRequest {

    private Parent parent;
    private Properties properties;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Block> children;

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

    public List<Block> getChildren() {
        return children;
    }

    public void setChildren(List<Block> children) {
        this.children = children;
    }

    public static class Block {
        private String object = "block";
        private String type = "paragraph";
        private Paragraph paragraph;

        public Block() {
        }

        public Block(List<RichText> richText) {
            this.paragraph = new Paragraph(richText);
        }

        public String getObject() {
            return object;
        }

        public String getType() {
            return type;
        }

        public Paragraph getParagraph() {
            return paragraph;
        }

        public void setParagraph(Paragraph paragraph) {
            this.paragraph = paragraph;
        }
    }

    public static class Paragraph {
        @JsonProperty("rich_text")
        private List<RichText> richText;

        public Paragraph() {
        }

        public Paragraph(List<RichText> richText) {
            this.richText = richText;
        }

        public List<RichText> getRichText() {
            return richText;
        }

        public void setRichText(List<RichText> richText) {
            this.richText = richText;
        }
    }

    public static class RichText {
        private String type = "text";
        private TextContent text;

        public RichText() {
        }

        public RichText(String content) {
            this.text = new TextContent(content);
        }

        public String getType() {
            return type;
        }

        public TextContent getText() {
            return text;
        }

        public void setText(TextContent text) {
            this.text = text;
        }
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
        @JsonProperty("Status")
        private StatusProperty status;
        @JsonProperty("Tags")
        private CheckboxProperty tags;

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

        public StatusProperty getStatus() {
            return status;
        }

        public void setStatus(StatusProperty status) {
            this.status = status;
        }

        public CheckboxProperty getTags() {
            return tags;
        }

        public void setTags(CheckboxProperty tags) {
            this.tags = tags;
        }
    }

    public static class StatusProperty {
        private StatusValue status;

        public StatusProperty() {
        }

        public StatusProperty(String name) {
            this.status = new StatusValue(name);
        }

        public StatusValue getStatus() {
            return status;
        }

        public void setStatus(StatusValue status) {
            this.status = status;
        }
    }

    public static class StatusValue {
        private String name;

        public StatusValue() {
        }

        public StatusValue(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class CheckboxProperty {
        private boolean checkbox;

        public CheckboxProperty() {
        }

        public CheckboxProperty(boolean checkbox) {
            this.checkbox = checkbox;
        }

        public boolean isCheckbox() {
            return checkbox;
        }

        public void setCheckbox(boolean checkbox) {
            this.checkbox = checkbox;
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
