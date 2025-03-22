package org.distributed.stockserver.service;

import io.grpc.stub.StreamObserver;
import org.distributed.StockRequest;
import org.distributed.StockResponse;
import org.distributed.StockTradingServiceGrpc;
import org.distributed.stockserver.entities.Stock;
import org.distributed.stockserver.repository.StockRepository;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class StockService extends StockTradingServiceGrpc.StockTradingServiceImplBase {

    private final StockRepository repository;

    public StockService(StockRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getStockPrice(StockRequest request, StreamObserver<StockResponse> responseObserver) {
        Stock stockEntity = repository.findByStockSymbol(request.getStockSymbol());
        StockResponse stockResponse = StockResponse.newBuilder()
                .setStockSymbol(stockEntity.getStockSymbol())
                .setPrice(stockEntity.getPrice())
                .setTimestamp(stockEntity.getLastUpdated().toString())
                .build();

        responseObserver.onNext(stockResponse);
        responseObserver.onCompleted();
    }
}
