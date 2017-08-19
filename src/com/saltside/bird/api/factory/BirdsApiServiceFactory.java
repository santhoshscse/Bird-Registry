package com.saltside.bird.api.factory;

import com.saltside.bird.api.BirdsApiService;
import com.saltside.bird.api.impl.BirdsApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-08-19T05:47:47.983Z")
public class BirdsApiServiceFactory {
    private final static BirdsApiService service = new BirdsApiServiceImpl();

    public static BirdsApiService getBirdsApi() {
        return service;
    }
}
