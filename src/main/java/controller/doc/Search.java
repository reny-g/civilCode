package controller.doc;

import service.ES.indexDocApi.DocCRUD;

import java.io.IOException;

/**
 * @author 吴仁杨
 */
public class Search {
    public static void main(String[] args) throws IOException {
        new DocCRUD().searchLighter("财产权",0,130);
    }
}
