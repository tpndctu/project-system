### Research Apache Spark

##### Document
* Reference: [Link](http://spark.apache.org/docs/latest/index.html)

##### Download: [Link](http://spark.apache.org/downloads.html)

##### Config variable environment: 
* `export PATH=${PATH}:/path to your spark/bin`
* add lines at the bottom of the file: `~/.bashrc`
  * `export PATH=${PATH}:/path to your spark/bin`
* `source ~/.bashrc`

##### Run application with Apache Spark:
* `cd [project-name]/`
* `mvn clean package`
* `spark-submit --class "spark.SimpleApp" target\spark-0.0.1-SNAPSHOT.jar`

Thanks,  
Nhan Dinh

