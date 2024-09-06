package com.project.sales.Entity;

import com.project.sales.enums.UserRole;
import jakarta.persistence.*;
import jdk.jfr.Label;
import lombok.Data;


@Entity
@Data
@Table(name = "User")
public class User {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private  String password;
    private  String name;
    private UserRole role;


@Lob
@Column(columnDefinition = "LONGBLOB")
private byte[] img;



}
