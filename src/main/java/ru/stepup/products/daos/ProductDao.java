package ru.stepup.products.daos;

import org.springframework.stereotype.Component;
import ru.stepup.products.configurations.DataSource;
import ru.stepup.products.entities.Product;
import ru.stepup.products.enums.ProductType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDao extends BaseDao<Product, Long> {
    public static final String SELECT_ALL_PRODUCTS = "select * from public.products";
    public static final String SELECT_PRODUCT_BY_ID = "select * from public.products u where u.id = ";
    public static final String UPDATE_PRODUCT_BY_ID = "update public.products u set account_number = %s, balance = %s, type = '%s' where u.id = %s";
    public static final String DELETE_PRODUCT_BY_ID = "delete from public.products u where u.id = ";
    public static final String INSERT_PRODUCT = "insert into public.products (account_number, balance, type) values (%s, %s, '%s');";

    public ProductDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Product> getAll() {
        List<Product> lst = new LinkedList<>();
        PreparedStatement ps = getPrepareStatement(SELECT_ALL_PRODUCTS);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getLong("id"),
                        rs.getLong("account_number"),
                        rs.getInt("balance"),
                        ProductType.valueOf(rs.getString("type").toUpperCase())
                );
                lst.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }

        return lst;
    }

    @Override
    public Optional<Product> getEntityById(Long id) {
        PreparedStatement ps = getPrepareStatement(SELECT_PRODUCT_BY_ID + id);
        Product product = null;
        try {
            ResultSet rs = ps.executeQuery();
            rs.next();
            product = new Product(
                    rs.getLong("id"),
                    rs.getLong("account_number"),
                    rs.getInt("balance"),
                    ProductType.valueOf(rs.getString("type").toUpperCase())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }

        return Optional.ofNullable(product);
    }

    @Override
    public boolean update(Product product) {
        PreparedStatement ps = getPrepareStatement(
                String.format(
                        UPDATE_PRODUCT_BY_ID,
                        product.getAccountNumber(),
                        product.getBalance(),
                        product.getType().getType(),
                        product.getId()
                )
        );
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closePrepareStatement(ps);
        }

        return true;
    }

    @Override
    public boolean delete(Long id) {
        PreparedStatement ps = getPrepareStatement(DELETE_PRODUCT_BY_ID + id);

        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closePrepareStatement(ps);
        }

        return true;
    }

    @Override
    public boolean create(Product product) {
        PreparedStatement ps = getPrepareStatement(
                String.format(
                        INSERT_PRODUCT,
                        product.getAccountNumber(),
                        product.getBalance(),
                        product.getType().getType()
                )
        );

        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closePrepareStatement(ps);
        }

        return true;
    }
}
