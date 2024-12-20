//package com.github.zack.use.projects.grpc.server;
//
//import io.grpc.Server;
//import io.grpc.ServerBuilder;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.Collection;
//import java.util.concurrent.TimeUnit;
//import java.util.logging.Logger;
//
///**
// * @author zack
// * @date 2021/12/20
// */
//public class RouteGuideServer {
//    private static final Logger logger = Logger.getLogger(RouteGuideServer.class.getName());
//
//    private final int port;
//    private final Server server;
//
//    public RouteGuideServer(int port) throws IOException {
//        this(port, RouteGuideUtil.getDefaultFeaturesFile());
//    }
//
//    public RouteGuideServer(int port, URL featureFile) throws IOException {
//        this(ServerBuilder.forPort(port), port, RouteGuideUtil.parseFeatures(featureFile));
//    }
//
//    public RouteGuideServer(ServerBuilder<?> serverBuilder, int port, Collection<Feature> features) {
//        this.port = port;
//        server = serverBuilder.addService(new RouteGuideService(features))
//                .build();
//    }
//
//    public void start() throws IOException {
//        server.start();
//        logger.info("Server started, listening on " + port);
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
//            System.err.println("*** shutting down gRPC server since JVM is shutting down");
//            try {
//                RouteGuideServer.this.stop();
//            } catch (InterruptedException e) {
//                e.printStackTrace(System.err);
//            }
//            System.err.println("*** server shut down");
//        }));
//    }
//
//    public void stop() throws InterruptedException {
//        if (server != null) {
//            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
//        }
//    }
//
//    private void blockUntilShutdown() throws InterruptedException {
//        if (server != null) {
//            server.awaitTermination();
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        RouteGuideServer server = new RouteGuideServer(8980);
//        server.start();
//        server.blockUntilShutdown();
//    }
//
//
//
//}
