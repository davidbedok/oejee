package hu.qwaevisz.school.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.school.ejbservice.domain.MarkDetailStub;
import hu.qwaevisz.school.ejbservice.domain.MarkStub;
import hu.qwaevisz.school.persistence.entity.Mark;
import hu.qwaevisz.school.persistence.result.MarkDetailResult;

@Local
public interface MarkConverter {

	MarkStub to(Mark mark);

	List<MarkDetailStub> to(List<MarkDetailResult> results);

}
