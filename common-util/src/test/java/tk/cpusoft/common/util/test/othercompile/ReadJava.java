/**
 * @desc  
 * @date 2015年6月26日
 */
package tk.cpusoft.common.util.test.othercompile;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import org.testng.annotations.Test;

/**
 * @desc 这个成功了
 * @date 2015年6月26日-下午1:40:18
 */
public class ReadJava {

    /**
     * @desc 
     * @date 2015年6月26日-下午1:40:18
     * @param args void 
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new ReadJava().readJava();
    }
    
    public void readJava(){
        try{
            String currentDir = "E:\\Java\\workspace2014\\fin\\fin-crawl\\fin-crawl-download\\src\\test\\java";  
            // 將源码写入文件中  
            String src = "package tk.cpusoft.test;"  
                    + "public class TestCompiler {"  
                    + " public void disply() {"  
                    + " System.out.println(\"Hello\");"  
                    + "}}";  
            String filename = currentDir+"\\tk\\cpusoft\\test\\TestCompiler.java";  
            File file = new File(filename);  
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);  
            fw.write(src);  
            fw.flush();  
            fw.close();  
            // 使用JavaCompiler 编译java文件  
            JavaCompiler jc = ToolProvider.getSystemJavaCompiler();  
            StandardJavaFileManager fileManager = jc.getStandardFileManager(null, null, null);  
            Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects(filename);  
            CompilationTask cTask = jc.getTask(null, fileManager, null, null, null, fileObjects);  
            cTask.call();  
            fileManager.close();  
            // 使用URLClassLoader加载class到内存  
            URL[] urls = new URL[] { new URL("file:/" + currentDir + "/") };  
            URLClassLoader cLoader = new URLClassLoader(urls);  
            Class<?> c = cLoader.loadClass("tk.cpusoft.test.TestCompiler");  
            cLoader.close();  
            // 利用class创建实例，反射执行方法  
            Object obj = c.newInstance();  
            Method method = c.getMethod("disply");  
            method.invoke(obj);  
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
