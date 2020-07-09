package cn.szlee.shop.common;

import lombok.Data;

/**
 * <b><code>ResponseMessage</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2020/7/9 12:32 下午.
 *
 * @author Li Shangzhe
 * @since seckill 0.3.0
 */
@Data
public class ResponseMessage {
    private boolean success;
    private String message;

    private ResponseMessage(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static ResponseMessage success() {
        return new ResponseMessage(true, "下单成功");
    }

    public static ResponseMessage error() {
        return new ResponseMessage(false, "已售完");
    }
}
