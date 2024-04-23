package org.softwarephoenix.testzadanie.repos;

import org.softwarephoenix.testzadanie.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
