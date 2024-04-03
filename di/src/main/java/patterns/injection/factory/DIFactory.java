package patterns.injection.factory;

import patterns.injection.service.DBIntegrationService;
import patterns.injection.service.FileIntegrationService;
import patterns.injection.service.FileService;
import patterns.injection.service.ParseService;
import patterns.injection.service.StringIntegrationService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class DIFactory
        implements AbstractFactory {
    private static final Map<Class<?>, Object> context = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> clazz) {
        if (StringIntegrationService.class.isAssignableFrom(clazz)) {
            return (T) (new ParseService());
        }
        if (FileIntegrationService.class.isAssignableFrom(clazz)) {
            Object object = context.get(clazz);
            if (object == null) {
                context.put(clazz, new FileService());
                return (T) context.get(clazz);
            }
            return (T) object;
        }
        if (DBIntegrationService.class.isAssignableFrom(clazz)) {
            return (T) getProxy(clazz);
        }
        return null;
    }

    private <T> Object getProxy(final Class<T> clazz) {
        return Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz}, new InvocationHandler() {
                    @Override
                    public Object invoke(
                            Object proxy,
                            Method method,
                            Object[] args) {
                        if (method.getName().startsWith("get")) {
                            return handleGetMethod(method, args);
                        }
                        return null;
                    }

                    private Object handleGetMethod(
                            Method method,
                            Object[] args) {
                        String[] tableNameAndParams =
                                method.getName().substring(3).split("By");
                        String tableName = tableNameAndParams[0];
                        String[] conditions =
                                tableNameAndParams[1].split("(And|Or)");
                        if (conditions.length > 0 && args != null && args.length >= conditions.length) {
                            return getSql(args, tableName, conditions,
                                    tableNameAndParams);
                        }
                        return null;
                    }

                    private static String getSql(
                            Object[] args,
                            String tableName,
                            String[] conditions,
                            String[] tableNameAndParams) {
                        StringBuilder query = new StringBuilder(
                                String.format("SELECT * FROM %s WHERE ",
                                        tableName.toLowerCase()));
                        for (int i = 0; i < conditions.length; i++) {
                            query.append(String.format("%s = '%s'",
                                    conditions[i].toLowerCase(), args[i]));
                            if (i < conditions.length - 1) {
                                if (tableNameAndParams[1].contains("And")) {
                                    query.append(" AND ");
                                } else if (tableNameAndParams[1].contains(
                                        "Or")) {
                                    query.append(" OR ");
                                }
                            }
                        }
                        return query.toString();
                    }
                });
    }
}
