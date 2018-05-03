import com.topweshare.HystrixApplication;
import com.topweshare.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

/**
 * @author mongoding
 * @create 2017/7/25.
 * @blog https://mongoding.github.io
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HystrixApplication.class)
public class CollapserTest {

    @Autowired
    UserService userService;

    @Test
    public void test() throws Exception {
        Future<String> u1 = userService.findById(1L);
        Future<String> u2 = userService.findById(2L);
        Future<String> u3 = userService.findById(3L);
        Future<String> u4 = userService.findById(4L);


        log.info(u1.get().toString());
        log.info(u2.get().toString());
        log.info(u3.get().toString());
        log.info(u4.get().toString());


        Assert.assertEquals("aaa",u1.get());
        Assert.assertEquals("bbb",u2.get());
        Assert.assertEquals("ccc",u3.get());
        Assert.assertEquals("ddd",u4.get());
    }

}
