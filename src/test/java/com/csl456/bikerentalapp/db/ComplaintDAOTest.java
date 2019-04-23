package com.csl456.bikerentalapp.db;

import com.csl456.bikerentalapp.core.*;
import io.dropwizard.testing.junit5.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
public class ComplaintDAOTest {

    private final DAOTestExtension daoTestRule = DAOTestExtension
            .newBuilder()
            .addEntityClass(Complaint.class)
            .build();

    private ComplaintDAO complaintDAO;

    @Test
    public void createComplaint() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 3, 15, 17, 9, 57);
        Date happyNewYearDate = calendar.getTime();
        final Complaint complaint = daoTestRule.inTransaction(() -> complaintDAO
                .create(new Complaint(
                        "punctured",
                        ComplaintStatus.UNRESOLVED,
                        1,
                        happyNewYearDate,
                        null,
                        1
                )));
        assertThat(complaint.getId()).isGreaterThan(0);
        assertThat(complaint.getDetails()).isEqualTo("punctured");
        assertThat(complaint.getStatus()).isEqualTo(ComplaintStatus.UNRESOLVED);
        assertThat(complaint.getPersonId()).isEqualTo(1);
        assertThat(complaint.getCycleId()).isEqualTo(1);
    }

    @Test
    public void findAll() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 3, 15, 17, 9, 57);
        Date happyNewYearDate = calendar.getTime();
        daoTestRule.inTransaction(() -> {
            complaintDAO.create(new Complaint(
                    "punctured",
                    ComplaintStatus.UNRESOLVED,
                    1,
                    happyNewYearDate,
                    null,
                    1
            ));
            complaintDAO.create(new Complaint(
                    "brake failed",
                    ComplaintStatus.UNRESOLVED,
                    2,
                    happyNewYearDate,
                    null,
                    2
            ));
        });

        final List<Complaint> complaints = complaintDAO.findAll();
        assertThat(complaints)
                .extracting("details")
                .containsOnly("punctured", "brake failed");
        assertThat(complaints).extracting("cycleId").containsOnly(1, 2);
        assertThat(complaints).extracting("personId").containsOnly(1, 2);
    }

    @Test
    public void findById() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 3, 15, 17, 9, 57);
        Date happyNewYearDate = calendar.getTime();
        daoTestRule.inTransaction(() -> {
            complaintDAO.create(new Complaint(
                    "punctured",
                    ComplaintStatus.UNRESOLVED,
                    1,
                    happyNewYearDate,
                    null,
                    1
            ));
        });

        final Complaint complaint = complaintDAO.getById(1);
        assertThat(complaint.getId()).isGreaterThan(0);
        assertThat(complaint.getDetails()).isEqualTo("punctured");
        assertThat(complaint.getStatus()).isEqualTo(ComplaintStatus.UNRESOLVED);
        assertThat(complaint.getPersonId()).isEqualTo(1);
        assertThat(complaint.getCycleId()).isEqualTo(1);
    }

    @BeforeEach
    public void setUp() {
        complaintDAO = new ComplaintDAO(daoTestRule.getSessionFactory());
    }

}
