Client-Server Messenger Round Robin
====================================

A client-server implementation of a messenger. If the current server is down, the IP address of an alternative server will be provided in round robin fashion from an available pool of servers.

Platform: Linux

1. Compile source code
```
javac redirectHost.java
javac msgServer.java
javac msgClient.java
```

2. Run redirectHost by typing `java redirectHost`
on owl.cs.umanitoba.ca

3. Run msgServer, one on falcon.cs.umanitoba.ca 
and another on osprey.cs.umanitoba.ca by typing 
`java msgServer`

4. Run msgClient on any other aviary machine,
maybe on swan.cs.umanitoba.ca by typing 
`java msgClient`
