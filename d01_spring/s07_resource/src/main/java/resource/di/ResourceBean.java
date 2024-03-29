package resource.di;

import org.springframework.core.io.Resource;

/**
 * 依赖注入类，定义属性和方法
 *
 * @author zt
 * @date 2023/8/22
 **/
public class ResourceBean {

    private Resource resource;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }

    public void parse() {
        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());
    }
}
