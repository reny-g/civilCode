package service;

import service.ES.indexDocApi.DocCRUD;

import java.io.IOException;

/**
 * @author 吴仁杨
 */
public class Main {
    public static void main(String[] args) throws IOException {
        new DocCRUD().search("财产权",0,13);
    }
}
