package org.arijit.consistent.hashing.annotation.processor;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.*;

public class AnnotationProcessorUtil {

    private AnnotationProcessorUtil(){

    }


    public  List<Class> findAnnotatedClass(String basePackage,Class annotation){
        System.out.println("Looking for annotated class in package: "+basePackage);
        List<Class> annotatedClass = new ArrayList<>();
        try {
            List<Class> classList = getClasses(basePackage);
            if(classList==null || classList.isEmpty()){
                System.out.println("No class found under this base package");
                return Collections.emptyList();
            }
            Iterator<Class> iterator = classList.iterator();
            while(iterator.hasNext()){
                Class<?> cls = iterator.next();
                System.out.println("ClassName: "+cls.getName());
                Annotation markedAnotation = cls.getAnnotation(annotation);
                if(markedAnotation!=null) {
                    System.out.println("Annotation: " + markedAnotation);
                    annotatedClass.add(cls);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return annotatedClass;
    }

    private List<Class> getClasses(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public static AnnotationProcessorUtil getInstance(){
        return AnnotationProcessorUtilInner.getInstance();
    }

    public static class AnnotationProcessorUtilInner{
        private static AnnotationProcessorUtil instance = new AnnotationProcessorUtil();
        private AnnotationProcessorUtilInner(){

        }
        public static AnnotationProcessorUtil getInstance() {
            return instance;
        }
    }
}
