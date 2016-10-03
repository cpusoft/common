/**
 * @desc  
 * @date 2015年6月26日
 */
package tk.cpusoft.common.util.compile;

import java.net.URI;

import javax.tools.SimpleJavaFileObject;

/**
 * @desc 
 * @date 2015年6月26日-下午2:43:55
 */
public class Source extends SimpleJavaFileObject {
    private final String content;

    Source(String name, Kind kind, String content) {
        super(URI.create("memo:///" + name.replace('.', '/') + kind.extension), kind);
        this.content = content;
    }

    @Override
    public CharSequence getCharContent(boolean ignore) {
        return this.content;
    }
}