package com.circuitbakery.learnwith;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    JsonObject mySQLClientConfig = new JsonObject()
      .put("username", "root")
      .put("password", "example")
      .put("database", "mysql")
      .put("host", "mysql");
    SQLClient mySQLClient = MySQLClient.createShared(vertx, mySQLClientConfig);

    vertx.createHttpServer().requestHandler(req -> {

      req.response()
        .putHeader("content-type", "text/plain");


      mySQLClient.getConnection(res -> {
        if (res.succeeded()) {

          SQLConnection connection = res.result();

        req.response().end("Hello from Vert.x! Connected");

        } else {
          // Failed to get connection - deal with it
          req.response().end("Failed to connect!");
        }
      });

    }).listen(8888, http -> {
      if (http.succeeded()) {
        startFuture.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startFuture.fail(http.cause());
      }
    });
  }

}
