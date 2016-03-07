package hu.qwaevisz.shopping.report.service;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.shopping.report.domain.Transaction;
import hu.qwaevisz.shopping.report.exception.ReportException;

@Local
public interface ShoppingReport {

	List<Transaction> getTransactions() throws ReportException;

	byte[] getTransactionDetailsReport(final long transactionId) throws ReportException;

}
