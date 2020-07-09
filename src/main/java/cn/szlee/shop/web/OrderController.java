package cn.szlee.shop.web;

import cn.szlee.shop.common.ResponseMessage;
import cn.szlee.shop.exception.SoldOutException;
import cn.szlee.shop.service.OrderService;
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

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseMessage secKill(Integer productId) {
        try {
            orderService.secKill(productId);
            return ResponseMessage.success();
        } catch (SoldOutException e) {
            return ResponseMessage.error();
        }
    }
}
