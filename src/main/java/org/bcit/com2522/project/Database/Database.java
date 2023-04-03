package org.bcit.com2522.project.Database;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bcit.com2522.project.labyrinth.LabyrinthManager;
import org.bcit.com2522.project.labyrinth.Tiles.TileType;
import org.bson.Document;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;


public class Database {

  private static Database instance;
  private MongoDatabase database;
  private MongoClient client;
  private Database() {
    ConnectionString connectionString = new ConnectionString("mongodb+srv://maze:1234@a-maze-ing.mu66nzj.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .serverApi(ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build())
        .build();
    client = MongoClients.create(settings);
    database = client.getDatabase("test");

  }

  public static Database getInstance(){
    if(instance == null) {
      instance = new Database();
    }
    return instance;
  }


  /**
   * Saves the current labyrinth's tile layout and given name into the database.
   */
  public void saveCurrent(String name) {
    Document doc = new Document();
    TileType[][] layout = LabyrinthManager.getInstance().getTileList();

    doc.put("name", name);

    BasicDBList tileList = new BasicDBList();
    BasicDBList row;
    for (TileType[] tileTypes : layout) {
      row = new BasicDBList();
      row.addAll(Arrays.asList(tileTypes).subList(0, layout[0].length));
      tileList.add(row);
    }
    doc.put("tiles", tileList);

    database.getCollection("labyrinths").insertOne(doc);
  }

  /**
   * loads the first labyrinth found in the database with the given name.
   */
  public void loadLabyrinth(String name) {
    Document target = database.getCollection("labyrinths").find(
        eq("name", name)).first();
    LabyrinthManager.getInstance().loadLabyrinth(target);
  }

  public FindIterable<Document> loadAll() {
    FindIterable<Document> labyrinths = database.getCollection("labyrinths").find();
    return labyrinths;
  }


}
