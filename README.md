# zookeeper-example


Start a zookeeper cluster
```shell
magnus@Magnuss-MBP-3> etc/bin/allZkServer.sh start                                                                                             ~/dev/labb/zookeeper-samples
Using config: /Users/magnus/dev/labb/zookeeper-samples/etc/instance-1/conf/zoo.cfg
Starting zookeeper ... STARTED
Using config: /Users/magnus/dev/labb/zookeeper-samples/etc/instance-2/conf/zoo.cfg
Starting zookeeper ... STARTED
Using config: /Users/magnus/dev/labb/zookeeper-samples/etc/instance-3/conf/zoo.cfg
Starting zookeeper ... STARTED
magnus@Magnuss-MBP-3>          
```

Currently you need to build this with intellij to create the target folder with all java classes. Then you can either start direct from intellij or using the shell script.
```shell
magnus@Magnuss-MBP-3> etc/bin/runClient.sh                                                                                                     ~/dev/labb/zookeeper-samples
INFO - Environment.logEnv(100)  | Client environment:zookeeper.version=3.4.5-1392090, built on 09/30/2012 17:52 GMT
INFO - Environment.logEnv(100)  | Client environment:host.name=magnuss-mbp-3
INFO - Environment.logEnv(100)  | Client environment:java.version=1.7.0_79
INFO - Environment.logEnv(100)  | Client environment:java.vendor=Oracle Corporation
INFO - Environment.logEnv(100)  | Client environment:java.home=/Library/Java/JavaVirtualMachines/jdk1.7.0_79.jdk/Contents/Home/jre
INFO - Environment.logEnv(100)  | Client environment:java.class.path=etc/bin/../../target/classes:etc/bin/../examples-libs/curator-client-2.11.1.jar:etc/bin/../examples-libs/curator-framework-2.11.1.jar:etc/bin/../examples-libs/curator-recipes-2.11.1.jar:etc/bin/../examples-libs/guava-16.0.1.jar:etc/bin/../examples-libs/log4j-1.2.17.jar:etc/bin/../examples-libs/slf4j-api-1.6.1.jar:etc/bin/../examples-libs/slf4j-log4j12-1.6.1.jar:etc/bin/../examples-libs/zookeeper-3.4.5.jar
INFO - Environment.logEnv(100)  | Client environment:java.library.path=/Users/magnus/Library/Java/Extensions:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java:.
INFO - Environment.logEnv(100)  | Client environment:java.io.tmpdir=/var/folders/gb/jjyds0sx27s143wwc6vpwm0m0000gn/T/
INFO - Environment.logEnv(100)  | Client environment:java.compiler=<NA>
INFO - Environment.logEnv(100)  | Client environment:os.name=Mac OS X
INFO - Environment.logEnv(100)  | Client environment:os.arch=x86_64
INFO - Environment.logEnv(100)  | Client environment:os.version=10.12.3
INFO - Environment.logEnv(100)  | Client environment:user.name=magnus
INFO - Environment.logEnv(100)  | Client environment:user.home=/Users/magnus
INFO - Environment.logEnv(100)  | Client environment:user.dir=/Users/magnus/dev/labb/zookeeper-samples
INFO - ZooKeeper.<init>(438)  | Initiating client connection, connectString=localhost:2181,localhost:2182,localhost:2183 sessionTimeout=10000 watcher=org.apache.curator.ConnectionState@76a11b29
Press enter/return to quit

INFO - LeaderSelectorClient.stateChanged(45)  | New state: CONNECTED
INFO - LeaderSelectorClient.takeLeadership(78)  | Client #25 is now the leader
DEBUG - LeaderSelectorClient.takeLeadership(83)  | Client #25 still leader. 
```
