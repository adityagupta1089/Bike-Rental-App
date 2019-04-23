package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.*;
import com.csl456.bikerentalapp.db.*;
import io.dropwizard.testing.junit5.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(DropwizardExtensionsSupport.class)
public class ComplaintResourceTest {

    private static final ComplaintDAO COMPLAINT_DAO = mock(ComplaintDAO.class);

    private static final ResourceExtension RESOURCES = ResourceExtension
            .builder()
            .addResource(new ComplaintResource(COMPLAINT_DAO))
            .build();

    private final ArgumentCaptor<Complaint> complaintCaptor
            = ArgumentCaptor.forClass(Complaint.class);

    private Complaint complaint;

    @Test
    void createComplaint() {
        when(COMPLAINT_DAO.create(any(Complaint.class))).thenReturn(complaint);
        final Response response = RESOURCES
                .target("/complaint")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(complaint,
                        MediaType.APPLICATION_JSON_TYPE
                ));
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(COMPLAINT_DAO).create(complaintCaptor.capture());
        Complaint result = complaintCaptor.getValue();
        assertThat(result.getId()).isEqualTo(complaint.getId());
        assertThat(result.getDetails()).isEqualTo(complaint.getDetails());
        assertThat(result.getStatus()).isEqualTo(complaint.getStatus());
        assertThat(result.getPersonId()).isEqualTo(complaint.getPersonId());
        assertThat(result.getCycleId()).isEqualTo(complaint.getCycleId());
    }

    @Test
    void listComplaints() {
        final List<Complaint> complaints = Collections.singletonList(complaint);
        when(COMPLAINT_DAO.findAll()).thenReturn(complaints);

        final List<Complaint> response = RESOURCES
                .target("/complaint")
                .request()
                .get(new GenericType<List<Complaint>>() {});

        verify(COMPLAINT_DAO).findAll();
        assertThat(response).containsAll(complaints);
    }

    @BeforeEach
    void setUp() {
        complaint = new Complaint("punctured",
                ComplaintStatus.UNRESOLVED,
                1,
                new Date(0),
                new Date(500),
                1
        );
    }

    @AfterEach
    void tearDown() {
        reset(COMPLAINT_DAO);
    }


}
