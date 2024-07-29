package com.trashclash.trashclash.entities;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class CustomBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double rating;
    private int numberOfPeopleRated;
    private String about;
    private int numberOfDownloads;
    private Timestamp uploadedTime;
    private String baseDownloadLink;

    @ManyToOne
    @JoinColumn(name = "townhall_id", nullable = false)
    private TownHall townHall;

    @OneToMany(mappedBy = "customBase", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Image> images;

    @ManyToMany
    @JoinTable(name = "BaseTypeTag", joinColumns = @JoinColumn(name = "custom_base_id"), inverseJoinColumns = @JoinColumn(name = "base_type_id"))
    @JsonManagedReference
    private Set<BaseType> baseTypes;
}
