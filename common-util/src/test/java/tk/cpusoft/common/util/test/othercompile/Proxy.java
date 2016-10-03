/**
 * @desc  
 * @date 2015年6月26日
 */
package tk.cpusoft.common.util.test.othercompile;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * @desc 没成功
 * @date 2015年6月26日-下午1:54:16
 */
public class Proxy {
    public static Object getNewInstance(Class<?> classInput,Object object) throws Exception {
        String path = System.getProperty("user.dir") + File.separator + "mybin"
                + File.separator + "com" + File.separator + "cn"
                + File.separator;
        String fileName = "$Proxy.java";
        String nextLine = System.getProperty("line.separator");
        // create java File
        String fileValue = "package com.cn;"+nextLine+
                           "import com.cn.*;"+nextLine+
                           "import java.lang.reflect.Method;"+nextLine+
                           "public class $Proxy implements "+ classInput.getName() +"{"+nextLine+
                           "    private InvactionHandle h;"+nextLine+
                           "    public $Proxy(InvactionHandle hin)"+ nextLine+
                           "     {"+nextLine+
                           "           this.h = hin;"+nextLine+
                           "     }"+nextLine;
        Method[] methods = classInput.getDeclaredMethods();
        for (Method m:methods) {
            fileValue += "    public "+ m.getReturnType()+" "+m.getName()+"()"+nextLine+
                       "     {"+nextLine+
                       "          try{            "+nextLine+
                       //测试方法不带参数  所以new Class<?>[]{}空参数传入
                       "          Method me = "+classInput.getName()+".class.getDeclaredMethod(\""+m.getName()+"\",  new Class<?>[]{});"+nextLine+
                       "          h.invoke(this,me);"+nextLine+
                       "          }catch(Exception e){ "+nextLine+
                       "           e.printStackTrace(); }"+nextLine+
                       "     }"+nextLine;
        }
        fileValue +="}"+nextLine;
        File f =  new File(path);//是否存在此目录
        if (!f.exists())
            f.mkdirs();
        FileWriter writer = new FileWriter(new File(f,fileName));
        writer.write(fileValue);
        writer.flush();
        writer.close();
        System.out.println("***************     create java file over      ******************");
        // compiler 生成 class文件  调取javac编译
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(null,null, null);
        Iterable<? extends JavaFileObject> in = manager.getJavaFileObjects(path+ fileName);
        CompilationTask task = compiler.getTask(null, manager, null, null,null, in);
        task.call();
        System.out.println("***************     complier class file over      ******************");

        // loader 加载class文件 的第一种方法 URLClassLoader可以load任意目录下的类！
        URL[] urls = new URL[] { new URL("file:/" + System.getProperty("user.dir") + File.separator + "mybin"+ File.separator) };
        URLClassLoader loader = new URLClassLoader(urls);
        Class<?> classLoader = loader.loadClass("com.cn.$Proxy");
        System.out.println("***************     loader class file over      ******************");

        // newInstance class JVM
        Constructor<?> con = classLoader.getConstructor(classInput);
        Object o = con.newInstance(object);
        // newInstance...
        /**
         加载class文件 的第二种方法 ClassLoader只能load位于classpath（src目录）下的类
         Class<?> second = Proxy.class.getClassLoader().loadClass("com.cn.$Proxy");
         System.out.println(second.getSimpleName());
         */
        return o;
    }
}
