package com.fake.demo.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("lost_list")
public class Lost {
  @TableId("name_id")
  private String nameID;

  @TableField("name")
  @NotBlank(message = "bean.entity.Lost.name cannot be blank")
  private String name;

  @TableField("item_desc")
  @NotBlank
  private String itemDesc;

  @TableField("lost_time")
  @NotBlank
  private String lostTime;

  @TableField("grade")
  @NotBlank
  private String grade;

  @TableField("found_item")
  @NotNull
  private int foundItem;

  @TableField("id_if_found")
  private String idIfFound;
}
