package com.academy.hw6;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create your own connection pool.
 * Set the minimum and maximum pool size.
 * The minimum pool size ensures that when the pool is created,
 * a certain number of ready-to-use connections will already be generated.
 * The pool can be dynamically expanded to a certain predefined maximum value.
 * Consider a case where you need to issue a new connection,
 * but the pool can no longer service any more new connections.
 * In this case, new connection allocation requests should be queued and serviced as soon as a free connection appears.
 * Also set a timeout limit for such a request, after which an error will be issued stating that the pool is not available.
 * <p>
 * As part of this task, provide the code that you used to test the connection pool.
 * Use ExecutorService or CompletableFuture to emulate a multi-threaded environment
 */
public class ConnectionPool {

  Queue<Connection> availableConnections;
  List<Connection> usedConnections;
  RequestQueue requestQueue;
  int initConnCnt;
  int maxConnCnt;


  /**
   * The constructor takes as input the initial and max numbers of connections in the pool
   */
  public ConnectionPool(int initConnCnt, int maxConnCnt) {
    this.initConnCnt = initConnCnt;
    this.maxConnCnt = maxConnCnt;
    this.requestQueue = new RequestQueue();
    this.usedConnections = new ArrayList<>();
    this.availableConnections = new ArrayDeque<>();
    for (int i = 0; i < initConnCnt; i++) {
      availableConnections.add(new Connection());
    }
  }

  //the method is designed to allocate a new connection from the pool
  public synchronized Connection retrieve() {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    if (availableConnections.size() == 0) {
      if (usedConnections.size() != maxConnCnt) {
        Connection newConnection = new Connection();
        usedConnections.add(newConnection);
        return newConnection;
      } else {
        return null;
      }
    }
    Connection connection = availableConnections.poll();
    usedConnections.add(connection);
    return connection;
  }

  //the method is intended to return the connection back to the pool
  public synchronized void putback(Connection c) throws NullPointerException {
    if (c != null) {
      availableConnections.add(c);
      usedConnections.remove(c);
    }
    else {
      throw new NullPointerException("Unknown connection");
    }
  }

  public synchronized void run(Request request) {
    ExecutorService executorService = Executors.newFixedThreadPool(20);

  }

  public void addRequestToQueue (Request request) {
    requestQueue.add(request);
  }

  public Request getRequest() {
    return requestQueue.poll();
  }

  public int getCountAvailableConns() {
    return availableConnections.size();
  }

  public int getCountUsedConns() {
    return usedConnections.size();
  }

}
