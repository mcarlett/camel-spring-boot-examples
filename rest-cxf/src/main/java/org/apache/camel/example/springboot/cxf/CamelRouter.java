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
package org.apache.camel.example.springboot.cxf;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import org.springframework.stereotype.Component;

/**
 * A simple Camel REST DSL route with OpenApi API documentation.
 */
@Component
public class CamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        UserServiceImpl service = new UserServiceImpl();

        // @formatter:off
        from("cxfrs:/api?" +
                    "resourceClasses=org.apache.camel.example.springboot.cxf.UserService" +
                    "&bindingStyle=SimpleConsumer" +
                    //"&providers=jaxrsProvider" +
                    "&loggingFeatureEnabled=true")
                .to("bean-validator:user?validationProviderResolver=#validationProviderResolver")
                .to("log:camel-cxf-log?showAll=true")
                .setHeader(Exchange.BEAN_METHOD_NAME, simple("${header.operationName}"))
                .bean(service);

        // @formatter:on
    }

}
