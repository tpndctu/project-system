## Research Apache Kafka 
##### Document:
* Reference: [Link](http://kafka.apache.org/documentation.html)

##### Download Kafka Server:  
* Download: [Link](http://kafka.apache.org/downloads.html)
* Extract file downloaded.

##### Start Zookeeper server
* `cd /path to your/kafka/`
* `./bin/zookeeper-server-start.sh ./config/zookeeper.properties`

##### Start Kafka server
* `cd /path to your/kafka/`
* `./bin/kafka-server-start.sh ./config/server.properties`

##### Topic in Kafka
* Create topic:`bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test`
* List topic: `bin/kafka-topics.sh --list --zookeeper localhost:2181`

##### Example send and recive message with Kafka
* Alter start server zookeeper and kafka
* Producer (send message): `bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test`
* Consumer (recive message): `bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic test --from-beginning`

Thanks,  
Nhan Dinh
