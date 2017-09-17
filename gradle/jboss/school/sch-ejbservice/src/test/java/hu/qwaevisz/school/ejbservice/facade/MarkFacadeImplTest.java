package hu.qwaevisz.school.ejbservice.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import hu.qwaevisz.school.ejbservice.converter.MarkConverter;
import hu.qwaevisz.school.ejbservice.domain.MarkDetailStub;
import hu.qwaevisz.school.ejbservice.exception.AdaptorException;
import hu.qwaevisz.school.persistence.entity.Subject;
import hu.qwaevisz.school.persistence.entity.trunk.Institute;
import hu.qwaevisz.school.persistence.exception.PersistenceServiceException;
import hu.qwaevisz.school.persistence.result.MarkDetailResult;
import hu.qwaevisz.school.persistence.service.MarkService;
import hu.qwaevisz.school.persistence.service.StudentService;
import hu.qwaevisz.school.persistence.service.SubjectService;

public class MarkFacadeImplTest {

	private static final String SUBJECT_NAME = "LoremIpsumSubject";
	private static final Long SUBJECT_ID = 42L;

	@InjectMocks
	private MarkFacadeImpl facade;

	@Mock
	private StudentService studentService;

	@Mock
	private SubjectService subjectService;

	@Mock
	private MarkService markService;

	@Mock
	private MarkConverter markConverter;

	@BeforeMethod
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createListOfMarkDetailsFromSubjectName() throws AdaptorException, PersistenceServiceException {
		final Subject subject = Mockito.mock(Subject.class);
		Mockito.when(this.subjectService.read(SUBJECT_NAME)).thenReturn(subject);
		Mockito.when(subject.getId()).thenReturn(SUBJECT_ID);
		final List<MarkDetailResult> results = new ArrayList<>();
		results.add(new MarkDetailResult(Institute.NEUMANN, new Date(), 0));
		results.add(new MarkDetailResult(Institute.KANDO, new Date(), 0));
		Mockito.when(this.markService.read(SUBJECT_ID)).thenReturn(results);
		final List<MarkDetailStub> stubs = new ArrayList<>();
		final MarkDetailStub neumannStub = Mockito.mock(MarkDetailStub.class);
		stubs.add(neumannStub);
		final int yearKando = 2014;
		final double averageGradeKando = 2.4142;
		stubs.add(new MarkDetailStub(Institute.KANDO.toString(), yearKando, averageGradeKando));
		Mockito.when(this.markConverter.to(results)).thenReturn(stubs);

		final List<MarkDetailStub> markDetailStubs = this.facade.getMarkDetails(SUBJECT_NAME);

		Mockito.verify(this.markService).read(SUBJECT_ID);

		Assert.assertEquals(markDetailStubs.size(), stubs.size());
		Assert.assertEquals(markDetailStubs.get(0), neumannStub);
		Assert.assertEquals(markDetailStubs.get(1).getInstitute(), Institute.KANDO.toString());
		Assert.assertEquals(markDetailStubs.get(1).getYear(), yearKando);
		Assert.assertEquals(markDetailStubs.get(1).getAverageGrade(), averageGradeKando);
	}

}
