package com.fake.demo.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lost {
  private String nameID;
  @NotBlank(message = "bean.entity.Lost.name cannot be blank")
  private String name;
  @NotBlank
  private String itemDesc;
  @NotBlank
  private String lostTime;
  @NotBlank
  private String grade;
  @NotNull
  private int foundItem;
  private String idIfFound;
}
