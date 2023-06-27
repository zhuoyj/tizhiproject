package com.common.server.domain;

import javax.persistence.*;
import java.util.List;


@Table(name = "tb_user_class")
public class UserClass {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")


       private Long id;



       private String name;

   public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
