package cn.szlee.shop.service;

import cn.szlee.shop.exception.SoldOutException;
import cn.szlee.shop.mapper.OrderMapper;
import cn.szlee.shop.mapper.ProductMapper;
import cn.szlee.shop.model.SecKillOrder;
import cn.szlee.shop.model.Product;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public OrderService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public void secKill(int productId) {

        // 查询商品
        Product product = productMapper.selectById(productId);
        int stock = product.getStock();
        if (stock <= 0) {
            throw new SoldOutException();
        }

        // 保存订单
        save(SecKillOrder.of(productId));

        // 减库存
        product.setStock(stock - 1);
        if (productMapper.updateById(product) <= 0) {
            throw new SoldOutException();
        }
    }
}
