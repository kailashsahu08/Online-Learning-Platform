package com.xyz.EHub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private String imageUrl;
    private String videoUrl;

    private int totalLike;
    @ManyToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Users> user=new ArrayList<>();
//    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
//    private List<Content> content=new ArrayList<>();

}
