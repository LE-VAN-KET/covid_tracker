package com.coranavirus.coronavirus.controller;

import com.coranavirus.coronavirus.DTO.news.NewResponse;
import com.coranavirus.coronavirus.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewController {
    @Autowired
    private NewService newService;

    @GetMapping("/news-covid-19")
    public List<NewResponse> getAllNews() {
        return newService.getAllNews();
    }
}
