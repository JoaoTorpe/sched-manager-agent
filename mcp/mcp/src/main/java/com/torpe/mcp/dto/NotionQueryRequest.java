package com.torpe.mcp.dto;


import java.util.Map;

public class NotionQueryRequest {

    private Filter filter;

    public NotionQueryRequest() {
    }

    public NotionQueryRequest(Filter filter) {
        this.filter = filter;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public static class Filter {
        private And[] and;


        public And[] getAnd() {
            return and;
        }

        public void setAnd(And[] and) {
            this.and = and;
        }

    }

    public static class And {
        private String property;
        private Map<String, String> date;

        public And() {
        }

        public And(String property, Map<String, String> date) {
            this.property = property;
            this.date = date;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public Map<String, String> getDate() {
            return  date;
        }

        public void setDate(Map<String, String> date) {
            this.date = date;
        }
    }

}

