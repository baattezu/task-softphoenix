package org.softwarephoenix.testzadanie.services.implementations;

import lombok.AllArgsConstructor;
import org.softwarephoenix.testzadanie.entities.News;
import org.softwarephoenix.testzadanie.repos.NewsRepository;
import org.softwarephoenix.testzadanie.services.NewsService;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {

    private NewsRepository newsRepository;
    @Override
    public List<News> getNews() {
        return newsRepository.findAll();
    }
}
