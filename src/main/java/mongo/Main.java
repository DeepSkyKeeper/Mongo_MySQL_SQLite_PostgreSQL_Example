package mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        try (var mongoClient = MongoClients.create()) {
            // statements
            /**
             * Список баз данных
             */
//            getDbList(mongoClient);
            var database = mongoClient.getDatabase("syn");
            /**
             * Получение списка таблиц
             */
//            getTableList(database);
            /**
             * Создание документа
             */
//            addDoc(todoCollection);

            // test
            var todoCollection = database.getCollection("todo");
            resOut(todoCollection);

            /**
             * Изменение документов
             */
            changeDoc(todoCollection);

            /**
             * Поиск документов и вывод результатов
             */
            resOut(todoCollection);
            /**
             * Удаление документов
             */
            todoCollection.deleteOne(new Document("_id", new ObjectId("659fcb48e24805191473dc6a")));

            resOut(todoCollection);

        }
    }

    private static void addDoc(MongoCollection<Document> todoCollection) {
        /**
         * Создать документ
         */
        var todoDocument = new Document(Map.of(
                "_id", new ObjectId(),
                "task", "Drink some coffee",
                "dateCreated", LocalDateTime.now(),
                "done", false));
/**
 * Добавить документ в таблицу
 */
        todoCollection.insertOne(todoDocument);
    }

    private static void changeDoc(MongoCollection<Document> todoCollection) {

        todoCollection.updateOne(new Document(

                 // поиск по подобию
                "_id", new ObjectId("659fcb48e24805191473dc6a")),

                 // обновление
                new Document(Map.of(
                        "$set", new Document("done", true),
                        "$currentDate", new Document("dateDone", true),
                        "$unset", new Document("dateCreated", true)
                ))
        );
    }

    private static void resOut(MongoCollection<Document> todoCollection) {
        /**
         * поиск
         */
        todoCollection.find()
                .forEach((Consumer<Document>) System.out::println);
        /**
         * Поиск по подобию
         */
//        todoCollection.find(new Document("task", new Document("$regex", "coffee")))
//                .forEach((Consumer<Document>) System.out::println);
    }

    private static void getTableList(MongoDatabase database) {
        database.listCollectionNames()
                .forEach((Consumer<String>) System.out::println);
        // todo
        database.listCollections()
                .forEach((Consumer<Document>) System.out::println);
    }

    private static void getDbList(MongoClient mongoClient) {
        mongoClient.listDatabases()
                .forEach((Consumer<Document>) System.out::println);
        // show dbs
        // Document{{name=test, sizeOnDisk=1.385336832E9, empty=false}}
        mongoClient.listDatabaseNames()
                .forEach((Consumer<String>) System.out::println);
    }
}
