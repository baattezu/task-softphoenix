package org.softwarephoenix.testzadanie.services;

import org.softwarephoenix.testzadanie.entities.Request;

import java.util.List;

public interface RequestService {
    void save(Request request);
    List<Request> getAll();
}
