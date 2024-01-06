package tech.ydb.jooq;

import java.util.Collection;
import java.util.List;

import org.jooq.Binding;
import org.jooq.CharacterSet;
import org.jooq.Collation;
import org.jooq.Comment;
import org.jooq.Configuration;
import org.jooq.Converter;
import org.jooq.DataType;
import org.jooq.Domain;
import org.jooq.EnumType;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Nullability;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultBinding;
import org.jooq.impl.SQLDataType;
import org.jooq.tools.Convert;

/**
 *
 * @author Aleksandr Gorshenin
 */
public class YQLDataType<T> implements DataType<T> {
    private static final long serialVersionUID = 8625722251997883175L;

    public static final DataType<Byte>    INT8  = new YQLDataType<>("Int8",  SQLDataType.TINYINT);
    public static final DataType<Short>   INT16 = new YQLDataType<>("Int16", SQLDataType.SMALLINT);
    public static final DataType<Integer> INT32 = new YQLDataType<>("Int32", SQLDataType.INTEGER);
    public static final DataType<Long>    INT64 = new YQLDataType<>("Int64", SQLDataType.BIGINT);

    private final String name;
    private final Class<T> type;
    private final DataType<T> sqlDataType;
    private final Converter<?, T> converter;
    private final Binding<?, T> binding;

    public YQLDataType(String name, DataType<T> sqlDataType) {
        this.name = name;
        this.type = sqlDataType.getType();
        this.sqlDataType = sqlDataType;
        this.converter = new NonConverter<>(type);
        this.binding = DefaultBinding.binding(converter);
    }

    @Override
    public DataType<T> getSQLDataType() {
        return sqlDataType;
    }

    @Override
    public DataType<T> getDataType(Configuration configuration) {
        return this;
    }

    @Override
    public int getSQLType() {
        return sqlDataType.getSQLType();
    }

    @Override
    public int getSQLType(Configuration configuration) {
        return sqlDataType.getSQLType(configuration);
    }

    @Override
    public Binding<?, T> getBinding() {
        return binding;
    }

    @Override
    public Converter<?, T> getConverter() {
        return converter;
    }

    @Override
    public Class<T> getType() {
        return type;
    }

    @Override
    public Domain<T> getDomain() {
        return null;
    }

    @Override
    public Class<T[]> getArrayType() {
        return null;
    }

    @Override
    public DataType<T[]> getArrayDataType() {
        return null;
    }

    @Override
    public Class<?> getArrayComponentType() {
        return null;
    }

    @Override
    public DataType<?> getArrayComponentDataType() {
        return null;
    }

    @Override
    public final <E extends EnumType> DataType<E> asEnumDataType(Class<E> enumDataType) {
        return null;
    }

    @Override
    public <U> DataType<U> asConvertedDataType(Converter<? super T, U> converter) {
        return asConvertedDataType(DefaultBinding.binding(converter));
    }

    @Override
    @SuppressWarnings("unchecked")
    public<U> DataType<U> asConvertedDataType(Binding<? super T, U> newBinding) {
        return (DataType<U>) this;
    }

    @Override
    public String getTypeName() {
        return name;
    }

    @Override
    public String getTypeName(Configuration configuration) {
        return name;
    }

    @Override
    public String getCastTypeName() {
        return null;
    }

    @Override
    public String getCastTypeName(Configuration configuration) {
        return null;
    }

    @Override
    public SQLDialect getDialect() {
        return SQLDialect.MYSQL;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T convert(Object object) {
        if (object == null)
            return null;
        else if (object.getClass() == getType())
            return (T) object;
        else
            return Convert.convert(object, getType());
    }

    @Override
    public T[] convert(Object... objects) {
        return Convert.convertArray(objects, getConverter());
    }

    @Override
    public List<T> convert(Collection<?> objects) {
        return Convert.convert(objects, getConverter());
    }

    @Override
    public DataType<T> nullability(Nullability nullability) {
        return null;
    }

    @Override
    public Nullability nullability() {
        return Nullability.NULL;
    }

    @Override
    public DataType<T> nullable(boolean nullable) {
        return this;
    }

    @Override
    public boolean nullable() {
        return true;
    }

    @Override
    public DataType<T> collation(Collation collation) {
        return null;
    }

    @Override
    public Collation collation() {
        return null;
    }

    @Override
    public DataType<T> characterSet(CharacterSet characterSet) {
        return null;
    }

    @Override
    public CharacterSet characterSet() {
        return null;
    }

    @Override
    public DataType<T> identity(boolean identity) {
        return this;
    }

    @Override
    public boolean identity() {
        return false;
    }

    @Override
    public DataType<T> defaultValue(T defaultValue) {
        return this;
    }

    @Override
    public DataType<T> defaultValue(Field<T> defaultValue) {
        return this;
    }

    @Override
    public Field<T> defaultValue() {
        return null;
    }

    @Override
    public DataType<T> default_(T defaultValue) {
        return this;
    }

    @Override
    public DataType<T> default_(Field<T> defaultValue) {
        return this;
    }

    @Override
    public Field<T> default_() {
        return null;
    }

    @Override
    @SuppressWarnings("deprecation")
    public DataType<T> defaulted(boolean defaulted) {
        return this;
    }

    @Override
    public boolean defaulted() {
        return false;
    }

    @Override
    public DataType<T> precision(int precision) {
        return this;
    }

    @Override
    public DataType<T> precision(int precision, int scale) {
        return this;
    }

    @Override
    public int precision() {
        return 0;
    }

    @Override
    public boolean hasPrecision() {
        return false;
    }

    @Override
    public boolean precisionDefined() {
        return false;
    }

    @Override
    public DataType<T> scale(int scale) {
        return this;
    }

    @Override
    public int scale() {
        return 0;
    }

    @Override
    public boolean hasScale() {
        return false;
    }

    @Override
    public boolean scaleDefined() {
        return false;
    }

    @Override
    public DataType<T> length(int length) {
        return this;
    }

    @Override
    public int length() {
        return 2;
    }

    @Override
    public boolean hasLength() {
        return false;
    }

    @Override
    public boolean lengthDefined() {
        return false;
    }

    @Override
    public boolean isNumeric() {
        return isInteger();
    }

    @Override
    public boolean isInteger() {
        return type == Integer.class || type == Long.class || type == Short.class || type == Byte.class;
    }

    @Override
    public boolean isString() {
        return type == String.class;
    }

    @Override
    public boolean isNString() {
        return false;
    }

    @Override
    public boolean isDateTime() {
        return false;
    }

    @Override
    public boolean isDate() {
        return false;
    }

    @Override
    public boolean isTimestamp() {
        return false;
    }

    @Override
    public boolean isTime() {
        return false;
    }

    @Override
    public boolean isTemporal() {
        return false;
    }

    @Override
    public boolean isInterval() {
        return false;
    }

    @Override
    public boolean isBinary() {
        return type == byte[].class;
    }

    @Override
    public boolean isLob() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isEmbeddable() {
        return false;
    }

    @Override
    public boolean isUDT() {
        return false;
    }

    @Override
    public boolean isEnum() {
        return false;
    }

    @Override
    public boolean isJSON() {
        return false;
    }

    @Override
    public boolean isXML() {
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Name getQualifiedName() {
        return DSL.name(name);
    }

    @Override
    public Name getUnqualifiedName() {
        return DSL.name(name);
    }

    @Override
    public String getComment() {
        return null;
    }

    @Override
    public Comment getCommentPart() {
        return null;
    }
}
