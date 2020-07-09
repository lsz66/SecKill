package cn.szlee.shop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * <b><code>Order</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2020/7/9 12:21 下午.
 *
 * @author Li Shangzhe
 * @since seckill 0.3.0
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class SecKillOrder {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NonNull
    private Integer productId;
}
