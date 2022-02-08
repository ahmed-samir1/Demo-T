package Mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class mogo_1 {


    public static class InsertingDocument {
        public static void main(String args[]) {


            MongoClient mongo = new MongoClient("localhost", 27017);

            MongoDatabase database = mongo.getDatabase("myDatabase");
            // database.createCollection("customer1");
//Write a Script with any preferable programming language that Inserts the Following document

            Document document = new Document();
//          document.append("accountId", "gdx541");
//          document.append("totalAmount", 5000);
//          document.append("onHoldAmount", 1000);
            MongoCollection<Document> coll = database.getCollection("customer1");
            coll.insertOne(document);

            System.out.println(document + "Document inserted successfully");
             //After Inserting the document Retrieve the document by “accountId”

            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("accountId", "gdx540");
            MongoCursor<Document> cursor = coll.find(searchQuery).iterator();
            while (cursor.hasNext()) {
                Document x = cursor.next();
                System.out.println(x);
                //Calculate the available balance after deducting the onHoldAmount
                System.out.println(x.getInteger("totalAmount") - x.getInteger("onHoldAmount"));


            }
        }

    }
}

