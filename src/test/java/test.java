import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class test {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String url = "https://18149y827w.51mypc.cn/view/toPayPage?merchantCode=2670123";
        System.out.println(URLEncoder.encode(url, "UTF-8"));
    }
}
