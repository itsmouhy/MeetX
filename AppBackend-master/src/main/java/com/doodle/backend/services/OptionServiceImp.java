package com.doodle.backend.services;

import com.doodle.backend.entities.Option;
import com.doodle.backend.entities.Sandage;
import com.doodle.backend.repositories.OptionRepository;
import com.doodle.backend.repositories.SandageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OptionServiceImp implements OptionService{


    @Autowired
    OptionRepository optionRepository;

    @Autowired
    SandageServiceImp sandageServiceImp;

    @Override
    public Option saveOption(Option option) {
        return optionRepository.save(option);
    }

    @Override
    public Option updateOption(Option option) {
        return optionRepository.save(option);
    }

    @Override
    public Option getOption(Long id) {
        return optionRepository.findById(id).get();
    }

    @Override
    public List<Option> getOptions() {
        return optionRepository.findAll();
    }

    @Override
    public Page<Option> getAllOptionsByPage(int page, int size) {
        return optionRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public void deleteOptionById(Long id) {
        optionRepository.deleteById(id);

    }

    @Override
    public void deleteAllOptions() {
        optionRepository.deleteAll();
    }




}
