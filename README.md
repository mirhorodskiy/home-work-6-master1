Create your own connection pool.
Set the minimum and maximum pool size.
The minimum pool size ensures that when the pool is created,
a certain number of ready-to-use connections will already be generated.
The pool can be dynamically expanded to a certain predefined maximum value.
Consider a case where you need to issue a new connection,
but the pool can no longer service any more new connections.
In this case, new connection allocation requests should be queued and serviced as soon as a free connection appears.
Also set a timeout limit for such a request, after which an error will be issued stating that the pool is not available.

As part of this task, provide the code that you used to test the connection pool. 
Use ExecutorService or CompletableFuture to emulate a multi-threaded environment