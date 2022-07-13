## sentinel1.8.4
[![OSCS Status](https://www.oscs1024.com/platform/badge/inyuo/sentinel-dashboard-nacos.git.svg?size=small)](https://www.murphysec.com/dr/89QbgiHsgLWQhw8dr6)

### 支持nacos持久化
1. 支持流控规则持久化
2. 支持熔断规则持久化

### 使用方式
1. 打包后自行部署
   mvn clean package
2. 运行sentinel-dashboard.jar,
   需要指定nacos服务地址，nacos.address: 127.0.0.1:8848
   指定nacos命名空间，nacos.namespace: xxx-sentinel
   java -jar -Xms768m -Xmx768m -XX:PermSize=512M -XX:MaxPermSize=512M  -Dfile.encoding=UTF-8  -Dnacos.namespace=appName-sentinel  -Dnacos.address=127.0.0.1:8848 sentinel-dashboard.jar
3. nacos上添加对应的命名空间
4. sentinel是惰性，需要调用接口后才会显示接口链路