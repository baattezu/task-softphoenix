package org.softwarephoenix.testzadanie.controllers;

import lombok.AllArgsConstructor;
import org.softwarephoenix.testzadanie.entities.News;
import org.softwarephoenix.testzadanie.services.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@AllArgsConstructor
public class NewsController {
    private NewsService newsService;

    @GetMapping
    public ResponseEntity<List<News>> getNews(){
        return new ResponseEntity<>(newsService.getNews(),HttpStatus.OK);
    }

}
