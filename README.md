### Research Spring Framework, Hadoop.
**Use technology:**

1. Spring boot
2. Spring XD
3. Spring data with Hadoop
4. Spring Integration Kafka  
5. Java 8

#### JDK 8
* Download JDK 8 on windows: [Link](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Install JDK 8 on Linux:
   * `sudo apt-get update`
   * `sudo apt-get install oracle-java8-installer`
* Change java version:
   * `sudo update-alternatives --config java`
   * Chose java version:  
   ```
   There are 2 choices for the alternative java (providing /usr/bin/java).
   Selection    Path                                            Priority   Status
   ------------------------------------------------------------
  * 0            /usr/lib/jvm/java-8-oracle/jre/bin/java          1072      auto mode
    1            /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/java   1071      manual mode
    2            /usr/lib/jvm/java-8-oracle/jre/bin/java          1072      manual mode
   Press enter to keep the current choice[*], or type selection number:
```
 * You can now choose the number to use as default. This can also be done for the Java compiler (javac):
   * `sudo update-alternatives --config javac`
 * Setting the "JAVA_HOME" environment variable
   * `sudo update-alternatives --config java`
   ```
There are 2 choices for the alternative java (providing /usr/bin/java).

  Selection    Path                                            Priority   Status
------------------------------------------------------------
* 0            /usr/lib/jvm/java-8-oracle/jre/bin/java          1072      auto mode
  1            /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/java   1071      manual mode
  2            /usr/lib/jvm/java-8-oracle/jre/bin/java          1072      manual mode

Press enter to keep the current choice[*], or type selection number:
   ```
   * The path of the installation is for each:
      1. `/usr/lib/jvm/java-8-oracle/`
      2. `/usr/lib/jvm/java-7-openjdk-amd64/`
   * Copy the path from your preferred installation and then edit the file /etc/environment:  
     `sudo nano /etc/environment`
   * In this file, add the following line (replacing YOUR_PATH by the just copied path):  
     `JAVA_HOME="YOUR_PATH"`
   * That should be enough to set the environment variable. Now reload this file:  
     `source /etc/environment`
   * Test it by executing:  
     `echo $JAVA_HOME`
* Reference: [Link](https://www.digitalocean.com/community/tutorials/how-to-install-java-on-ubuntu-with-apt-get)

#### Eclipse Tool
* Download eclipse: [Download](https://eclipse.org/downloads/)  

#### Maven  
1. **Maven install**

* Install maven on linux (Ubuntu):
     * Type command line: `sudo apt-get install maven`
     * Set proxy with maven:
          * Add file settings.xml into directory `~/.m2/`
          * Content file setting.xml: [reference](http://www.mkyong.com/maven/how-to-enable-proxy-setting-in-maven/)
      ```xml
          <proxies>
            <proxy>
              <id>optional</id>
              <active>true</active>
              <protocol>http</protocol>
              <username>dhnhan</</username>
              <password>123456</password>
              <host>proxy.tma.com</host>
              <port>8080</port>
              <nonProxyHosts>127.0.0.1</nonProxyHosts>
            </proxy>
          </proxies>
      ```
* Install maven on Windows:
  * Download maven with [Maven download](https://maven.apache.org/download.cgi)
  * Set variable enviroment for maven: [Link](http://www.mkyong.com/maven/how-to-install-maven-in-windows/)
  * Set proxy with maven:
     * Add file settings.xml: `${user.home}/.m2/settings.xml`  
     * Content file setting.xml:  
      ```xml
          <proxies>
            <proxy>
              <id>optional</id>
              <active>true</active>
              <protocol>http</protocol>
              <username>dhnhan</</username>
              <password>123456</password>
              <host>proxy.tma.com</host>
              <port>8080</port>
              <nonProxyHosts>127.0.0.1</nonProxyHosts>
            </proxy>
          </proxies>
      ```
2. **Create project maven**
    * [Reference](http://www.mkyong.com/maven/how-to-create-a-java-project-with-maven/) or [Home maven](https://maven.apache.org/guides/getting-started/)


Thanks,  
Nhan Dinh

