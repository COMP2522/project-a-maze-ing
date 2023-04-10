package org.bcit.com2522.project.Database;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bcit.com2522.project.labyrinth.LabyrinthManager;
import org.bcit.com2522.project.labyrinth.Tiles.TileType;
import org.bson.Document;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;

/**
 * The Database class represents a MongoDB database used to store labyrinths.
 * This class is a singleton, meaning that only one instance of the class is allowed to exist at any given time.
 * The Database instance is responsible for initializing the MongoDB client, connecting to the "test" database,
 * and providing methods for saving and loading labyrinths to/from the database.
 * @author Matthew Siggs
 * @author Laurie Solkoski
 * @version 1.0
 */
public class Database {

  /**
   * Private static field for the singleton instance of the Database class.
   */
  private static Database instance;

  /**
   * Private field for the MongoDB database associated with the Database instance.
   */
  private MongoDatabase database;

  /**
   * Private field for the MongoDB client associated with the Database instance.
   */
  private MongoClient client;

  /**
   * Private constructor for the Database class.
   * Initializes the MongoDB client and connects to the "test" database.
   * This constructor should only be called once, when the singleton instance is created.
   */
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

  /**
   * Returns the singleton instance of the Database class.
   * @return the singleton instance of the Database class
   */
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

  /**
   * Returns all documents in the "labyrinths" collection.
   * @return a {@code FindIterable<Document>} containing all documents in the "labyrinths" collection
   */
  public FindIterable<Document> loadAll() {
    FindIterable<Document> labyrinths = database.getCollection("labyrinths").find();
    return labyrinths;
  }


}