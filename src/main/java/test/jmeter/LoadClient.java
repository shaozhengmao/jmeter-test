package test.jmeter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Description:
 *
 * @author: zhengmiao
 * @Date: 2018/06/12 下午2:34
 */
public class LoadClient extends AbstractJavaSamplerClient
{
    /**
     * 需要测试的接口
     */
    @Resource(name = "xxxApiService")
    //private xxxApiServices xxxApiService;

    private long start = 0;//记录测试开始时间；
    private long end = 0;//记录测试结束时间；


    //初始化操作
    @Override
    public void setupTest(JavaSamplerContext arg0) {
        DubboInit init = DubboInit.getInstance();
        DubboInit.initApplicationContext();
        //xxxApiService = (xxxApiService) init.getBean("xxxApiService");
    }



    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sr = new SampleResult();

        start = System.currentTimeMillis();
        try {
            /**
             * 构建请求对象
             */
            String info = "";

            // Request request = JSON.parseObject(info, Request.class);
            /**
             * 构建jmeter参数对象
             */


            Map<String,String> map = JSON.parseObject(info,  new TypeReference<Map<String, String>>(){});
            Arguments arguments = new Arguments();
            for(Map.Entry<String, String> entry : map.entrySet()){
                arguments.addArgument(entry.getKey(), entry.getValue());
            }
            javaSamplerContext = new JavaSamplerContext(arguments);

            sr.sampleStart();
            /**
             * 调用远程方法
             */
            //ResponseResult<xxVO> result = xxxApiService.remoteMethod(request);
            sr.sampleEnd();

            /**
             * 构建结果
             */
            sr.setSuccessful(true);
            sr.setResponseCodeOK();
            sr.setResponseData(""/*result*/, "utf-8");

        } catch (Exception e) {
            getLogger().error("response error : " + e.getMessage());
            sr.setSuccessful(false);
        }
        return sr;
    }

    @Override
    public void teardownTest(JavaSamplerContext arg0) {
        end = System.currentTimeMillis();
        getLogger().info("    cost time: " + (end - start) + "ms");
    }

    public static void main(String[] args) {
        Arguments arguments = new Arguments();
        arguments.addArgument("expertId","00001748-5cb6-49fe-b254-eb4251a455a8000");
        JavaSamplerContext javaSamplerContex = new JavaSamplerContext(arguments);
        LoadClient jMeter = new LoadClient();
        jMeter.setupTest(javaSamplerContex);
        jMeter.runTest(javaSamplerContex);
        jMeter.teardownTest(javaSamplerContex);
    }
}
