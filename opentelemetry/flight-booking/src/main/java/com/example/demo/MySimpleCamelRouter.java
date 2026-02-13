/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.demo;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Random;

@Component
public class MySimpleCamelRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration()
                .contextPath("/camel")
                .bindingMode(RestBindingMode.json);

        rest().get("/bookFlight")
                .to("direct:bookFlight");

        from("direct:bookFlight").routeId("bookFlight-http")
                .log(LoggingLevel.INFO, "New book flight request with trace=${header.traceparent}")
                .process(exchange -> {
                    int random = new Random().nextInt(4320923);
                    if ( random % 7 == 0 ){
                        throw new ConnectException("problem to get the list of the available flights from the datastore");
                    }
                })
                .bean(new AvailableFlights(),"getAvailableFlight")
                .unmarshal().json(JsonLibrary.Jackson);

        // kafka based 
        from("kafka:flight_input").routeId("bookFlight-kafka")
                .log(LoggingLevel.INFO, "New book flight request via Kafka topic")
                .bean(new AvailableFlights(),"getAvailableFlight")
                .process(exchange -> {
                    int random = new Random().nextInt(4320923);
                    if ( random % 7 == 0 ){
                        throw new IOException("problem to sent the result of the available flights to the broker");
                    }
                })
                .to("kafka:flight_output");

    }
}
