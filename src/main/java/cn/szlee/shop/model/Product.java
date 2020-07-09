package cn.szlee.shop.model;

import lombok.Data;

/**
 * <b><code>Product</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2020/7/9 12:21 下午.
 *
 * @author Li Shangzhe
 * @since seckill 0.3.0
 */
@Data
public class Product {
    private Integer id;
    private String name;
    private Integer stock;
}
