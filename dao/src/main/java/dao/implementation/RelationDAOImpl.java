package dao.implementation;

import dao.DAOException;
import dao.RelationDAO;
import dao.mapper.RelationMapper;
import entity.Relation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RelationDAOImpl implements RelationDAO {
    private DriverManagerDataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional
    public void addRelation(int user1Id, int user2Id, int relationTypeId) throws DAOException {
        String query = "insert into relation(user1, user2, relation_type) values (?, ?, ?);";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query, new Object[]{user1Id, user2Id, relationTypeId});
    }

    @Transactional
    public Relation getRelationById(int relationId) throws DAOException {
        String query = "select * from relation where relation_id = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        Relation relation = (Relation) jdbcTemplate.queryForObject(query, new Object[]{relationId}, new RelationMapper());
        return relation;
    }

    @Transactional
    public void updateRelation(Relation relation) throws DAOException {
        String query = "update relation set user1 = ?, user2 = ?, relation_type = ? where relation_id = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query, new Object[]{relation.getRelationTypeId(), relation.getUser1Id(), relation.getUser2Id(),
                    relation.getRelationTypeId()});
    }

    @Transactional
    public void deleteRelationBy(int user1id, int user2Id) throws DAOException {
        String query = "delete from relation where user1 = ? and user2 = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query, new Object[]{user1id, user2Id});
    }

    @Transactional
    public List<Relation> getAllRelationBy(int userId) throws DAOException {
        String query = "select * from relation where user1 = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        List relation = jdbcTemplate.query(query,new Object[]{userId}, new RelationMapper());
        return relation;
    }
}
