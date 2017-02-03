package courses.hibernate.dao;

import courses.hibernate.vo.EBiller;

public interface EBillerDAO {

	public EBiller createEBiller(EBiller eBiller);

	public EBiller getEBiller(long ebillerId);

	public void deleteEBiller(EBiller eBiller);

}
