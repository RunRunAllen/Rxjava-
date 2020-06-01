package com.example.notedemo;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class InjectUtil {

    public static void injectView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
//        clazz.getFields() //获取自己的/父类的 （public的）
//        clazz.getDeclaredFields() //获取自己的 ，任何作用域

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectView.class)) {
                InjectView annotation = field.getAnnotation(InjectView.class);
                int id = annotation.value();
                View view = activity.findViewById(id);
                field.setAccessible(true);
                try {
                    field.set(activity,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
