package com.example.todo_backend.application.controller;

import org.springframework.web.context.request.NativeWebRequest;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiUtil {
    public static void setExampleResponse(NativeWebRequest req, String contentType, String example) {
        try {
            HttpServletResponse res = req.getNativeResponse(HttpServletResponse.class);
            if (res != null) { 
                res.setCharacterEncoding("UTF-8");
                res.addHeader("Content-Type", contentType);
                res.getWriter().print(example);
            } else {
                System.err.println("HttpServletResponse is null. Cannot set example response.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
