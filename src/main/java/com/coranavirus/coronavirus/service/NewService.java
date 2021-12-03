package com.coranavirus.coronavirus.service;

import com.coranavirus.coronavirus.DTO.news.NewResponse;

import java.util.List;

public interface NewService {
    boolean isExistNewByNewId(String newId);
    List<NewResponse> getAllNews();
}
