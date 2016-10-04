package tk.cpusoft.common.redis.test.common;



import org.apache.commons.configuration.CompositeConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;


/**
 * 测试类 抽象父类
 * 
 * @date 2014年12月30日 下午1:45:53
 * @version 1.0
 * @since 1.0
 *
 */

@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" ,
        "classpath*:spring/applicationContext-redis.xml",
        "classpath*:spring/applicationContext-redis-cluster.xml"})
public abstract class AbstractSpringTestNGTest extends
		AbstractTestNGSpringContextTests {

    /**
     * 配置文件
     */
    @Autowired
    protected CompositeConfiguration config;
    
 

    @BeforeClass
    public void init(){
    }
    

	@BeforeClass
	public void prepare() {
	//    config  =  this.applicationContext.getBean("config",CompositeConfiguration.class);
    //    System.out.println(config);
	}

}
