package dao.mapper;

import entity.Relation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RelationMapper implements RowMapper {
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Relation relation = new Relation();
        relation.setRelationId(resultSet.getInt("relation_id"));
        relation.setUser1Id(resultSet.getInt("user1"));
        relation.setUser2Id(resultSet.getInt("user2"));
        relation.setRelationTypeId(resultSet.getInt("relation_type"));
        return relation;
    }
}
