package test.jmeter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description:
 *
 * @author: zhengmiao
 * @Date: 2018/06/12 上午11:41
 */
public class DubboInit
{
    private static DubboInit init = null;
    private DubboInit(){}
    private  static ApplicationContext context;
    public synchronized static DubboInit getInstance(){
        if(init == null){
            init = new DubboInit();
        }
        return init;
    }

    public  static void  initApplicationContext(){
        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        if(context==null)
        {
            throw new IllegalArgumentException("Load dubbo-client.xml fail");
        }
    }

    public  Object getBean(String beanName) {
        return context.getBean(beanName);
    }
}
