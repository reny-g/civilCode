package domain;

public class OneLaw {
    int id = 0;
    String content = " ";
    String contentName = " ";

    //构造方法作在对象中不存在
    public OneLaw(int id,String content, String contentName) {
        this.id = id;
        this.content = content;
        this.contentName = contentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }
}
