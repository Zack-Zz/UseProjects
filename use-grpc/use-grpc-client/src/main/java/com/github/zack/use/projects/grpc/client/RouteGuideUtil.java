//package com.github.zack.use.projects.grpc.client;
//
//import com.google.protobuf.util.JsonFormat;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.net.URL;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//
///**
// * Common utilities for the RouteGuide demo.
// */
//public class RouteGuideUtil {
//    private static final double COORD_FACTOR = 1e7;
//
//    /**
//     * Gets the latitude for the given point.
//     */
//    public static double getLatitude(Point location) {
//        return location.getLatitude() / COORD_FACTOR;
//    }
//
//    /**
//     * Gets the longitude for the given point.
//     */
//    public static double getLongitude(Point location) {
//        return location.getLongitude() / COORD_FACTOR;
//    }
//
//    public static URL getDefaultFeaturesFile() {
//        return RouteGuideClient.class.getResource("route_guide_db.json");
//    }
//
//    /**
//     * Parses the JSON input file containing the list of features.
//     */
//    public static List<Feature> parseFeatures(URL file) throws IOException {
//        InputStream input = file.openStream();
//        try {
//            Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
//            try {
//                FeatureDatabase.Builder database = FeatureDatabase.newBuilder();
//                JsonFormat.parser().merge(reader, database);
//                return database.getFeatureList();
//            } finally {
//                reader.close();
//            }
//        } finally {
//            input.close();
//        }
//    }
//
//    /**
//     * Indicates whether the given feature exists (i.e. has a valid name).
//     */
//    public static boolean exists(Feature feature) {
//        return feature != null && !feature.getName().isEmpty();
//    }
//}
