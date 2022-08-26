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
* 



Schedule

* create schedule
* online schedule
* offline schedule
* delete schedule



DataSource

* create datasource
* update datasource
* list datasource





Resource

* online create file
* update file content
* page file