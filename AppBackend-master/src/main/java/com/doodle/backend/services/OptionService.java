package com.doodle.backend.services;


import com.doodle.backend.entities.Option;
import com.doodle.backend.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OptionService {


    Option saveOption(Option option);
    Option updateOption(Option option);
    Option getOption(Long id);
    List<Option> getOptions();
    Page<Option> getAllOptionsByPage(int page, int size);
    void deleteOptionById(Long id);
    void deleteAllOptions();

}
