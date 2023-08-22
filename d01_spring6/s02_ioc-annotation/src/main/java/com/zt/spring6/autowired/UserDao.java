package com.zt.spring6.autowired;

/**
 * 当有两个实现类时，单使用 @Autowired 会报错，因为 @Autowired 默认根据类型进行装配
 * 错误信息中说：不能装配，UserDao这个Bean的数量等于2
 * 解决这个问题需要按名称进行装配，@Autowired 需要搭配 @Qualifier 使用
 *
 * @author zt
 * @since 2023/8/21 19:46
 **/
public interface UserDao {

    void add();
}
