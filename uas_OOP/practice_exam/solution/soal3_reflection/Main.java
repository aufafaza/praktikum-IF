import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Object instance = null;

        while (sc.hasNext()) {
            String cmd = sc.next();
            switch (cmd) {
                case "CREATE": {
                    String className = sc.next();
                    String payload = sc.next();
                    Map<String, String> data = new HashMap<>();
                    for (String pair : payload.split(",")) {
                        String[] kv = pair.split("=");
                        data.put(kv[0], kv[1]);
                    }
                    Class<?> clazz = Class.forName(className);
                    instance = ReflectUtil.createInstance(clazz, data);
                    System.out.println("CREATED " + instance);
                    break;
                }
                case "SCHEMA": {
                    String className = sc.next();
                    ReflectUtil.printSchema(Class.forName(className));
                    break;
                }
                case "READ": {
                    System.out.println(ReflectUtil.readProperties(instance));
                    break;
                }
                case "UPDATE": {
                    String property = sc.next();
                    String value = sc.next();
                    boolean ok = ReflectUtil.updateField(instance, property, value);
                    if (!ok) {
                        System.out.println("READONLY: " + property);
                    } else {
                        System.out.println("UPDATED " + property + " -> " + value);
                    }
                    break;
                }
                case "END":
                    sc.close();
                    return;
                default:
                    break;
            }
        }
        sc.close();
    }
}
