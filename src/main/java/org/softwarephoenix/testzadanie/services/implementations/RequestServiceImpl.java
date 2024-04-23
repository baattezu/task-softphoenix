package org.softwarephoenix.testzadanie.services.implementations;

import lombok.AllArgsConstructor;
import org.softwarephoenix.testzadanie.entities.Request;
import org.softwarephoenix.testzadanie.repos.RequestRepository;
import org.softwarephoenix.testzadanie.services.RequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RequestServiceImpl implements RequestService {
    private RequestRepository requestRepository;
    @Override
    public void save(Request request) {
        requestRepository.save(request);
    }

    @Override
    public List<Request> getAll() {
        return requestRepository.findAll();
    }
}
