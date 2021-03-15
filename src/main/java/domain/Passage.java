package domain;

/**
 * @author 张宏沛
 */
public class Passage {
    String title = "";
    String publish_date = "";
    String general_content = "";
    String complete_content = "";
    String source_name = "";
    String source_url = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getGeneral_content() {
        return general_content;
    }

    public void setGeneral_content(String general_content) {
        this.general_content = general_content;
    }

    public String getComplete_content() {
        return complete_content;
    }

    public void setComplete_content(String complete_content) {
        this.complete_content = complete_content;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public Passage(String title, String publish_date, String general_content,
                   String complete_content, String source_name, String source_url) {
        this.title = title;
        this.publish_date = publish_date;
        this.general_content = general_content;
        this.complete_content = complete_content;
        this.source_name = source_name;
        this.source_url = source_url;
    }
}
