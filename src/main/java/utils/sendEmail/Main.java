package utils.sendEmail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.io.File;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author 吴仁杨
 */
public class Main {
    public static final String SMTP_SERVER = "smtp.163.com";
    public static final String SMTP_PORT = "587";
    public static final String ACCOUNT = "reng_y@163.com";
    public static final String PASSWORD = "UOEDMEEPUYLEDNNG";

    private static HtmlEmail getEmail() {
        HtmlEmail htmlEmail = new HtmlEmail();
        // 配置发件信息
        htmlEmail.setSmtpPort(Integer.parseInt(SMTP_PORT));
        htmlEmail.setHostName(SMTP_SERVER);
        htmlEmail.setAuthentication(ACCOUNT, PASSWORD);
        htmlEmail.setSSLOnConnect(true);
        htmlEmail.setCharset("UTF-8");
        return htmlEmail;
    }

    /**
     *
     * @param addTo 目标地址
     * @param subject 邮件主题
     * @param msg 具体内容
     * @throws EmailException
     */
    public static void sendSimpleEmail(String addTo , String subject , String msg) throws EmailException {
        HtmlEmail htmlEmail = getEmail();
        htmlEmail.setFrom(ACCOUNT);
        htmlEmail.addTo(addTo);
        htmlEmail.setSubject(subject);
        htmlEmail.setHtmlMsg(msg);
        htmlEmail.send();
        System.out.println("邮件发送成功！");
    }

    public static void main(String[] args) throws Exception {
        //sendSimpleEmail("15179793794@139.com", "来自xxx（嘿嘿）的邮件", "hello，Email,Emaik!");
//        sendAttachmentEmail("C:\\Users\\吴仁杨\\Pictures\\爱壁纸UWP\\动漫\\2 (15).jpg",
//                "某个图片", "15179793794@139.com", "来自嘿嘿嘻嘻",
//                "Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、" +
//                        "指针等概念，因此Java语言具有功能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表，" +
//                        "极好地实现了面向对象理论，允许程序员以优雅的思维方式进行...");
        Map<String, Object> map = new HashMap<>(2);
        map.put("user", "reng_y");
        map.put("oneLaw", new domain.OneLaw(1, "www.baidu.com", "民法典第十条"));
        sendTemplateEmail("template.html",
                "15179793794@139.com", "模板文件哦！",map);
    }

    /**
     * description: 发送附件邮件
     * @param path  附件路径，也可以是url
     * @param attachmentName 附件名称
     * @param addTo  收件人
     * @param subject  主题
     * @param msg 内容
     * @throws Exception
     * @return void
     * @author w
     */
    public static void sendAttachmentEmail(String path ,String attachmentName,String addTo , String subject , String msg) throws Exception {
        EmailAttachment attachment = new EmailAttachment();
        // 也可以发送本地文件作为附件
        attachment.setPath(path);
        //attachment.setURL(new URL(path));
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("图片描述zzz");
        // 附件名称
        attachment.setName(attachmentName);

        HtmlEmail htmlEmail = getEmail();
        // 添加附件
        htmlEmail.attach(attachment);

        htmlEmail.setFrom(ACCOUNT);
        htmlEmail.addTo(addTo);
        htmlEmail.setSubject(subject);
        htmlEmail.setHtmlMsg(msg);
        htmlEmail.send();
        System.out.println("附件发送成功！");
    }

    /**
     * description: 初始化 FreeMarker 相关配置
     * @param path  模板路径位置
     * @throws Exception
     * @return Configuration
     * @author w
     */
    private static Configuration init(String path) throws Exception{
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setEncoding(Locale.CHINESE,"UTF-8");
        //模板加载的目录
        configuration.setDirectoryForTemplateLoading(new File(path));
        return configuration;
    }

    /**
     * description: 获取模板对象
     * @param path  模板目录
     * @param templateName  模板名称
     * @throws Exception
     * @return Template
     * @author w
     */
    public static Template getTemplate(String path , String templateName) throws Exception {
        Configuration configuration = init(path);
        Template template = configuration.getTemplate(templateName);
        return template ;
    }

    /**
     * description: 生成的模板转换成字符串输出
     * @param path 模板目录
     * @param templateName  模板名称
     * @param dataMap  数据map
     * @throws Exception
     * @return String
     * @author w
     */
    public static String convertToString(String path , String templateName, Map<String,Object> dataMap) throws Exception {
        StringWriter stringWriter = new StringWriter();
        Template template = getTemplate(path, templateName);
        template.process(dataMap, stringWriter);
        return stringWriter.toString();
    }
    /**
     * description: 发送模板邮件
     * @param templateName  模板名称
     * @param addTo  收件人
     * @param subject  邮件主题
     * @param map  邮件数据
     * @throws Exception
     * @return void
     * @author w
     */
    public static void sendTemplateEmail(String templateName , String addTo ,String subject,Map<String,Object> map) throws Exception {
        HtmlEmail htmlEmail = getEmail();
        // 模板根目录，从resource中获取模板
        String path = Email.class.getResource("/").getFile();
        System.out.println("path:"+path);
        path = URLDecoder.decode(path, "utf-8");
        // 生成的模板转换成字符串输出
        System.out.println("path:"+path);
        String htmlStr = convertToString(path, templateName, map);
        // 发件人
        htmlEmail.setFrom(ACCOUNT);
        // 收件人， 可多个
        htmlEmail.addTo(addTo);
        // 邮件主题
        htmlEmail.setSubject(subject);
        // 邮件内容
        htmlEmail.setHtmlMsg(htmlStr);
        // 发送
        htmlEmail.send();
        System.out.println("模板邮件发送成功！");
    }
}
