package com.gzeic.smartcity01.bean;

public class TestWtBean {
    String name;
    String content;
    int id;

    public TestWtBean(String name, String content, int id) {
        this.name = name;
        this.content = content;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
