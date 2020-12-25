package domain;

/**
 * @author 吴仁杨
 */
public class Law {
    int id = 0;
    String part = " ";
    String partName = " ";
    String subPart = " ";
    String subPartName = " ";
    String section = " ";
    String sectionName = " ";
    String content = " ";
    String contentName = " ";

    public Law(int id,String part, String partName, String subPart, String subPartName,
               String section, String sectionName, String content, String contentName) {
        this.id=id;
        this.part = part;
        this.partName = partName;
        this.subPart = subPart;
        this.subPartName = subPartName;
        this.section = section;
        this.sectionName = sectionName;
        this.content = content;
        this.contentName = contentName;
    }
}
