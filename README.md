Client-Server Messenger Round Robin
====================================

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
