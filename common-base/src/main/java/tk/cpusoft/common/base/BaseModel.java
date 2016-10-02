/**
 * @desc  
 * @date  2015年2月9日
 */
package tk.cpusoft.common.base;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @desc 基础类，所有的数据库映射类，都需要从此Model派生<br/>
 *     
 * @date 2015年2月6日-下午3:04:56
 */
public class BaseModel implements Serializable,Cloneable {

   /**
     * @desc 
     * @date 2015年2月6日-下午3:05:45
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @desc 自动log4j
     * @date 2015年2月6日-下午3:09:24
     * @return 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * 
     * @desc 克隆类，派生类如果有复杂属性，需要Override
     * @date 2015年2月6日-下午3:10:24
     * @return Object
     * @throws CloneNotSupportedException 
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }
    
    
    
    
}
