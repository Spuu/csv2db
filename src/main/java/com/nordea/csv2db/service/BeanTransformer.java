package com.nordea.csv2db.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

@AllArgsConstructor
public class BeanTransformer<T, R> implements Function<T,R> {

    private Class<R> clazz;

    @Override
    public R apply(T source) {
        try {
            R target = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
