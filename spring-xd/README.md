### Research Spring XD with Hadoop
* **Document:** [Link](http://docs.spring.io/spring-xd/docs/current/reference/html/) 
* **Download:** [Link](http://repo.spring.io/libs-release/org/springframework/xd/spring-xd/1.2.1.RELEASE/spring-xd-1.2.1.RELEASE-dist.zip)
* **Install Spring XD**:
  * Alter downloaded `spring-xd-1.2.1.RELEASE-dist.zip`
  * Extract file `spring-xd-1.2.1.RELEASE-dist.zip`
* **Start Spring XD**
  * `cd [Path]\spring-xd-1.2.1.RELEASE-dist\xd\bin\`
  * `xd-singlenode.bat`
* **Open new Command Prompt**
  * `cd [Path]\spring-xd-1.2.1.RELEASE-dist\shell\bin\`
  * `xd-shell.bat`
* **Exmple Spring XD with stream**
  * Describe: create stream with Spring XD when Kafka producer send message to Kafka sever, then store message to HDFS
  * Start Zookeeper and Kafka Server: [Reference](https://github.com/huunhancit/project-system/blob/master/kafka/README.md)
  * Shell XD:
    * Config Hadoop with Spring XD: `hadoop config fs hdfs://[IP or domain]:8020`
    * Create stream with shell xd or web ui (`localhost:9393`):
    
    ``` 
stream create filedemo --definition "kafka --zkconnect=localhost:2181 --topic=mytopic --outputType=text/plain | hdfs --directory=/tmp/dhnhan/ --fileName=data --idleTimeout=10000" --deploy
    ```
(Note: mytopic exits in Kafka server, alter 10 senconds write file to HDFS).
    * Check data on HDFS: `hadoop fs cat /tmp/dhnhan/data.txt`
* **Use Taps and label in Spring XD**
```
----------          -------------            -------------           ----------
| Source |     -->  | Processor1 |     -->   | Processor2 |    -->   | Sink 1 |
----------          -------------            -------------           ----------
                         |
                         |
                         |       --------------          ---------
                         ----->  | Processor3 |    -->   | Sink2 |
                                 --------------          ---------
 Source : HTTP
 Processor1: f1:filter --expression=payload.contains('a') (Spark Aggregate)
 Processor2: f2:filter --expression=payload=='NoAlarm' (Spark div alarm)
 Processor3: f3:filter --expression='Alarm' (Spark Alarm)
 Sink1: Log (HDFS)
 Sink2: File (Kafka)
```
 * XD Shell:
  * Create stream noalrm:
  ```
   stream create --name noalarm --definition "http | f1: filter --expression=payload.contains('a') | f2: filter --expression=payload== 'NoAlarm' | log" --deploy
  ```
  * Create stream Alarm:
  ```
   stream create --name alarm --definition "tap:stream:noalarm.f1 > f3: filter --expression=payload=='Alarm' | file --dir=E:\\" --deploy
  ```
  * Test stream:
  ```
   http post --data="NoAlarm"
   http post --data="Alarm"
  ```
  * Check file in E:\alarm.out
  ```
  Alarm
  ```
  * Check Log in xd  singlestanalone
  ```
   NoAlarm
  ```
