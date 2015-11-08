package com.rockwellcollins.atc.agree.shared;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.rockwellcollins.atc.agree.agree.SpecStatement;

public class AgreeStatementsShared {
	private static class AgreeStatementRepository {
		public static class AnnexRepository {
			private Set<SpecStatement> statements;
			private Set<String> parents;
			private Set<String> children;
			private boolean isAbstract = false;
			
			public AnnexRepository() {
				statements = new HashSet<SpecStatement>();
				parents = new HashSet<String>();
				children = new HashSet<String>();
			}
			
			public Set<SpecStatement> getStatements() {
				return statements;
			}
			
			public Set<String> getParents() {
				return parents;
			}
			
			public Set<String> getChildren() {
				return children;
			}
			
			public void setAbstract() {
				isAbstract = true;
			}
			
			public boolean isAbstract() {
				return isAbstract;
			}
		}
		
		private Map<String, AnnexRepository> repository;
		
		public AgreeStatementRepository() {
			repository = new HashMap<String, AnnexRepository>();
		}
		
		public AnnexRepository getRepository(String annexName) {
			if(repository.get(annexName) == null) {
				repository.put(annexName, new AnnexRepository()); 
			}
			return repository.get(annexName);
		}
		
		public void clearRepository() {
			repository.clear();
		}
	}
	
	private static AgreeStatementRepository agreeStatementRepository;
	
	public static AgreeStatementRepository getRepositoryInstance() {
		if(agreeStatementRepository == null) {
			agreeStatementRepository = new AgreeStatementRepository();
		}
		return agreeStatementRepository;
	}
	
	public static AgreeStatementRepository.AnnexRepository getRepository(String annexName) {
		return getRepositoryInstance().getRepository(annexName);
	}
	
	public static void clearRepository() {
		getRepositoryInstance().clearRepository();
	}
}
