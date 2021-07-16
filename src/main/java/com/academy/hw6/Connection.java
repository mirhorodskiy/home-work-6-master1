package com.academy.hw6;

import java.util.Objects;

public class Connection {
  private final String connectionName;

  public Connection(String connectionName) {
    this.connectionName = connectionName;
  }

  public String getConnectionName() {
    return connectionName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Connection that = (Connection) o;
    return Objects.equals(connectionName, that.connectionName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(connectionName);
  }

  @Override
  public String toString() {
    return "Connection{" +
        "connectionName='" + connectionName + '\'' +
        '}';
  }
}
