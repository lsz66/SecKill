package cn.szlee.shop.web;

import cn.szlee.shop.common.Constant;
import cn.szlee.shop.common.ResponseMessage;
import cn.szlee.shop.exception.SoldOutException;
import cn.szlee.shop.service.OrderService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    private final Map<Integer, Boolean> productSoldOutMap = new ConcurrentHashMap<>();

    public OrderController(OrderService orderService, StringRedisTemplate redisTemplate) {
        this.orderService = orderService;
        this.redisTemplate = redisTemplate;
    }

    @PostMapping
    public ResponseMessage secKill(Integer productId) {
        if (productSoldOutMap.get(productId) != null) {
            return ResponseMessage.error();
        }
        String key = Constant.REDIS_PRODUCT_STOCK_PREFIX + productId;
        Long stock = redisTemplate.opsForValue().decrement(key);
        assert stock != null;
        if (stock < 0) {
            productSoldOutMap.put(productId, true);
            redisTemplate.opsForValue().increment(key);
            return ResponseMessage.error();
        }
        try {
            orderService.secKill(productId);
            return ResponseMessage.success();
        } catch (SoldOutException e) {
            redisTemplate.opsForValue().increment(key);
            if (productSoldOutMap.get(productId) != null) {
                productSoldOutMap.remove(productId);
            }
            return ResponseMessage.error();
        }
    }
}
