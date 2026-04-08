package com.torpe.mcp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotionUpdatePageRequest {

    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public static class Properties {
        @JsonProperty("Tags")
        private CheckboxProperty tags;

        public CheckboxProperty getTags() {
            return tags;
        }

        public void setTags(CheckboxProperty tags) {
            this.tags = tags;
        }
    }

    public static class CheckboxProperty {
        private Boolean checkbox;

        public CheckboxProperty() {
        }

        public CheckboxProperty(Boolean checkbox) {
            this.checkbox = checkbox;
        }

        public Boolean getCheckbox() {
            return checkbox;
        }

        public void setCheckbox(Boolean checkbox) {
            this.checkbox = checkbox;
        }
    }
}

