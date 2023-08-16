## Servlet

### 编码

tomcat8之前，设置编码：

- get请求方式：

  1. 将字符串打散成字节数组

     byte[] bytes = fname.getBytes("ISO-8859-1");

  2. 将字节数组按照设定的编码重新组装成字符串

     fname = new String(bytes,"UTF-8");

- post请求方式：request.setCharacterEncoding("UTF-8");

tomcat8 开始，设置编码，只需要针对 post 方式

- request.setCharacterEncoding("UTF-8");
- 注意：设置编码(post)这一句代码必须在所有的获取参数动作之前

### 继承关系

javax.servlet.Servlet 接口
	javax.servlet.GenericServlet 抽象类
		javax.servlet.http.HttpServlet 抽象子类

### 相关方法

`javax.servlet.Servlet` 接口

- void init(config) - 初始化方法
- void service(request,response) - 服务方法
- void destory() - 销毁方法

`javax.servlet.GenericServlet` 抽象类

- void service(request,response) - 仍然是抽象的

`javax.servlet.http.HttpServlet` 抽象子类

- void service(request,response) - 不是抽象的
  1. String method = req.getMethod(); 获取请求的方式
  2. 各种if判断，根据请求方式不同，决定去调用不同的do方法
  3. 在 HttpServlet 这个抽象类中，do方法机构类似
     

当有请求过来时，service 方法会自动响应（其实是tomcat容器调用的）。

在 HttpServlet 中我们会去分析请求的方式，区分 get、post、put、delete 等，否则默认会报405错误。

### 生命周期

生命周期对应Servlet中的三个方法：init(), service(), destroy()。

1. 第一次接收请求时，这个 Servlet 会进行实例化(调用构造方法)、初始化(调用init())、然后服务(调用service())
2. 从第二次请求开始，每一次都是服务
3. 当容器关闭时，其中的所有的servlet实例会被销毁，调用销毁方法
4. Servlet实例tomcat只会创建一个，所有的请求都是这个实例去响应。

### Http协议

Http 称之为 超文本传输协议

Http是无状态的

Http请求响应包含两个部分：请求和响应

- 默认情况下，第一次请求时，tomcat才会去实例化，初始化，然后再服务.这样的好处是什么？ 提高系统的启动速度 。 这样的缺点是什么？ 第一次请求时，耗时较长。
    - 因此得出结论： 如果需要提高系统的启动速度，当前默认情况就是这样。如果需要提高响应速度，我们应该设置Servlet的初始化时机。
    4） Servlet的初始化时机：
    - 默认是第一次接收请求时，实例化，初始化
    - 我们可以通过<load-on-startup>来设置servlet启动的先后顺序,数字越小，启动越靠前，最小值0
    5） Servlet在容器中是：单例的、线程不安全的
    - 单例：所有的请求都是同一个实例去响应
    - 线程不安全：一个线程需要根据这个实例中的某个成员变量值去做逻辑判断。但是在中间某个时机，另一个线程改变了这个成员变量的值，从而导致第一个线程的执行路径发生了变化
    - 我们已经知道了servlet是线程不安全的，给我们的启发是： 尽量的不要在servlet中定义成员变量。如果不得不定义成员变量，那么不要去：①不要去修改成员变量的值 ②不要去根据成员变量的值做一些逻辑判断

- 请求，包含三个部分： 1、请求行；2、请求消息头；3、请求主体
    - 请求行包含是三个信息： 1、请求的方式；2、请求的URL；3、请求的协议（一般都是HTTP1.1）。
    - 请求消息头中包含了很多客户端需要告诉服务器的信息，比如：浏览器型号、版本、接收的内容的类型、发送的内容的类型、内容的长度等
    - 请求体
        - get方式，没有请求体，但是有一个queryString
        - post方式，有请求体，form data
        - json格式，有请求体，request payload
- 响应，包含三个部分：1、响应行；2、响应头；3、响应体
    - 响应行包含三个信息：1、协议；2、响应状态码(200)；3、响应状态(ok)。
    - 响应头：包含了服务器的信息；服务器发送给浏览器的信息（内容的媒体类型、编码、内容长度等）。
    - 响应体：响应的实际内容（比如请求add.html页面时，响应的内容就是<html><head><body><form....）。

### 会话

Http是无状态的，因此服务器无法判断这两次请求是同一个客户端发过来的，还是不同的客户端发过来的。

通过会话跟踪技术来解决无状态的问题。

**会话跟踪技术**

1. 客户端第一次发请求给服务器，服务器获取session，获取不到，则创建新的，然后响应给客户端

2. 下次客户端给服务器发请求时，会把sessionID带给服务器，那么服务器就能获取到了，那么服务器就判断这一次请求和上次某次请求是同一个客户端，从而能够区分开客户端

**常用的API**

- request.getSession() -> 获取当前的会话，没有则创建一个新的会话
- request.getSession(true) -> 效果和不带参数相同
- request.getSession(false) -> 获取当前会话，没有则返回null，不会创建新的

**session保存作用域**

  - session 保存作用域是和具体的某一个session对应的
  - 常用的API
    - void session.setAttribute(k,v)
    - Object session.getAttribute(k)
    - void removeAttribute(k)

### 转发与重定向

服务器内部转发：request.getRequestDispatcher("...").forward(request,response);

  - 一次请求响应的过程，对于客户端而言，内部经过了多少次转发，客户端是不知道的

- 地址栏没有变化

客户端重定向：response.sendRedirect("....");

  - 两次请求响应的过程。客户端肯定知道请求URL有变化
  - 地址栏有变化
