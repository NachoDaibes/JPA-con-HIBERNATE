package Config;

import org.hibernate.envers.RevisionListener;

import Audit.Revision;

public class CustomRevisionListener implements RevisionListener{
	
	public void newRevision(Object revisionEntity){
		final Revision revision = (Revision) revisionEntity;
	}
}
