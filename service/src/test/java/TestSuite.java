import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthorizationServiceImplTest.class,
        CommentServiceImplTest.class,
        NewsServiceImplTest.class,
        RelationServiceImplTest.class,
        RelationTypeServiceImplTest.class,
        UserServiceImplTest.class
})
public class TestSuite {
}
