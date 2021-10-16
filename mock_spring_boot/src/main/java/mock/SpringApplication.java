package mock;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class SpringApplication {

    public static void run() {
        try {
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(8080);
            StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File("src/main").getAbsolutePath());
            ctx.setReloadable(false);
            // class文件地址
            File file = new File("target/classes");
            // 创建webRoot
            WebResourceRoot resource = new StandardRoot(ctx);
            // Tomcat内部读取class执行
            resource.addJarResources(new DirResourceSet(resource, "/WEB-INF/classes", file.getAbsolutePath(), "/"));
            // 启动Tomcat
            tomcat.start();
            System.out.println("Tomcat服务启动成功...");
            // 异步接收请求
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
