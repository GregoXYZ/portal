package common.business.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;

public class SQLUtils {

    public static void setString(PreparedStatement stmt, int indice, String valor) throws SQLException {
        if( valor==null || "".equals(valor.trim()) ) {
            stmt.setNull(indice, Types.VARCHAR);
        } else {
            stmt.setString(indice, valor);
        }
    }

    public static void setNumber(PreparedStatement stmt, int indice, String valor) throws SQLException {
        if( valor==null || "".equals(valor.trim()) ) {
            stmt.setNull(indice, Types.NUMERIC);
        } else {
            stmt.setLong(indice, Long.parseLong(valor));
        }
    }

    public static void setNumber(PreparedStatement stmt, int indice, long valor) throws SQLException {
        stmt.setLong(indice, valor);
    }

    public static void setDate(PreparedStatement stmt, int indice, String valor) throws SQLException, java.text.ParseException {
        if( valor==null || "".equals(valor.trim()) ) {
            stmt.setNull(indice, Types.DATE);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Timestamp ts = new Timestamp(sdf.parse(valor).getTime());
            stmt.setTimestamp(indice, ts);
        }
    }

}