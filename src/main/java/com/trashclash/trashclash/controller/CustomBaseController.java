package com.trashclash.trashclash.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.trashclash.trashclash.entities.BaseType;
import com.trashclash.trashclash.entities.CustomBase;
import com.trashclash.trashclash.entities.Image;
import com.trashclash.trashclash.entities.TownHall;
import com.trashclash.trashclash.repository.BaseTypeRepository;
import com.trashclash.trashclash.repository.TownHallRepository;
import com.trashclash.trashclash.services.CustomBaseService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomBaseController {

    // private List<String> baseTypesList = new ArrayList<>();

    private final CustomBaseService customBaseService;
    private final TownHallRepository townHallRepository;
    private final BaseTypeRepository baseTypeRepository;

    public CustomBaseController(CustomBaseService customBaseService,
            TownHallRepository townHallRepository,
            BaseTypeRepository baseTypeRepository) {
        this.customBaseService = customBaseService;
        this.townHallRepository = townHallRepository;
        this.baseTypeRepository = baseTypeRepository;
    }

    @GetMapping("/add-base")
    public String showAddBaseForm(Model model) {
        // for (int i = 1; i <= 15; i++) {
        // TownHall tHall = new TownHall();
        // tHall.setName("Town Hall " + String.valueOf(i));
        // townHallRepository.save(tHall);
        // }

        // baseTypesList.addAll(List.of("Farming Bases", "Trophy Bases", "Hybrid Bases",
        // "War Bases"));
        // for (String bType : baseTypesList) {
        // BaseType b = new BaseType();
        // b.setName(bType);
        // baseTypeRepository.save(b);
        // }

        model.addAttribute("townHalls", townHallRepository.findAll());
        model.addAttribute("baseTypes", baseTypeRepository.findAll());
        return "addBase";

    }

    @PostMapping("/admin/add-base")
    public ResponseEntity<CustomBase> createCustomBase(
            @RequestParam String name,
            @RequestParam String about,
            @RequestParam Long townHallId,
            @RequestParam String imageLinks,
            @RequestParam String baseDownloadLink,
            @RequestParam("baseTypes") Set<Long> baseTypeIds) throws Exception {

        CustomBase customBase = new CustomBase();
        customBase.setName(name);
        customBase.setRating(0);
        customBase.setNumberOfPeopleRated(0);
        customBase.setAbout(about);
        customBase.setNumberOfDownloads(0);
        customBase.setUploadedTime(Timestamp.from(Instant.now()));
        customBase.setBaseDownloadLink(baseDownloadLink);

        // Townhall should always be present
        TownHall townHall = townHallRepository.findById(townHallId)
                .orElseThrow(() -> new Exception("Townhall not found"));

        customBase.setTownHall(townHall);

        // Handling Image list
        List<Image> imageList = Arrays.stream(imageLinks.split(","))
                .map(String::trim).map(link -> {
                    Image img = new Image();
                    img.setUrl(link);
                    img.setCustomBase(customBase);
                    return img;
                }).collect(Collectors.toList());

        customBase.setImages(imageList);

        Set<BaseType> baseTypes = new HashSet<>();
        for (Long baseTypeId : baseTypeIds) {
            BaseType baseType = baseTypeRepository.findById(baseTypeId)
                    .orElseThrow(() -> new Exception("Basetype not found"));

            baseTypes.add(baseType);
        }

        customBase.setBaseTypes(baseTypes);
        System.out.println(customBase);

        CustomBase savedBase = customBaseService.saveCustomBase(customBase);

        return new ResponseEntity<>(savedBase, HttpStatus.CREATED);
    }

}
