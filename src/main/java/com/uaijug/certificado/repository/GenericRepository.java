package com.uaijug.certificado.repository;

import java.util.List;

public interface GenericRepository< T > {

    T create(T t);
    
    T update(T t);

    void delete(Object id);

    T find(Object id);
    
    List<T> findAll();  
    
}
