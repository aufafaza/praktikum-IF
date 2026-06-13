import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReflectUtil {

    public static Object createInstance(Class<?> clazz, Map<String, String> data) throws Exception {
        Object instance = clazz.getDeclaredConstructor().newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            Property property = field.getAnnotation(Property.class);
            if (property == null) {
                continue;
            }

            String key = property.value();
            String value = data.get(key);
            if (value == null) {
                DefaultValue defaultValue = field.getAnnotation(DefaultValue.class);
                if (defaultValue != null) {
                    value = defaultValue.value();
                } else {
                    continue;
                }
            }

            field.setAccessible(true);
            field.set(instance, convert(field.getType(), value));
        }

        return instance;
    }

    public static Map<String, String> readProperties(Object obj) throws Exception {
        Map<String, String> result = new LinkedHashMap<>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            Property property = field.getAnnotation(Property.class);
            if (property == null) {
                continue;
            }

            field.setAccessible(true);
            Object value = field.get(obj);
            result.put(property.value(), String.valueOf(value));
        }

        return result;
    }

    public static boolean updateField(Object obj, String propertyName, String newValue) throws Exception {
        for (Field field : obj.getClass().getDeclaredFields()) {
            Property property = field.getAnnotation(Property.class);
            if (property == null || !property.value().equals(propertyName)) {
                continue;
            }

            if (field.getAnnotation(ReadOnly.class) != null) {
                return false;
            }

            field.setAccessible(true);
            field.set(obj, convert(field.getType(), newValue));
            return true;
        }

        return false;
    }

    public static void printSchema(Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            Property property = field.getAnnotation(Property.class);
            if (property == null) {
                continue;
            }

            boolean readOnly = field.getAnnotation(ReadOnly.class) != null;
            DefaultValue defaultValue = field.getAnnotation(DefaultValue.class);
            String defaultStr = defaultValue == null ? "-" : defaultValue.value();

            System.out.println(property.value() + " : " + field.getType().getSimpleName()
                    + " [readonly=" + readOnly + "] [default=" + defaultStr + "]");
        }
    }

    private static Object convert(Class<?> type, String value) {
        if (type == int.class || type == Integer.class) {
            return Integer.parseInt(value);
        }
        if (type == double.class || type == Double.class) {
            return Double.parseDouble(value);
        }
        return value;
    }
}
