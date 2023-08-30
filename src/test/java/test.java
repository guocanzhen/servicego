import org.springframework.util.ObjectUtils;

import java.io.UnsupportedEncodingException;

public class test {

    public static void main(String[] args) throws UnsupportedEncodingException {
//        String url = "https://18149y827w.51mypc.cn/view/toPayPage?merchantCode=2670123";
//        System.out.println(URLEncoder.encode(url, "UTF-8"));
        String gkjsjStr = ObjectUtils.isEmpty("\n1996") ? "" : "\n1996".toString().trim();
        System.out.println(gkjsjStr);
    }
}
