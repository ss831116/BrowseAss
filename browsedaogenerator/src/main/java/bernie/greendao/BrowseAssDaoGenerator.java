package bernie.greendao;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class BrowseAssDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "bernie.greendao.dao");
        addNote(schema);
        try {
            new DaoGenerator().generateAll(schema,"D:\\SMACK-API-Android-Demo-master\\BrowseAss\\app\\src\\main\\java_gen\\");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * @param schema
     */
    private static void addNote(Schema schema) {
        Entity note = schema.addEntity("BrowseAssBookMarks");
        //note.addStringProperty("id").primaryKey().index();
        note.addStringProperty("webSite");
        note.addStringProperty("webSiteIcon");
        note.addStringProperty("saveDate");
    }
}
