# SpringCloud 功能测试

## 模块结构

- sc-api-common 模块通用服务
- sc-eureka-7001 eureka 注册中心, fork 7002 测试 eureka 集群
- sc-eureka-provider8001 注册到 eureka 的服务者, fork 8002 测试 provider 集群
- sc-eureka-consumer8101 注册到 eureka 的消费者
- sc-consul-provider8003 注册到 consul 的服务者, fork 8004 测试 provider 集群
- sc-consul-consumer8102 注册到 consul 的消费者, 测试 RestTemplate / feign 调用, 测试 Loadbalancer 负载均衡
- sc-consul-consumer8103 注册到 consul 的消费者, 测试 resilience4j 熔断降级/舱壁隔离/服务限流, 测试 Micrometer + zipkin 链路追踪, 测试 gateway 网关
- sc-gateway-9527 网关
- sc-nacos-provider9001 nacos注册中心

### 注册中心

consul 本地持久化

启动命令: consul agent -server -ui -bind=127.0.0.1 -client=0.0.0.0 -bootstrap-expect  1  -data-dir /Users/zhangtao/Code/Java/JavaNote/conf/consul >> /Users/zhangtao/Code/Java/JavaNote/log/consul.log &

### 远程调用和负载均衡

openfeign

- sc-api-common 在通用模块设置远程调用接口
- sc-provider-payment8003/sc-provider-payment8004 为业务服务端, 通过 consul 注册服务和配置服务
- sc-consumer-order8103 为业务客户端, 使用 openfeign 测试 远程调用和负载均衡

openfeign 超时配置

默认超时 60s, sc-consumer-order8103 业务客户端 application.yml 中配置超时时间

openfeign 重试机制

默认重试是关闭的, 通过 sc-consumer-order8103 业务客户端的配置类 FeignConfig Retryer 配置重试机制

openfeign 默认 HttpClient 修改

OpenFeign 中 http client 默认使用JDK自带的 HttpURLConnection 发送请求，没有连接池、性能和效率比较低，采用 Apache 的 HttpClient5 替代。

通过 sc-consumer-order8103 业务客户端 application.yml 中配置 hc5 的开启

openfeign 压缩机制

支持对请求和响应进行GZIP压缩，以减少通信过程中的性能损耗。

通过 sc-consumer-order8103 业务客户端 application.yml 中配置

openfeign 日志打印

对 Feign 接口的调用情况进行监控和输出

默认不显示任何日志,
通过 sc-consumer-order8103 业务客户端的配置类 FeignConfig Logger.Level 配置显示的日志级别,
通过 sc-consumer-order8103 业务客户端 application.yml 中配置需要开启 feign 日志的客户端

openfeign 负载均衡策略

通过 sc-consumer-order8103 业务客户端的配置类 LoadBalancerConfig 来实现

### 服务熔断/降级/限流

resilience4j (恢复/复原/弹性) 实现 circuit breaker (断路器) 接口规范

通过 sc-consumer-order8104 业务客户端 application.yml 中配置 resilience4j 配置项, 
配合 OrderController 中的 @CircuitBreaker/@Bulkhead/@RateLimiter 注解来实现 服务熔断/降级, 服务隔离, 服务限流

### 分布式链路追踪

sleuth -> Micrometer(数据收集) + zipkin(数据展示)

引入依赖

- micrometer-tracing-bom 导入链路追踪版本中心，体系化说明
- micrometer-tracing 指标追踪
- micrometer-tracing-bridge-brave 一个Micrometer模块，用于与分布式跟踪工具 Brave 集成，以收集应用程序的分布式跟踪数据。Brave是一个开源的分布式跟踪工具，它可以帮助用户在分布式系统中跟踪请求的流转，它使用一种称为"跟踪上下文"的机制，将请求的跟踪信息存储在请求的头部，然后将请求传递给下一个服务。在整个请求链中，Brave会将每个服务处理请求的时间和其他信息存储到跟踪数据中，以便用户可以了解整个请求的路径和性能。
- micrometer-observation 一个基于度量库 Micrometer的观测模块，用于收集应用程序的度量数据。
- feign-micrometer 一个Feign HTTP客户端的Micrometer模块，用于收集客户端请求的度量数据。
- zipkin-reporter-brave 一个用于将 Brave 跟踪数据报告到Zipkin 跟踪系统的库。

通过 sc-consumer-order8105 业务客户端 application.yml 中配置 management zipkin 和 tracing 配置项

### 网关 

sc-gateway-9527

### springcloud alibaba 

sc-provider-payment9001
sc-consumer-order9101