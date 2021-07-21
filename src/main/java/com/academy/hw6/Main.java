package com.academy.hw6;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
  public static void main(String[] args) {
    ConnectionPool connectionPool = new ConnectionPool(2,10);
    ExecutorService executorService = Executors.newFixedThreadPool(20);
    boolean flag = true;

    for (int i = 0; i < 200; i++) {
      AtomicReference<Request> request = new AtomicReference<>(new Request());
      executorService.execute(() -> {
        Connection connection = connectionPool.retrieve();
        if (connection!=null) {
          Request request1 = connectionPool.getRequest();
          if (request1 != null) {
            connectionPool.addRequestToQueue(request.get());
            request.set(request1);
          }
          System.out.println(request.get().getRequestTitle() + " was processed by " + connection.getConnectionName());

          connectionPool.putback(connection);
        }
        else
        {
          connectionPool.addRequestToQueue(request.get());
          System.out.println("No available connections -> " + request.get().getRequestTitle() + " was added to the Queue");
        }
      });
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    executorService.shutdown();
  }
}
