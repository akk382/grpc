package org.distributed.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.distributed.StockRequest;
import org.distributed.StockResponse;
import org.distributed.StockTradingServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class StockClientService {

    @GrpcClient("stockService")
    private StockTradingServiceGrpc.StockTradingServiceBlockingStub serviceBlockingStub;

    public StockResponse getStockPrice(String stockSymbol) {
        StockRequest request = StockRequest.newBuilder()
                .setStockSymbol(stockSymbol).build();
        return serviceBlockingStub.getStockPrice(request);
    }

}
