package dao.mapper;

import entity.RelationType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RelationTypeMapper implements RowMapper{
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        RelationType relationType = new RelationType();
        relationType.setRelationTypeId(resultSet.getInt("relation_type_id"));
        relationType.setRelationTypeName(resultSet.getString("name"));
        return relationType;
    }
}
