package ru.stepup.products.daos;

import lombok.extern.slf4j.Slf4j;
import ru.stepup.products.configurations.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class BaseDao<E, K> {
    private final Connection connection;

    public BaseDao(DataSource dataSource) {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract List<E> getAll();
    public abstract boolean update(E entity);
    public abstract Optional<E> getById(K id);
    public abstract boolean delete(K id);
    public abstract boolean create(E entity);

    // Получение экземпляра PrepareStatement
    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            log.error("sql exception", e);
        }

        return ps;
    }

    // Закрытие PrepareStatement
    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                log.error("sql exception", e);
            }
        }
    }
}
