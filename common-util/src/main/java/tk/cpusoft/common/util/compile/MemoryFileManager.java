/**
 * @desc  
 * @date 2015年6月26日
 */
package tk.cpusoft.common.util.compile;

import java.util.HashMap;
import java.util.Map;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject.Kind;

/**
 * @desc 
 * @date 2015年6月26日-下午2:45:06
 */
public class MemoryFileManager extends ForwardingJavaFileManager {
    public final Map<String,Output> map = new HashMap<String,Output>();

    MemoryFileManager(JavaCompiler compiler) {
        super(compiler.getStandardFileManager(null, null, null));
    }

    @Override
    public Output getJavaFileForOutput
            (Location location, String name, Kind kind, FileObject source) {
        Output mc = new Output(name, kind);
        this.map.put(name, mc);
        return mc;
    }
}
