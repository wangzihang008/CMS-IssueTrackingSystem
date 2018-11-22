package com.fdmgroup.Controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.DAO.IssueDAO;

public class IssueDetailControllerTest {

	@Mock
	private IssueDAO iDao;

	@InjectMocks
	private IssueDetailController idc = new IssueDetailController();

	@Before
	public void startInjectMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void return_GoToIssueDetail() {
		Model mockModel = mock(Model.class);
		String nextPage = idc.goToIssueDetail(mockModel);
		assertEquals("issue/detail", nextPage);
	}

}
