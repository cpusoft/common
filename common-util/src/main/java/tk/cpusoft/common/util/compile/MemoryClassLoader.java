/**
 * @desc  
 * @date 2015年6月26日
 */
package tk.cpusoft.common.util.compile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject.Kind;
import javax.tools.ToolProvider;

/**
 * @desc 
 * @date 2015年6月26日-下午2:46:11
 */
public class MemoryClassLoader extends ClassLoader {
    private final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    private final MemoryFileManager manager = new MemoryFileManager(this.compiler);

    public MemoryClassLoader(String classname, String filecontent) {
        Map<String,String> map = new HashMap<String,String>();
        map.put(classname, filecontent);
        init(map);
    }

    public MemoryClassLoader(Map<String,String> map) {
        init(map);
    }
    private void init(Map<String,String> map){
        List<Source> list = new ArrayList<Source>();
        for (Map.Entry<String,String> entry : map.entrySet()) {
            list.add(new Source(entry.getKey(), Kind.SOURCE, entry.getValue()));
        }
        this.compiler.getTask(null, this.manager, null, null, null, list).call();
    }

    @Override
    protected Class findClass(String name) throws ClassNotFoundException {
        synchronized (this.manager) {
            Output mc = (Output)this.manager.map.remove(name);
            if (mc != null) {
                byte[] array = mc.toByteArray();
                return defineClass(name, array, 0, array.length);
            }
        }
        return super.findClass(name);
    }

}
