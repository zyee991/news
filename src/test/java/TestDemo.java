import org.junit.Test;

import java.util.*;

public class TestDemo {
    @Test
    public void test1() {
        Map<String,String> map = new HashMap<>();
        map.put("a","1");
        map.put("f","2");
        map.put("b","3");
        map.put("ab","3");
        map.put("ca","3");
        map.put("d","3");
        map.put("c","3");
        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            sb.append(key).append("=").append(map.get(key)).append("&");
        }
        String param = sb.toString();
        param = param.substring(0,param.lastIndexOf("&"));
        System.out.println(param);
    }

    @Test
    public void test2() {
        Properties properties = System.getProperties();
        for(Object key : properties.keySet()) {
            System.out.println(key + ":" + properties.get(key));
        }
    }
}
