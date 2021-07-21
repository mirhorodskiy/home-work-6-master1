package com.academy.hw6;

public class Request {
  private static int requestNumber = 0;
  private String requestTitle;

  public Request() {
    requestTitle = "Request #" + requestNumber;
    requestNumber++;
  }

  public String getRequestTitle() {
    return requestTitle;
  }
}
