package cn.szlee.shop.mapper;

import cn.szlee.shop.model.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * <b><code>ProductMapper</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2020/7/9 12:24 下午.
 *
 * @author Li Shangzhe
 * @since seckill 0.3.0
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 库存减1
     *
     * @param productId 商品id
     * @return 更新记录条数
     */
    @Update("update product set stock = stock - 1 where id = #{productId} and stock > 0")
    int deductStock(int productId);
}
