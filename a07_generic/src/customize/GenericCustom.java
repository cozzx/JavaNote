package customize;

import java.util.*;

/**
 * 自定义泛型类
 *
 * @author zt
 * @since 2023/7/5 20:15
 **/
public class GenericCustom<T> {

    private Map<String, T> map;

    {
        map = new HashMap<String, T>();
    }

    // 保存 T 类型的对象到 Map 成员变量中
    public void save(String id, T entity) {
        if (!map.containsKey(id)) {
            map.put(id, entity);
        }
    }

    // 从 map 中获取 id 对应的对象
    public T get(String id) {
        return map.get(id);
    }

    // 替换 map 中 key 为 id 的内容,改为 entity 对象
    public void update(String id, T entity) {
        if (map.containsKey(id)) {
            map.put(id, entity);
        }
    }

    // 返回 map 中存放的所有 T 对象
    public List<T> list() {
        Collection<T> values = map.values();
        return new ArrayList<>(values);
    }

    // 删除指定 id 对象
    public void delete(String id) {
        map.remove(id);
    }
}
