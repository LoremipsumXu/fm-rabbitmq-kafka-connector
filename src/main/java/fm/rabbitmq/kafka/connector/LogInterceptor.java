package fm.rabbitmq.kafka.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class LogInterceptor implements ServerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers,
                                                                 ServerCallHandler<ReqT, RespT> next) {
        System.out.println(call.getMethodDescriptor().getFullMethodName());
        logger.info(call.getMethodDescriptor().getFullMethodName());
        return next.startCall(call, headers);
    }
}
