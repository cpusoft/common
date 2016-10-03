package tk.cpusoft.common.util.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @desc 异常记录， 
 * @date 2016年3月27日-下午1:36:13
 */
public class ExceptionUtil {

    private ExceptionUtil(){};
    
    /**
     * @desc 异常记录，只能用在logservice中，其他类不能使用（只能用log4j自带的e记录） 
     * @date 2016年3月27日-下午1:36:30
     * @param exception
     * @return String 
     */
    public static String printStackTrace(Throwable exception) {
        if (exception == null) {
            return "";
        } else {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            pw.flush();
            String expString = sw.toString();
            pw.close();
            return expString;
        }
    }
}
