package com.vsu;

public class MenuItem {
    private int id;
    private String text;
    private RunnableMethod method;

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public RunnableMethod getMethod() {
        return method;
    }

    public MenuItem(int id, String text, RunnableMethod method) {
        this.id = id;
        this.text = text;
        this.method = method;
    }
}
