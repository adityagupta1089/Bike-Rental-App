package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.*;
import com.csl456.bikerentalapp.db.*;
import io.dropwizard.testing.junit5.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;

@ExtendWith(DropwizardExtensionsSupport.class)
public class UserResourceTest {

    private static final SessionDAO SESSION_DAO = mock(SessionDAO.class);
    private static final UserDAO    USER_DAO    = mock(UserDAO.class);

    private static final ResourceExtension RESOURCES = ResourceExtension
            .builder()
            .addResource(new SessionResource(USER_DAO, SESSION_DAO))
            .build();

    private final ArgumentCaptor<Session> sessionCaptor
            = ArgumentCaptor.forClass(Session.class);

    private Session session;

    private User user;


    @BeforeEach
    void setUp() {
        user    = new User("aditya", "abc", UserRole.NORMAL_USER, 1);
        session = new Session("aditya");
    }

    @AfterEach
    void tearDown() {
        reset(SESSION_DAO);
    }

}
