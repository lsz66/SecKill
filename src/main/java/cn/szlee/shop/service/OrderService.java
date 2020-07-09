package cn.szlee.shop.service;

import cn.szlee.shop.common.Constant;
import cn.szlee.shop.exception.SoldOutException;
import cn.szlee.shop.mapper.OrderMapper;
import cn.szlee.shop.mapper.ProductMapper;
import cn.szlee.shop.model.SecKillOrder;
import cn.szlee.shop.model.Product;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * <b><code>OrderService</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2020/7/9 12:25 下午.
 *
 * @author Li Shangzhe
 * @since seckill 0.3.0
 */
@Service
public class OrderService extends ServiceImpl<OrderMapper, SecKillOrder> {

    private final ProductMapper productMapper;
    private final StringRedisTemplate redisTemplate;


    public OrderService(ProductMapper productMapper, StringRedisTemplate redisTemplate) {
        this.productMapper = productMapper;
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        List<Product> products = productMapper.selectList(null);
        products.forEach(item -> redisTemplate.opsForValue().set(Constant.REDIS_PRODUCT_STOCK_PREFIX + item.getId(), String.valueOf(item.getStock())));
    }

    @Transactional(rollbackFor = Exception.class)
    public void secKill(int productId) {

        // 查询商品
        Product product = productMapper.selectById(productId);
        if (product.getStock() <= 0) {
            throw new SoldOutException();
        }

        // 保存订单
        save(SecKillOrder.of(productId));

        // 减库存
        int update = productMapper.deductStock(productId);
        if (update <= 0) {
            throw new SoldOutException();
        }
    }
}
