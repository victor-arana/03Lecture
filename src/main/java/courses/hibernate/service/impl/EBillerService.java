package courses.hibernate.service.impl;

import courses.hibernate.dao.EBillerDAO;
import courses.hibernate.dao.impl.EBillerDAOHibernate;
import courses.hibernate.service.EBillerServiceInterface;
import courses.hibernate.vo.EBiller;

public class EBillerService implements EBillerServiceInterface {
	
	EBillerDAO eBillerDAO = new EBillerDAOHibernate();

	@Override
	public EBiller createEBiller(EBiller eBiller) {
		return eBillerDAO.createEBiller(eBiller);
	}

	@Override
	public EBiller getEBiller(long ebillerId) {
		return eBillerDAO.getEBiller(ebillerId);
	}

	@Override
	public void delete(EBiller eBiller) {
		eBillerDAO.deleteEBiller(eBiller);
		
	}

}
