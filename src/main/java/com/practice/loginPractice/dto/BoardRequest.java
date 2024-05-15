package com.practice.loginPractice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequest {
    private String title;
    private String content;

    public BoardRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
    @Override
    public String toString() {
        return "Form{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
