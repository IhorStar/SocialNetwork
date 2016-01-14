package dao.implementation;

import dao.DAOException;
import dao.RelationTypeDAO;
import dao.mapper.RelationTypeMapper;
import entity.RelationType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RelationTypeDAOImpl implements RelationTypeDAO {
    private DriverManagerDataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addRelationType(RelationType relationType) throws DAOException {
        String query = "insert into relation_type  values (?, ?);";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query, new Object[]{relationType.getRelationTypeId(), relationType.getRelationTypeName()});
    }

    public RelationType getRelationTypeById(int relationTypeId) throws DAOException {
        String query = "select * from relation_type where relation_type_id = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        RelationType relationType = (RelationType) jdbcTemplate.queryForObject(query, new Object[]{relationTypeId}, new RelationTypeMapper());
        return relationType;
    }

    public void updateRelationType(RelationType relationType) throws DAOException {
        String query = "update relation_type set name = ? where relation_type_id = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query,new Object[]{relationType.getRelationTypeId(), relationType.getRelationTypeName()});
    }

    public void deleteRelationTypeById(int relationTypeId) throws DAOException {
        String query = "delete from relation_type where relation_type_id = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query, new Object[]{relationTypeId});
    }
}
