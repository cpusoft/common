/**
 * @desc  
 * @date 2015年6月26日
 */
package tk.cpusoft.common.util.compile;


import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @desc 动态内存中编译java代码
 * @date 2015年6月26日-下午3:39:02
 */
public class DynamicCompile {
    private Logger logger = LoggerFactory.getLogger(DynamicCompile.class);
    
    /**
     * @desc 动态编译并运行函数，没有做过多检查，需要自己保障源代码正确  https://weblogs.java.net/blog/2008/12/17/how-compile-fly 
     * @date 2015年6月26日-下午3:55:46
     * @param className 类名，不需要包名
     * @param javaContent 源代码的内容
     * @param method  执行的方法名
     * @param args  参数
     * @return Object  返回的值
     */
    public Object compileAndInvokeClass(String className,String javaContent,String method, Object[] args){
        try {
            if(StringUtils.isBlank(className) || StringUtils.isBlank(javaContent) || StringUtils.isBlank(method) || args==null){
                return null;
            }
            Class cls = compileClass(className, javaContent);
            return invokeClass(cls,method,args);
        } catch (Exception e) {
            logger.error("compileAndInvokeClass():exception:className:"+className+
                    "  javaContent:"+javaContent+
                    "  method:"+method+"  args:"+args+"   e:"+e.getMessage(),e);
            return null;
        }
    }
    
    /**
     * @desc 动态编译，
     * @date 2015年6月29日-上午9:18:14
     * @param className 类名
     * @param javaContent 源代码
     * @return Class 返回的class类
     */
    public Class compileClass(String className,String javaContent){
        try {
            if(StringUtils.isBlank(className) || StringUtils.isBlank(javaContent) ){
                return null;
            }
            MemoryClassLoader mcl = new MemoryClassLoader(className, javaContent);
            return  mcl.loadClass(className);
        } catch (Exception e) {
            logger.error("compileClass():exception:className:"+className+
                    "  javaContent:"+javaContent+
                    "   e:"+e.getMessage(),e);
            return null;
        } 
    }
    
    /**
     * @desc 根据class，动态运行
     * @date 2015年6月29日-上午9:18:27
     * @param cls class类对象
     * @param method 方法
     * @param args 参数
     * @return Object   返回的结果
     */
    public Object invokeClass( Class cls ,String method, Object[] args){
        try {
            if(cls == null ||  StringUtils.isBlank(method) || args==null){
                return null;
            }
            Object obj = cls.newInstance();  
            Class[] argsClass = new Class[args.length];   
            for (int i = 0, j = args.length; i < j; i++) {   
                argsClass[i] = args[i].getClass();   
            }   
            
            Method m = cls.getMethod(method, argsClass);
            return m.invoke(obj,args);
        } catch (Exception e) {
            logger.error("invokeClass():exception:"+
                    "  method:"+method+"  args:"+args+"   e:"+e.getMessage(),e);
            return null;
        }
    }
}
