package com.trashclash.trashclash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trashclash.trashclash.entities.CustomBase;
import com.trashclash.trashclash.repository.BaseTypeRepository;
import com.trashclash.trashclash.repository.CustomBaseRepository;
import com.trashclash.trashclash.repository.ImageRepository;
import com.trashclash.trashclash.repository.TownHallRepository;

@Service
public class CustomBaseService {

    @Autowired
    private CustomBaseRepository customBaseRepository;
    @Autowired
    private TownHallRepository townHallRepository;
    @Autowired
    private BaseTypeRepository baseTypeRepository;
    @Autowired
    private ImageRepository imageRepository;

    public CustomBase saveCustomBase(CustomBase customBase) {
        return customBaseRepository.save(customBase);
    }

}
