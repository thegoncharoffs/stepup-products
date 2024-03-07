package ru.stepup.products.daos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.stepup.products.configurations.DataSource;
import ru.stepup.products.entities.ProductEntity;
import ru.stepup.products.enums.ProductType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class ProductDao extends BaseDao<ProductEntity, Long> {
    public static final String SELECT_ALL_PRODUCTS = "select * from public.products";
    public static final String SELECT_PRODUCT_BY_ID = "select * from public.products u where u.id = ";
    public static final String UPDATE_PRODUCT_BY_ID = "update public.products u set account_number = %s, balance = %s, type = '%s' where u.id = %s";
    public static final String DELETE_PRODUCT_BY_ID = "delete from public.products u where u.id = ";
    public static final String INSERT_PRODUCT = "insert into public.products (account_number, balance, type) values (%s, %s, '%s')";

    public ProductDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<ProductEntity> getAll() {
        List<ProductEntity> lst = new LinkedList<>();
        PreparedStatement ps = getPrepareStatement(SELECT_ALL_PRODUCTS);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductEntity product = new ProductEntity(
                        rs.getLong("id"),
                        rs.getLong("account_number"),
                        rs.getInt("balance"),
                        ProductType.valueOf(rs.getString("type").toUpperCase())
                );
                lst.add(product);
            }
        } catch (SQLException e) {
            log.error("getAll error ", e);
        } finally {
            closePrepareStatement(ps);
        }

        return lst;
    }

    @Override
    public Optional<ProductEntity> getById(Long id) {
        PreparedStatement ps = getPrepareStatement(SELECT_PRODUCT_BY_ID + id);
        ProductEntity productEntity = null;
        try {
            ResultSet rs = ps.executeQuery();
            rs.next();
            productEntity = new ProductEntity(
                    rs.getLong("id"),
                    rs.getLong("account_number"),
                    rs.getInt("balance"),
                    ProductType.valueOf(rs.getString("type").toUpperCase())
            );
        } catch (SQLException e) {
            log.error("getById error ", e);
        } finally {
            closePrepareStatement(ps);
        }

        return Optional.ofNullable(productEntity);
    }

    @Override
    public void update(ProductEntity productEntity) {
        PreparedStatement ps = getPrepareStatement(
                String.format(
                        UPDATE_PRODUCT_BY_ID,
                        productEntity.getAccountNumber(),
                        productEntity.getBalance(),
                        productEntity.getType().getType(),
                        productEntity.getId()
                )
        );
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("update error ", e);
        } finally {
            closePrepareStatement(ps);
        }
    }

    @Override
    public void delete(Long id) {
        PreparedStatement ps = getPrepareStatement(DELETE_PRODUCT_BY_ID + id);
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("delete error ", e);
        } finally {
            closePrepareStatement(ps);
        }
    }

    @Override
    public void create(ProductEntity productEntity) {
        PreparedStatement ps = getPrepareStatement(
                String.format(
                        INSERT_PRODUCT,
                        productEntity.getAccountNumber(),
                        productEntity.getBalance(),
                        productEntity.getType().getType()
                )
        );

        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("create error ", e);
        } finally {
            closePrepareStatement(ps);
        }
    }
}
