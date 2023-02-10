package com.thesoftwarepartner.thesoftwarepartnertesttask.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseCrudService<Entity> {

    Page<Entity> getAll(Pageable pageable);

    Entity getById(String id);

    Entity save(Entity entity);

    Entity update(String id, Entity entity);

    void delete(String id);
}
