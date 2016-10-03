/**
 * @desc  
 * @date 2015年6月26日
 */
package tk.cpusoft.common.util.test.othercompile;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

/**
 * @desc 没成功
 * @date 2015年6月26日-下午2:31:44
 */
public class CompileString {

    public static void main(String[] args) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        String program = "class Test{\r\n" + "   public static void main (String [] args){\r\n"
                + "      System.out.println (\"Hello, World\");\r\n"
                + "      System.out.println (args.length);" + "   }" + "}";
        Iterable<? extends JavaFileObject> fileObjects;
        fileObjects = getJavaSourceFromString3(program);
        compiler.getTask(null, null, null, null, null, fileObjects).call();
        Class<?> clazz = Class.forName("Test");
        Method m = clazz.getMethod("main", new Class[] { String[].class });
        Object[] _args = new Object[] { new String[0] };
        m.invoke(null, _args);
    }
    static Iterable<JavaSourceFromString3> getJavaSourceFromString3(String code) {
        final JavaSourceFromString3 jsfs;
        jsfs = new JavaSourceFromString3("HelloWorld", code);
        return new Iterable<JavaSourceFromString3>() {
            public Iterator<JavaSourceFromString3> iterator() {
                return new Iterator<JavaSourceFromString3>() {
                    boolean isNext = true;
                    public boolean hasNext() {
                        return isNext;
                    }
                    public JavaSourceFromString3 next() {
                        if (!isNext)
                            throw new NoSuchElementException();
                        isNext = false;
                        return jsfs;
                    }
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}
class JavaSourceFromString3 extends SimpleJavaFileObject {
    final String code;
    JavaSourceFromString3(String name, String code) {
        super(URI.create("string:///" + name.replace(".", "/") + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }
}