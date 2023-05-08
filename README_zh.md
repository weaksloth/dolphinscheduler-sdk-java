😄[中文文档](README_zh.md)
😃[英文文档](README.md)

# 1 为什么做这个

在我们的场景中，dolphin scheduler已经成为我们作业的底层系统，几乎所有需要调度的任务都跑在dolphin scheduler上。

此外会有很多的第三方系统去对接dolphin scheduler，但是dolphin官方没有java sdk，如果用swagger去生成会非常难用，所以我们很需要一个公共的sdk供三方系统进行使用



# 2 功能特点

## 2.1 方便使用

你可以像使用页面那样的流程去操作定义dolphin scheduler上的资源，不过不同的是，你是通过代码的方式去实现。

## 2.2 多版本支持

随着dolphin scheduler的不断发展，rest api很可能会发生变化，所以我目前支持了dolphin scheduler`2.0.5`和`3.1.4`版本。如果你使用的是其他版本，可以考虑自行做修改，一般来说改动量不大

* `2.0.5-release` in branch `2.0.5-release`
* `3.0.4-release` in branch `3.0.4-release`

## 2.3 支持的操作

项目相关:

* create project
* update project
* list project
* delete project



工作流相关：

* create process definition

* update process definition

* delete process definition

* release(online/offline) process definition

  

工作流实例相关：

* start process instance
* rerun process instance
* get process instance info
* delete process instance
* list process instance



定时相关：

* create schedule
* update schedule
* online schedule
* offline schedule
* delete schedule
* get schedule by workflow code



数据源相关：

* create datasource
* update datasource
* list datasource
* delete datasource



资源相关：

* online create file
* update file content
* page file
* delete file




告警相关：
* create alert plugin
* list alert plugin



# 3 使用指南

## 3.1 编译安装

```shell
git clone https://github.com/weaksloth/dolphinscheduler-sdk-java.git
mvn install -Dmaven.test.skip=true 
```



## 3.2 导入依赖

在你的程序中，引入 `dolphinscheduler-sdk-java` 依赖

```xml
<dependency>
    <groupId>com.github.weaksloth</groupId>
    <artifactId>dolphinscheduler-sdk-java</artifactId>
    <version>2.0.5-RELEASE</version>
</dependency>
```



## 3.3 创建dolphin client

在使用dolphin client之前，请先准备好这些参数

| parameters     | comment                                                      |
| -------------- | ------------------------------------------------------------ |
| token          | dolphin scheduler token, 可以在web ui创建                    |
| dolphinAddress | dolphin scheduler入口,例如:`http://localhost:12345/dolphinscheduler` |



`springboot` project:

```java
@Configuration
public class BeanConfig {
    @Bean
    public DolphinClient dolphinClient() {
        String token = "xxxx";	// dolphin scheduler token
        String dolphinAddress = "http://localhost:12345/dolphinscheduler";  // dolphin scheduler address
        DolphinsRestTemplate restTemplate =
              new DolphinsRestTemplate(
                  new DefaultHttpClientRequest(
                      HttpClients.custom()
                          .addInterceptorLast(new RequestContent(true))
                          .setDefaultRequestConfig(RequestConfig.custom().build())
                          .build(),
                      RequestConfig.custom().build()));	
        
        return new DolphinClient(token,dolphinAddress,restTemplate);
    }
} 

// then you can use by  @Autowired private DolphinClient dolphinClient;
```



`java` project:

```java
String token = "xxxx";	// dolphin scheduler token
String dolphinAddress = "http://localhost:12345/dolphinscheduler";  // dolphin scheduler address
DolphinsRestTemplate restTemplate =
      new DolphinsRestTemplate(
          new DefaultHttpClientRequest(
              HttpClients.custom()
                  .addInterceptorLast(new RequestContent(true))
                  .setDefaultRequestConfig(RequestConfig.custom().build())
                  .build(),
              RequestConfig.custom().build()));	

DolphinClient dolphinClient = new DolphinClient(token,dolphinAddress,restTemplate);
```



## 3.4 操作dolphin scheduler

> 在test目录里有很多的测试代码可以提供参考

operation project:

```java
dolphinClient.opsForProject().create(...);
dolphinClient.opsForProject().update(...);
dolphinClient.opsForProject().page(...);
dolphinClient.opsForProject().delete(...);
```



operate workflow(process)

```java
dolphinClient.opsForProcess().create(...);
dolphinClient.opsForProcess().update(...);
dolphinClient.opsForProcess().delete(...);
dolphinClient.opsForProcess().release(...);
dolphinClient.opsForProcess().generateTaskCode(...);
```



operate workflow instance:

```java
dolphinClient.opsForProcessInst().start(...);
dolphinClient.opsForProcessInst().page(...);
dolphinClient.opsForProcessInst().reRun(...);
dolphinClient.opsForProcessInst().delete(...);
```



operate datasource:

```java
dolphinClient.opsForDataSource().create(...);
dolphinClient.opsForDataSource().update(...);
dolphinClient.opsForDataSource().delete(...);
dolphinClient.opsForDataSource().list(...);
```



operate resource:

```java
dolphinClient.opsForResource().page(...);
dolphinClient.opsForResource().onlineCreate(...);
dolphinClient.opsForResource().onlineUpdate(...);
dolphinClient.opsForResource().delete(...);
```



operate scheduler

```java
dolphinClient.opsForSchedule().create(...);
dolphinClient.opsForSchedule().update(...);
dolphinClient.opsForSchedule().online(...);
dolphinClient.opsForSchedule().offline(...);
dolphinClient.opsForSchedule().delete(...);
dolphinClient.opsForSchedule().getByWorkflowCode(...);
```