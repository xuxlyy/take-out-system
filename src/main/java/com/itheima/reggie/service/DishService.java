package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;

public interface DishService extends IService<Dish> {

    //新增菜品 同时插入菜品相关的口味数据
    //需要操作两张表 -->dish、dish_flavor
    public void saveWithFlavor(DishDto dishDto);

    //根据id查询对应菜品信息和对应的口味数据
    //操作两张表 ： dish , dish_flavor
    public DishDto getByIdWithFlavor(Long id);

    //修改菜品信息和对应口味信息
    public void updateWithFlavor(DishDto dishDto);
}
