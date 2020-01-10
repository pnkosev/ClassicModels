package orm;

import orm.interfaces.DBContext;
import orm.annotations.Column;
import orm.annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.IntStream;

public class EntityManager<E> implements DBContext<E> {
    private static final String INSERT_STATEMENT = "INSERT INTO `%s` (%s) VALUES (%s)";
    private static final String UPDATE_STATEMENT = "UPDATE %s SET %s WHERE %s";
    private static final String SELECT_ALL_WITH_WHERE = "SELECT * FROM %s WHERE %s";
    private static final String SELECT_FIRST_WITH_WHERE = SELECT_ALL_WITH_WHERE + " LIMIT 1";

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field idField = getId(entity.getClass());
        idField.setAccessible(true);

        Object idValue = idField.get(entity);

        if (idValue == null || (int) idValue <= 0) {
            return doInsert(entity);
        }

        return doUpdate(entity, idField);
    }

    public Iterable<E> find(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        return find(table, null);
    }

    public Iterable<E> find(Class<E> table, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = table.getSimpleName() + "s";
        Statement statement = connection.createStatement();
        String query = String.format(SELECT_ALL_WITH_WHERE,
                tableName,
                where == null ? "1 = 1" : where);

        ResultSet rs = statement.executeQuery(query);

        List<E> result = new ArrayList<>();

        while (rs.next()) {
            E current = this.mapResultToEntity(rs, table);
            result.add(current);
        }

        return result;
    }

    public E findFirst(Class<E> table) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return findFirst(table, null);
    }

    public E findFirst(Class<E> table, String where) throws SQLException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        return find(table, where == null ? "1 = 1 LIMIT 1" : where + " LIMIT 1").iterator().next();
    }

    private E mapResultToEntity(ResultSet rs, Class<E> table) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        E entity = table.getDeclaredConstructor().newInstance();

        Arrays.stream(table.getDeclaredFields())
                .forEach(f -> {
                    f.setAccessible(true);
                    String name = getNormalizedName(f.getName());
                    Object value = null;

                    try {
                        value = this.getFieldValueFromResultSet(rs, name, f.getType());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    try {
                        f.set(entity, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });

        return entity;
    }

    private Object getFieldValueFromResultSet(ResultSet rs, String name, Class<?> type) throws SQLException {
        Object result = null;

        if (type == int.class || type == Integer.class) {
            result = rs.getInt(name);
        } else if (type == double.class || type == Double.class) {
            result = rs.getDouble(name);
        } else if (type == long.class || type == Long.class) {
            result = rs.getLong(name);
        } else if (type == boolean.class || type == Boolean.class) {
            result = rs.getBoolean(name);
        } else if (type == Date.class) {
            result = rs.getDate(name);
        } else if (type == String.class) {
            result = rs.getString(name);
        }

        return result;
    }

    private Field getId(Class entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() ->
                        new UnsupportedOperationException("Entity does not have primary key")
                );
    }

    private boolean doInsert(E entity) throws SQLException {
        String tableName = this.getTableName(entity.getClass());
        String[] tableFields = getTableFields(entity);
        String[] tableValues = getTableValues(entity);

        String query = String.format(INSERT_STATEMENT,
                tableName,
                String.join(", ", tableFields),
                String.join(", ", tableValues)
        );

        return this.connection.prepareStatement(query).execute();
    }

    private boolean doUpdate(E entity, Field primary) throws SQLException, IllegalAccessException {
        String tableName = this.getTableName(entity.getClass());
        String[] tableFields = getTableFields(entity);
        String[] tableValues = getTableValues(entity);

        String[] fieldValuePairs = IntStream.range(0, tableFields.length)
                .mapToObj(i -> "`" + tableFields[i] + "`=" + tableValues[i])
                .toArray(String[]::new);

        String whereId = "`" + primary.getName() + "`='" + primary.get(entity) + "'";

        String query = String.format(UPDATE_STATEMENT,
                tableName,
                String.join(", ", fieldValuePairs),
                whereId
        );

        return this.connection.prepareStatement(query).execute();
    }

    private String[] getTableFields(E entity) {
        return Arrays.stream(entity
                .getClass()
                .getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> {
                    f.setAccessible(true);
                    return this.getNormalizedName(f.getName());
                })
                .toArray(String[]::new);
    }

    private String getNormalizedName(String name) {
        name = name.replaceAll("([A-Z])", "_$1").toLowerCase();
        name = name.replaceAll("([0-9])", "_$1");
        return name;
    }

    private String[] getTableValues(E entity) {
        return Arrays.stream(entity
                .getClass()
                .getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> {
                    f.setAccessible(true);
                    try {
                        Object value = f.get(entity);
                        value = value != null ? value.toString() : null;
                        return "'" +  value + "'";
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return "";
                    }
                })
                .toArray(String[]::new);
    }

    private String getTableName(Class aClass) {
        return aClass.getSimpleName().toLowerCase().concat("s");
    }
}
