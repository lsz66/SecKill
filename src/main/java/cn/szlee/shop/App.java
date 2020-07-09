package cn.szlee.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <b><code>App</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2020/7/9 12:18 下午.
 *
 * @author Li Shangzhe
 * @since seckill 0.3.0
 */
@SpringBootApplication
@MapperScan("cn.szlee.shop.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
