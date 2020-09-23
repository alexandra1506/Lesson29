package reflexapi;

import reflexapi.model.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Application {

    public static void main(String[] args) {

        Class clazz1 = Person.class;
        System.out.println(clazz1);

        Class clazz2 = null;
        try {
            clazz2 = Class.forName("reflexapi.model.Person");
            System.out.println(clazz2.getSimpleName());

            int modifiers = clazz2.getModifiers();
            System.out.println("modifiers = "+modifiers);
            System.out.println("isAbstract = "+Modifier.isAbstract(modifiers));
            System.out.println("isPublic = "+Modifier.isPublic(modifiers));

            System.out.println("***** interfaces *****");
            Class[] interfaces = clazz2.getInterfaces();
            for (Class in:interfaces) {
                System.out.println("interface - "+ in);
            }

            System.out.println("***** constructors *****");
            Constructor[] constructors = clazz2.getConstructors();
            for (Constructor con: constructors) {
                Class[] parameterTypes = con.getParameterTypes();
                System.out.print(con.getName()+"(");
                for (Class paramType: parameterTypes) {
                    System.out.print("\t"+paramType.getName());
                }
                System.out.println(")");
            }

            Person person = (Person) clazz2.getConstructor(String.class).
                    newInstance("Ваня");
            System.out.println(person.toString());

            Person person1 = new Person("Ivan");
            System.out.println(person1.toString());

            System.out.println("***** fields *****");
            Field [] fields = clazz2.getDeclaredFields();
            for (Field field: fields) {
                System.out.println("Поле = "+field.getName()+ " - "+field.getType());
                if (field.getName().equals("age")){
                    field.setAccessible(true);
                    field.set(person, new Integer(12));
                }
            }
            System.out.println("Персон "+person.toString());

            System.out.println("***** parametertype method *****");
            Method [] methods = clazz2.getDeclaredMethods();
            for (Method method: methods) {
                Class<?> [] parameterTypes = method.getParameterTypes();
                System.out.println(method.getName());
                for (Class parType: parameterTypes) {
                    System.out.println("\t"+parType.getName());
                }
                System.out.println("\t - \t"+method.getReturnType());
            }

            System.out.println("***** invoke method *****");
            Method say = clazz2.getMethod("say");
            System.out.println(say.invoke(person));

            System.out.println("******* info *******");
            person1.info();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
