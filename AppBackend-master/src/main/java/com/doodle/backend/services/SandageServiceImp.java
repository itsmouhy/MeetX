package com.doodle.backend.services;

import com.doodle.backend.entities.Sandage;
import com.doodle.backend.repositories.SandageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SandageServiceImp implements SandageService{


    @Autowired
    SandageRepository sandageRepository;

    @Override
    public Sandage saveSandage(Sandage sandage) {
        return sandageRepository.save(sandage);
    }

    @Override
    public Sandage updateSandage(Sandage sandage) {
        return sandageRepository.save(sandage);
    }

    @Override
    public Sandage getSandage(Long id) {
        return sandageRepository.findById(id).get();
    }

    @Override
    public List<Sandage> getSandages() {
        return sandageRepository.findAll();
    }

    @Override
    public Page<Sandage> getAllSandagesByPage(int page, int size) {
        return sandageRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public void deleteSandageById(Long id) {
        sandageRepository.deleteById(id);

    }

    @Override
    public void deleteAllSandages() {
        sandageRepository.deleteAll();

    }
}
