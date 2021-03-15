package domain;

/**
 * @author 吴仁杨
 */
public class Content {
    int id = 0;
    String part = " ";
    String partName = " ";
    String subPart = " ";
    String subPartName = " ";
    String section = " ";
    String sectionName = " ";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getSubPart() {
        return subPart;
    }

    public void setSubPart(String subPart) {
        this.subPart = subPart;
    }

    public String getSubPartName() {
        return subPartName;
    }

    public void setSubPartName(String subPartName) {
        this.subPartName = subPartName;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Content(int id, String part, String partName, String subPart, String subPartName, String section, String sectionName) {
        this.id = id;
        this.part = part;
        this.partName = partName;
        this.subPart = subPart;
        this.subPartName = subPartName;
        this.section = section;
        this.sectionName = sectionName;
    }
}
