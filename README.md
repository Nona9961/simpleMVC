# 本项目的目的

模仿SpringMVC，重复造轮子（Nona9961版本）

## 主要做的事情
不要web.xml，只有注解方式注册Controller
- [x] 通过tomcat启动，初始化Servlet
- [x] 自定义注解，标记Controller
- [ ] 解析器
- [ ] 自定义拓展
## tomcat
### Java中的SPI机制
SPI(`Service Provider Interface`)是一种Java内置的服务发现机制，大致意思是服务提供方提供一个标准（接口）
具体的实现由调用方实现，并做一些配置即可让服务方调用调用方的实现类。

具体如下：

1. 创建一个接口与实现该接口的实现类
2. 在resources下创建 "META-INF/services"目录，并新建与接口全限定类名同名的文件
3. 文件内容里面写实现类的全限定类名
4. 服务方通过ServiceLoader.load方法加载接口的实现类实例（可以看ServiceLoader的源码）
### tomcat启动的SPI机制
javax.servlet.ServletContainerInitializer就是tomcat启动会用spi调用的接口
## Annotation
### Controller
注册的类根据mapping可以处理httpRequest
### Mapping
声明处理相应url对应的request
### 处理注解
1. 在tomcat启动的时候扫描所有带有@Controller注解的类
2. 将这些类实例化存入一个容器中
3. 根据url来选择处理这个request的方法
#### 扫描注解
* SpringMVC是通过配置web.xml里面扫描器进行扫描包的
* SpringBoot是通过SpringBoot类所在包向下扫描的

这里采取配置文件（但不是web.xml）形式，因为没有启动类