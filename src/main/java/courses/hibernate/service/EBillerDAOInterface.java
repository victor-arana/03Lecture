package courses.hibernate.service;

import courses.hibernate.vo.EBiller;

public interface EBillerDAOInterface {

	public EBiller createEBiller(EBiller eBiller);

	public EBiller getEBiller(long ebillerId);

	public void deleteEBiller(EBiller eBiller);

}
