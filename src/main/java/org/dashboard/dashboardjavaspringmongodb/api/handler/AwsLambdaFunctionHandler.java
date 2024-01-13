package org.dashboard.dashboardjavaspringmongodb.api.handler;

import org.dashboard.dashboardjavaspringmongodb.DashboardJavaSpringMongodbApplication;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AwsLambdaFunctionHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {
    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    public AwsLambdaFunctionHandler() {
        try {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(DashboardJavaSpringMongodbApplication.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public AwsProxyResponse handleRequest(AwsProxyRequest input, Context context) {
        return handler.proxy(input, context);
    }
}