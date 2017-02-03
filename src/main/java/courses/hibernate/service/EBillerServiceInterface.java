package courses.hibernate.service;

import courses.hibernate.vo.EBiller;

public interface EBillerServiceInterface {

	public EBiller createEBiller(EBiller eBiller);

	public EBiller getEBiller(long ebillerId);

	public void delete(EBiller eBiller);

}
