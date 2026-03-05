package com.example.demo.user;

import lombok.Getter;
import lombok.Setter;
  // comeca pela entidade dps vai pro acesso ao banco (userrepository) services, controller
@Getter
@Setter
public class User {
    private String name;
    private String email;
}
