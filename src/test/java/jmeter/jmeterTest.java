package jmeter;

import test.jmeter.DubboInit;

/**
 * Description:
 *
 * @author: zhengmiao
 * @Date: 2018/06/12 下午3:28
 */
public class jmeterTest
{
    public static void main(String[] args) {
        DubboInit init = DubboInit.getInstance();
        DubboInit.initApplicationContext();
    }
}
