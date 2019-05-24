package controller;

import annotation.Scheduled;
import model.Time;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MainController {
    private Object object = null;
    private Timer timer;

    public MainController() {
        timer = new Timer();
    }

    public void startMethod(Method method, Object object) {
        Scheduled scheduled = method.getAnnotation(Scheduled.class);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    method.invoke(object);
                } catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                }
            }
        }, 0, scheduled.period());
    }

    public void stopMethod() {
        timer.cancel();
    }

    public Class<?> searchClass(String nameClass) {
        try {
            return Class.forName(nameClass);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public void createObject(Class<?> nameClass) {
        try {
            object = nameClass.newInstance();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }

        Method[] methods = nameClass.getDeclaredMethods();
        Arrays.stream(methods)
              .filter(method -> method.isAnnotationPresent(Scheduled.class))
              .forEach(method -> startMethod(method, object));
    }
}
