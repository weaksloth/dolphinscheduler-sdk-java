# dolphinscheduler-sdk-java
the java sdk for operating dolphinscheduler 

# 1 Why do this?
In our company,dolphin scheduler becomes base system for our job.Then we want to operate dolphin scheduler by RPC instead of the web view.
however,dolphin scheduler has not support java sdk,only python sdk.so I do this



# 2 features
##  2.1 multi http client support
you cant use `dolphinscheduler-java-sdk` in 
* springboot project (we use `RestTemplate` for post rest request)
* other java project (we use `http-client` for post rest request)



## 2.2 multi version support

with dolphinscheduler's develop,the rest api maybe change,so we want to support dolphinscheduler 2.0.6 and 3.0 version



## 2.3 supported operations

Process Definition：

* create process definition
* update process definition
* delete process definition
* release process definition

  

Process Instance

* start process instance
* rerun process instance
* get process instance info
* delete process instance



Schedule

* create schedule
* update schedule
* online schedule
* offline schedule
* delete schedule



DataSource

* create datasource
* update datasource
* list datasource
* delete datasource





Resource

* online create file
* update file content
* page file
* delete file


Alert
* create alert plugin
* list alert plugin


# 3 Use guide

**there are many example for operate dolphin scheduler in `test` directory**



## 3.1 install

```shell
git clone https://github.com/weaksloth/dolphinscheduler-sdk-java.git
mvn install -Dmaven.test.skip=true 
```



## 3.2 maven import

in your project,import `dolphinscheduler-sdk-java`

```xml
<dependency>
    <groupId>com.github.weaksloth</groupId>
    <artifactId>dolphinscheduler-sdk-java</artifactId>
    <version>1.0.0</version>
</dependency>
```



## 3.3 create dolphin client

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



## 3.4 operate dolphin scheduler

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
```