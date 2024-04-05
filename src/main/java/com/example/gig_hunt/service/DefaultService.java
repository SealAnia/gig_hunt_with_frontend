package com.example.gig_hunt.service;

import com.example.gig_hunt.exception.NumberOfSymbolsDifferentFromRequiredException;

import java.io.IOException;
import java.util.List;

public interface DefaultService<T> {

    List<T> getAll();
    T readById(Long id);
    T createOrUpdate(T t) throws NumberOfSymbolsDifferentFromRequiredException, IOException;
    void delete(Long id);

}
