import org.junit.Test;

import java.text.SimpleDateFormat;

public class StringIntern {
    SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void test(){
        String a = new String("abc");
        String b = a.intern();
        System.out.println(a == b);

        String c = new String("12")+new String("3");
        c.intern();
        String d = "123";
        System.out.println(c == d);

        long temp = System.currentTimeMillis();
    }
}
