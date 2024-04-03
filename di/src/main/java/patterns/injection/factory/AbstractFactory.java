package patterns.injection.factory;

public interface AbstractFactory {
    <T> T create(Class<T> clazz);
}
