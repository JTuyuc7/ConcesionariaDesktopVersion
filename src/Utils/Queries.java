package Utils;

import DBConnection.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Queries {

    DBConnection db = new DBConnection();

    public ResultSet getAllDataFromDb(){
        String query = "SELECT codigoproducto, nombreproducto, cantidadproducto, preciounitario, CONVERT_TZ(fecha,'+00:00','-06:00') fecha FROM `producto`";
        ResultSet inventario = null;

        try {
            inventario = db.readFromDb(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return inventario;
    }

    public String getSingleProductDb(String product_code ) throws SQLException {
        String query = "SELECT * FROM producto WHERE `codigoproducto` = ?";
        ResultSet product = null;
        String codigo_producto = "";
        product = db.singleProduct(query, product_code);
        try {
            product = db.singleProduct(query, product_code);
        }catch (SQLException e){
            System.out.println("unable to get the product");
            e.printStackTrace();
        }
        if(product != null){
            while (product.next()){
                codigo_producto = product.getString(1);
            }
        }
        return codigo_producto;
    }

    public ResultSet getSingleProductFromDb(String product_code ) throws SQLException {
        String query = "SELECT * FROM producto WHERE `codigoproducto` = ?";
        ResultSet product = null;
        product = db.singleProduct(query, product_code);
        try {
            product = db.singleProduct(query, product_code);
        }catch (SQLException e){
            System.out.println("unable to get the product");
            e.printStackTrace();
        }
        return product;
    }

    public int insertNewProductDb(String product_code, String product_name, int product_qty, float product_price) throws SQLException {
        String queryAddProduct = "INSERT INTO `producto` (`codigoproducto`, `nombreproducto`, `cantidadproducto`, `preciounitario`) VALUES (?, ?, ?, ?)";
        return db.insertRecord(queryAddProduct, product_code, product_name, product_qty, product_price);
    }

    public int updateRecordOnDb(String product_code, int amount_to_update ) throws SQLException {
        String queryUpdate = "UPDATE `producto` SET `cantidadproducto` = ? WHERE `producto`.`codigoproducto` = ?";
        return db.updateRecord(queryUpdate, product_code, amount_to_update);
    }

    public int deleteRecordFromDb(String product_code) throws SQLException {
        String deleteQuery = "DELETE FROM `producto` WHERE `producto`.`codigoproducto` = ?";
        return db.deleteProduct(deleteQuery, product_code);
    }
}
