package com.doodle.backend.services;


import com.doodle.backend.entities.Sandage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface SandageService {
    Sandage saveSandage(Sandage sandage);
    Sandage updateSandage(Sandage sandage);
    Sandage getSandage(Long id);
    List<Sandage> getSandages();
    Page<Sandage> getAllSandagesByPage(int page, int size);
    void deleteSandageById(Long id);
    void deleteAllSandages();

}
