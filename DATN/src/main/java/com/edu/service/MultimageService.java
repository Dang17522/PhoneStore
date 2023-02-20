package com.edu.service;

import java.util.List;
import java.util.Optional;

import com.edu.model.MultiImage;

public interface MultimageService {

    void deleteById(Long id);

    List<MultiImage> findAll();

    <S extends MultiImage> S save(S entity);
    List<MultiImage> findimage(Long productId);

    Optional<MultiImage> findById(Long id);

    MultiImage update(MultiImage multiImage);
}
