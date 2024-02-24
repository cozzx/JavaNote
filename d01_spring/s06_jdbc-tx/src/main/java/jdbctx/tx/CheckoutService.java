package jdbctx.tx;

/**
 * @author zt
 * @date 2023/8/21
 **/
public interface CheckoutService {

    void checkout(Integer[] bookIds, Integer userId);
}
