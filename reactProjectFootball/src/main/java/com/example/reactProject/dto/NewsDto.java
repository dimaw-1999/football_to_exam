package com.example.reactProject.dto;

import com.example.reactProject.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto implements Serializable {

    private String name;
    private String description;
    private String url;

}
