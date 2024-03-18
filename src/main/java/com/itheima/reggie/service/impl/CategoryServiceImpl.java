package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.common.CustomException;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.mapper.CategoryMapper;
import com.itheima.reggie.mapper.DishMapper;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.DishService;
import com.itheima.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    /**
     * 根据id删除分类 在删除之前要先进行判断
     * @param id
     */
    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询分类是否已经关联了菜品 如果已关联 那么抛出一个业务异常
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int countDish = dishService.count(dishLambdaQueryWrapper);//查询数量
        if(countDish > 0){//已经关联菜品 抛出业务异常
            throw new CustomException("当前分类已经关联菜品，不能删除");
        }

        //查询分类是否已经关联了套餐 如果已关联 那么抛出一个业务异常
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int countMeal = setmealService.count(setmealLambdaQueryWrapper);
        if(countMeal > 0){//已经关联套餐 抛出业务异常
            throw new CustomException("当前分类已经关联套餐，不能删除");
        }

        //正常删除分类
        super.removeById(id);
    }
}
