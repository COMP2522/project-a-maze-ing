//package org.bcit.com2522.project;
//
//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.ServerApi;
//import com.mongodb.ServerApiVersion;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//import static com.mongodb.client.model.Filters.eq;
//
//public class database {
//  MongoDatabase Database;
//
//  public database() {
//    ConnectionString connectionString = new ConnectionString("mongodb+srv://nelsonph11:bcitmongo@cluster0.kcao0ad.mongodb.net/?retryWrites=true&w=majority");
//    MongoClientSettings settings = MongoClientSettings.builder()
//        .applyConnectionString(connectionString)
//        .serverApi(ServerApi.builder()
//            .version(ServerApiVersion.V1)
//            .build())
//        .build();
//    MongoClient mongoClient = MongoClients.create(settings);
//    MongoDatabase database = mongoClient.getDatabase("test");
//  }
//
//  public void put (String key, String value){
//    Document document = new Document();
//    document.append(key, value);
//    new Thread(() ->database.getCollection("students").insertOne(document)).start();
//
//  }
//
//  public static void main(String[] args){
//    Database d = new Database();
//    d.put("hello", "world");
////  ConnectionString connectionString = new ConnectionString("mongodb+srv://nelsonph11:bcitmongo@cluster0.kcao0ad.mongodb.net/?retryWrites=true&w=majority");
////  MongoClientSettings settings = MongoClientSettings.builder()
////      .applyConnectionString(connectionString)
////      .serverApi(ServerApi.builder()
////          .version(ServerApiVersion.V1)
////          .build())
////      .build();
////  MongoClient mongoClient = MongoClients.create(settings);
////  MongoDatabase database = mongoClient.getDatabase("test");
//
//  //database.createCollection("students");
//
////    Document document = new Document();
////    document.append("name", "Ram");
////    document.append("age", 26);
////    document.append("city", "Hyderabad");
////
////    database.getCollection("students").insertOne(document);
////    Document find = database.getCollection("students").find(eq("name", "Ram")).first();
////    System.out.println(find);
//  }
//}
