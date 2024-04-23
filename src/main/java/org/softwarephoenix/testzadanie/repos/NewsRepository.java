package org.softwarephoenix.testzadanie.repos;

import org.softwarephoenix.testzadanie.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News,Long> {
}
