package com.example.jongkook.annotationtest;

import android.app.Activity;
import android.widget.Button;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by jongkook on 2016. 10. 27..
 */

public class ButterSpoon {
    public static void bind(Activity object){

        Class<?> klass = object.getClass();

        // MainActivity.class.getClassLoader().loadClass();
        try{
            for (Field field : klass.getClassLoader().loadClass(klass.getName()).getDeclaredFields()){
                if(field.isAnnotationPresent(com.example.jongkook.annotationtest.BindView.class)){

                    BindView bindView = field.getAnnotation(BindView.class);
                    int viewID = bindView.viewID();

                    Button btn = (Button)object.findViewById(viewID);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    };
}
