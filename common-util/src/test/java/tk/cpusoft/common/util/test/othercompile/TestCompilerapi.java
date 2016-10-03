/**
 * @desc  
 * @date 2015年6月26日
 */
package tk.cpusoft.common.util.test.othercompile;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.util.Arrays;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

/**
 * @desc 没成功
 * @date 2015年6月26日-下午2:27:14
 */
public class TestCompilerapi {

    private static void compilerJava() throws Exception{
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector diagnostics = new    DiagnosticCollector();
        // 定义一个StringWriter类，用于写Java程序
        StringWriter writer = new StringWriter();
        PrintWriter out = new PrintWriter(writer);
        // 开始写Java程序
        out.println("public class HelloWorld {");
        out.println(" public static void main(String args[]) {");
        out.println(" System.out.println(\"Hello, World\");");
        out.println(" }");
        out.println("}");
        out.close();
        //为这段代码取个名子：HelloWorld，以便以后使用reflection调用
        JavaFileObject file = new JavaSourceFromString2("HelloWorld", writer.toString());
        Iterable compilationUnits = Arrays.asList(file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, null,
                diagnostics, null, null, compilationUnits);
        boolean success = task.call();
        System.out.println("Success: " + success);
        // 如果成功，通过reflection执行这段Java程序
        if (success)
        {
            System.out.println("-----输出-----");
            Class.forName("HelloWorld").getDeclaredMethod("main", new Class[]
                    { String[].class }).invoke(null, new Object[]
                            { null });
            System.out.println("-----输出 -----");
        } 
    }
    public static void main(String args[]) throws Exception    {
        compilerJava();
    }
}
// 用于传递源程序的JavaSourceFromString类
class JavaSourceFromString2 extends SimpleJavaFileObject
{
    final String code;
    JavaSourceFromString2(String name, String code)  {
        super(URI.create(
                "string:///" + name.replace('.', '/')+ Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }
    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors)   {
        return code;
    }
}