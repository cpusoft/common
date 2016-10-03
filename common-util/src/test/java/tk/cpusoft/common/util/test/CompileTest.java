/**
 * @desc  
 * @date 2015年6月26日
 */
package tk.cpusoft.common.util.test;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import tk.cpusoft.common.util.compile.DynamicCompile;
import tk.cpusoft.common.util.compile.MemoryClassLoader;

/**
 * @desc 
 * @date 2015年6月26日-下午3:35:48
 */
public class CompileTest {
    private static final String CLASS = "Test";
    private static final String CLASS2 = "Test2";
    
    private static final String METHOD = "execute";
    
    private static final String EXPRESSION = "Math.cos(Math.PI/6)";
    private static final String EXPRESSION2 = "Math.cos(Math.PI/3)";
    
    private static final String CONTENT    
            = "public class " + CLASS + " {"
            + "    public Object " + METHOD + "(String str) {"
            + "        return str+" + EXPRESSION + ";"
            + "    }"
            + "}";
    
    private static final String CONTENT2    
    = "public class " + CLASS2 + " {"
    + "    public Object " + METHOD + "(String str) {"
    + "        return str+" + EXPRESSION2 + ";"
    + "    }"
    + "}";
    
    @Test 
    public void compileSimpleTest() throws Exception {
    //    MemoryClassLoader mcl = new MemoryClassLoader(CLASS, CONTENT);
     //   System.out.println(mcl.loadClass(CLASS).getMethod(METHOD).invoke(null));
        Object[] os = new Object[]{"yes"};
        DynamicCompile dc = new DynamicCompile();
        String ret = (String)dc.compileAndInvokeClass(CLASS, CONTENT, METHOD, os);
        System.out.println(ret);
    }
    
    @Test 
    public void compileMapTest() throws Exception {
    //    MemoryClassLoader mcl = new MemoryClassLoader(CLASS, CONTENT);
     //   System.out.println(mcl.loadClass(CLASS).getMethod(METHOD).invoke(null));
        Object[] os = new Object[]{"yes"};
        DynamicCompile dc = new DynamicCompile();
        String class1 = "class1";
        String class2 = "class2";
        Class cls1 = dc.compileClass(CLASS, CONTENT);
        Class cls2 = dc.compileClass(CLASS2, CONTENT2);
        Map map = new HashMap();
        map.put(class1, cls1);
        map.put(class2,cls2);
        
        Class cls = (Class)map.get(class1);
        String ret = (String)dc.invokeClass(cls, METHOD, os);
        System.out.println(ret);
        
        cls = (Class)map.get(class2);
        ret = (String)dc.invokeClass(cls, METHOD, os);
        System.out.println(ret);
        
    }

}
