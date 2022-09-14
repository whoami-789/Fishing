package com.example.fishing.models;

public class DocUrl {
    private String url;
    private String docName;

    public DocUrl(String url, String name) {
        this.url = url;
        this.docName = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "DocUrl{" +
                "url='" + url + '\'' +
                ", docName='" + docName + '\'' +
                '}';
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }
}
