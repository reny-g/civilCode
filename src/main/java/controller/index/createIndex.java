package controller.index;

import service.ES.indexApi.IndexCRUD;

import java.io.IOException;
import java.sql.SQLException;

public class createIndex {
    public static void main(String[] args) throws IOException, SQLException {
        new IndexCRUD().deleteIndex("laws");
        System.out.println(new IndexCRUD().IsExistIndex("laws"));
        new IndexCRUD().createIndexFromTable("laws","db_laws","id","content","contentName");
    }
}
