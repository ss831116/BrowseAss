package bernie.greendao;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class BrowseAssDaoGenerator {
    private static BrowseAssDaoGenerator browseAssDaoGenerator;
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "bernie.greendao.dao");
        browseAssDaoGenerator = new BrowseAssDaoGenerator();
        browseAssDaoGenerator.addNote(schema);
        browseAssDaoGenerator.addNoteHistory(schema);
        try {
            new DaoGenerator().generateAll(schema,"D:\\SMACK-API-Android-Demo-master\\BrowseAss\\app\\src\\main\\java_gen\\");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * @param schema
     */
    private void addNote(Schema schema) {
        Entity note = schema.addEntity("BrowseAssBookMarks");
        //note.addStringProperty("id").primaryKey().index();
        note.addIdProperty().primaryKey();
        note.addStringProperty("webSite");
        note.addByteArrayProperty("webSiteIcon");
        note.addStringProperty("saveDate");
        note.addStringProperty("title");
    }
    private void addNoteHistory(Schema schema){
        Entity note = schema.addEntity("HistoryWebPage");
        note.addIdProperty().primaryKey();
        note.addStringProperty("webPageSite");
        note.addStringProperty("webTitle");
        note.addStringProperty("scanTime");
    }
}
