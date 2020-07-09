package cn.szlee.shop.exception;

/**
 * <b><code>SoldOutException</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2020/7/9 2:19 下午.
 *
 * @author Li Shangzhe
 * @since seckill 0.3.0
 */
public class SoldOutException extends RuntimeException {
    public SoldOutException() {
        super("商品已售完");
    }
}
