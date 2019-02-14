package util;

public class GlobalConstant {

    public static final String PROJECT_DIR = GlobalConstant.class.getResource("/").getPath().replaceFirst("/","");

    public static final String LOCAL_HOST="127.0.0.1";

    public static final int PORT = 8000;

    public static final String FILE_SEPERATOR = "\\";

    public static final String PROXY_MODE_JDK = "proxy.JdkDynamicProxy";

    public static final String PROXY_MODE_CGLIB = "proxy.CglibProxy";

}
