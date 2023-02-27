package com.cryoport.skytrax;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryoport.skytrax.dto.RequestDTO;
import com.cryoport.skytrax.dto.ResponseDTO;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.function.aws.runtime.AbstractMicronautLambdaRuntime;

import java.net.MalformedURLException;
public class FunctionLambdaRuntime extends AbstractMicronautLambdaRuntime<RequestDTO, RequestDTO, RequestDTO, ResponseDTO>
{
    public static void main(String[] args) {
        try {
            new FunctionLambdaRuntime().run(args);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Nullable
    protected RequestHandler<RequestDTO, ResponseDTO> createRequestHandler(String... args) {
        return new FunctionRequestHandler();
    }
}
