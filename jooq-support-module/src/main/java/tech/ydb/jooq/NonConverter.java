package tech.ydb.jooq;

import org.jooq.Converter;

/**
 *
 * @author Aleksandr Gorshenin
 */
public class NonConverter<T> implements Converter<T, T> {
    private static final long serialVersionUID = 5334243875502868164L;

    private final Class<T> clazz;

    public NonConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T from(T databaseObject) {
        return databaseObject;
    }

    @Override
    public T to(T userObject) {
        return userObject;
    }

    @Override
    public Class<T> fromType() {
        return clazz;
    }

    @Override
    public Class<T> toType() {
        return clazz;
    }

    @Override
    public Converter<T, T> inverse() {
        return this;
    }
}
