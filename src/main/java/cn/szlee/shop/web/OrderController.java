package cn.szlee.shop.web;

import cn.szlee.shop.common.Constant;
import cn.szlee.shop.common.ResponseMessage;
import cn.szlee.shop.exception.SoldOutException;
import cn.szlee.shop.service.OrderService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b><code>OrderController</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2020/7/9 12:32 下午.
 *
 * @author Li Shangzhe
 * @since seckill 0.3.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final StringRedisTemplate redisTemplate;

    public OrderController(OrderService orderService, StringRedisTemplate redisTemplate) {
        this.orderService = orderService;
        this.redisTemplate = redisTemplate;
    }

    @PostMapping
    public ResponseMessage secKill(Integer productId) {
        Long stock = redisTemplate.opsForValue().decrement(Constant.REDIS_PRODUCT_STOCK_PREFIX + productId);
        assert stock != null;
        if (stock < 0) {
            return ResponseMessage.error();
        }
        try {
            orderService.secKill(productId);
            return ResponseMessage.success();
        } catch (SoldOutException e) {
            return ResponseMessage.error();
        }
    }
}
