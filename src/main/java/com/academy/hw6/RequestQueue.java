package com.academy.hw6;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.TimerTask;

public class RequestQueue {
  private Queue<Request> requestQueue;

  public RequestQueue() {
    requestQueue = new ArrayDeque<Request>();

  }


  public Request poll() {
    return requestQueue.poll();
  }

  public void add(Request request) {
    requestQueue.add(request);
  }
  public void clearQueue() {
    while (!requestQueue.isEmpty()) {
      System.out.println(requestQueue.poll() + "was not proceed");
    }
  }

  public boolean isEmpty() {
    return requestQueue.isEmpty();
  }

}
