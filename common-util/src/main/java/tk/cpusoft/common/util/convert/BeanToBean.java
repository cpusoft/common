/*
 * @(#)BeanToBean.java 2011-11-10
 *
 * Copyright 2011 北龙中网（北京）科技有限责任公司. All rights reserved.
 */
package tk.cpusoft.common.util.convert;
import java.beans.PropertyDescriptor;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tk.cpusoft.common.util.validate.reglogin.RegLoginValidate;

/**
 * @desc：类转换
 * @date：2016年10月3日-上午9:53:02
 */
public class BeanToBean {
    /**
     * logback
     */
    private static Logger logger = LoggerFactory.getLogger(BeanToBean.class);
	/**
	 * 把srcObj的值拷贝到destObj
	 *
	 * @param obj
	 * @param obj1
	 * @throws Exception
	 */
	public void BTB(Object srcBean, Object destBean) {
		BeanUtilsBean beanUtils = new BeanUtilsBean();
		PropertyDescriptor[] origDescriptors = beanUtils.getPropertyUtils().getPropertyDescriptors(srcBean);
		for (int i = 0; i < origDescriptors.length; i++) {
			String name = origDescriptors[i].getName();
			if ("class".equals(name)) {
				continue;
			}
			if (beanUtils.getPropertyUtils().isReadable(srcBean, name) && beanUtils.getPropertyUtils().isWriteable(srcBean, name)) {
				try {
					Object value = beanUtils.getPropertyUtils().getSimpleProperty(srcBean, name);
					if (value != null) {
						beanUtils.copyProperty(destBean, name, value);
					}
				} catch (Exception e) {
					 logger.error("BTB():error:e:"+e.getMessage(),e);
				}
			}
		}
	}

}
