package patterns.injection.ioc.context.cast;


public class JavaNumberTypeCast {

    private static final String SHORT = "short";
    private static final String LONG = "long";
    private static final String FLOAT = "float";
    private static final String DOUBLE = "double";
    private static final String BYTE = "byte";
    private static final String BOOLEAN = "boolean";
    private static final String INT = "int";

    public static Object castPrimitive(String value, Class<?> clazz) {
        return switch (clazz.getName()) {
            case INT -> Integer.valueOf(value);
            case BOOLEAN -> Boolean.valueOf(value);
            case BYTE -> Byte.valueOf(value);
            case DOUBLE -> Double.valueOf(value);
            case FLOAT -> Float.parseFloat(value);
            case LONG -> Long.parseLong(value);
            case SHORT -> Short.parseShort(value);
            default -> null;
        };
    }
}
