package servicetest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthorizationServiceImplTest.class,
        AuthorizationTest.class,
        CommentServiceImplTest.class,
        NewsServiceImplTest.class,
        RelationServiceImplTest.class,
        RelationTypeServiceImplTest.class,
        UserServiceImplTest.class
})
public class TestSuite {
}
