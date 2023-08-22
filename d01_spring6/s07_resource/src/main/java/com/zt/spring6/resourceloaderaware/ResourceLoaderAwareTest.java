package com.zt.spring6.resourceloaderaware;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

/**
 * @author zt
 * @since 2023/8/22 19:07
 **/
public class ResourceLoaderAwareTest implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    /**
     * 实现 ResourceLoaderAware接口必须实现的方法
     * 如果把该 Bean 部署在 Spring 容器中，该方法将会有 Spring 容器负责调用。
     * Spring 容器调用该方法时，Spring 会将自身作为参数传给该方法。
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }
}
