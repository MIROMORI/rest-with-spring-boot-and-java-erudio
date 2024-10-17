package com.miromori.spring_app;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Greeting {
    private final long id;
        private final String content;
}
