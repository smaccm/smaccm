package com.rockwellcollins.atc.agree.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osate.aadl2.ComponentImplementation;

import com.rockwellcollins.atc.agree.agree.SpecStatement;

public class AgreeStatementsShared {
	public static class AgreeStatementRepository {
		public static class AnnexRepository {
			private List<SpecStatement> statements;
			private String parent;
			private List<String> children;
			private boolean isAbstract = false;
			private ComponentImplementation implementation;
			private int randomlyVerify = 0;
			private List<String> verify;
			
			public AnnexRepository() {
				statements = new ArrayList<SpecStatement>();
				parent = null;
				children = new ArrayList<String>();
				verify = new ArrayList<String>();
			}
			
			public List<SpecStatement> getStatements() {
				return statements;
			}
			
			public String getParent() {
				return parent;
			}
			
			public void setParent(String parent) {
				this.parent = parent;
			}
			
			public List<String> getChildren() {
				return children;
			}
			
			public void setAbstract() {
				isAbstract = true;
			}
			
			public boolean isAbstract() {
				return isAbstract;
			}
			
			public void setImplementation(ComponentImplementation implementation) {
				this.implementation = implementation;
			}
			
			public ComponentImplementation getImplementation() {
				return implementation;
			}
			
			public void setRandomlyVerify(int randomlyVerify) {
				this.randomlyVerify = randomlyVerify;
			}
			
			public int getRandomlyVerify() {
				return randomlyVerify;
			}
			
			public List<String> getVerify() {
				return verify;
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
