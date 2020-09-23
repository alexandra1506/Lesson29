package reflexapi.model;

import reflexapi.impl.Movable;
import reflexapi.impl.Speaking;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Person implements Movable, Speaking {

    private String name;
    private Integer age;

    public Integer x;

    public Person(String name) {
        this.name = name;
    }

    public Person(Integer age) {
        this.age = age;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    private boolean check() {
        return (!this.name.isEmpty() && this.age > 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int coordX() {
        return 10;
    }

    @Override
    public int coordY() {
        return -10;
    }

    @Override
    public String say() {
        return "Привет";
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", x=" + x +
                '}';
    }

    public void info() {
        System.out.println("Information about object");
        // инфа о полях
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Поле = " + field.getName() + " тип - " + field.getType());
        }
            // инфа о методах
            Method [] methods = this.getClass().getDeclaredMethods();
            for (Method method: methods) {
                Class<?> [] parameterTypes = method.getParameterTypes();
                System.out.println("\nМетод - "+method.getName());
                for (Class parType: parameterTypes) {
                    System.out.println("\t"+parType.getName());
                }
                System.out.print("\t - \t"+method.getReturnType());
            }


    }
}