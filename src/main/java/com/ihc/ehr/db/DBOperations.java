package com.ihc.ehr.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.ehr.model.ORMDeleteRecord;
import com.ihc.ehr.model.SpParameters;
import com.ihc.ehr.model.Wrapper_Entity;
import com.ihc.ehr.util.EnumUtil.Operation;
import com.ihc.ehr.util.GeneralOperations;

@Service
public class DBOperations {

	@Autowired
	private EntityManagerFactory emf;
	final public Logger logger;

	private DBOperations() {
		logger = Logger.getLogger(DBOperations.class);
	}

	@SuppressWarnings(value = { "unchecked" })
	public <T> List<T> getQueryData(String query, Class<T> c) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<T> list = null;
		try {
			Query theQuery = em.createNativeQuery(query, c);
			list = theQuery.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Error=> getQueryData() Class:" + c.toString(), e);
			System.out.println(e.getStackTrace());
			em.getTransaction().rollback();
		} finally {
			// em.getTransaction().commit();
			em.close();
		}
		return list;
	}

	@SuppressWarnings(value = { "unchecked" })
	public <T> List<T> getStoreProcedureData(String procName, Class<T> c, List<SpParameters> lstParam) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		String paramString = "";
		List<T> list = null;
		try {
			StoredProcedureQuery theQuery = em.createStoredProcedureQuery(procName, c);
			if (lstParam != null) {
				for (SpParameters param : lstParam) {

					theQuery.registerStoredProcedureParameter(param.getParamName(), param.getParmClass(),
							param.getParmMode());
					theQuery.setParameter(param.getParamName(), param.getParamValue());

					paramString += "'" + param.getParamValue() + "',";
				}
			}
			GeneralOperations.logMsg("*** Procedure **** " + procName + " " + paramString);

			list = theQuery.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Error=>:getStoreProcedureData Class=> " + c.toString() + " Procedure=> " + procName + " "
					+ paramString, e);
			System.out.println(e.getStackTrace());
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return list;
	}

	public String getQuerySingleResult(String query) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		String singleValue = "";
		try {
			Query theQuery = em.createNativeQuery(query);
			singleValue = theQuery.getSingleResult().toString();
			em.getTransaction().commit();
		} catch (NoResultException noResult) {
			System.out.println(noResult.getMessage());
			em.getTransaction().rollback();
		} catch (NonUniqueResultException nonUnique) {
			System.out.println(nonUnique.getMessage());
			em.getTransaction().rollback();
		} catch (Exception e) {
			logger.error("Error=>:getQuerySingleResult Query=> " + query, e);
			System.out.println(e.getStackTrace());
			em.getTransaction().rollback();
		} finally {
			// em.getTransaction().commit();
			em.close();
		}
		return singleValue;
	}

	public String getStoreProcedureSingleResult(String procName, List<SpParameters> lstParam) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		String paramString = "";
		String singleValue = "";
		try {
			StoredProcedureQuery theQuery = em.createStoredProcedureQuery(procName);

			if (lstParam != null) {
				for (SpParameters param : lstParam) {

					theQuery.registerStoredProcedureParameter(param.getParamName(), param.getParmClass(),
							param.getParmMode());
					theQuery.setParameter(param.getParamName(), param.getParamValue());

					paramString += "'" + param.getParamValue() + "',";

				}
			}

			GeneralOperations.logMsg("*** Procedure **** " + procName + " " + paramString);

			singleValue = theQuery.getSingleResult().toString();
			em.getTransaction().commit();

		} catch (NoResultException noResult) {
//			logger.error("Error=>:getStoreProcedureSingleResult  Procedure=> " + procName + " " + paramString,
//					noResult);
//			System.out.println(noResult.getMessage());
			em.getTransaction().rollback();
		} catch (NonUniqueResultException nonUnique) {
			logger.error("Error=>:getStoreProcedureSingleResult  Procedure=> " + procName + " " + paramString,
					nonUnique);
			System.out.println(nonUnique.getMessage());
			em.getTransaction().rollback();
		} catch (Exception e) {
			logger.error("Error=>:getStoreProcedureSingleResult  Procedure=> " + procName + " " + paramString, e);
			System.out.println(e.getStackTrace());
			em.getTransaction().rollback();
		} finally {
			// em.getTransaction().commit();
			em.close();
		}
		return singleValue;
	}

	public int ExecuteUpdateStoreProcedure(String procName, List<SpParameters> lstParam) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		int result = 0;
		String paramString = "";
		try {
			StoredProcedureQuery theQuery = em.createStoredProcedureQuery(procName);

			if (lstParam != null) {
				for (SpParameters param : lstParam) {

					theQuery.registerStoredProcedureParameter(param.getParamName(), param.getParmClass(),
							param.getParmMode());
					theQuery.setParameter(param.getParamName(), param.getParamValue());

					paramString += "'" + param.getParamValue() + "',";

				}
			}

			GeneralOperations.logMsg("*** Procedure **** " + procName + " " + paramString);

			result = theQuery.executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Error=>:ExecuteUpdateStoreProcedure  Procedure=> " + procName + " " + paramString, e);
			System.out.println(e.getStackTrace());
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}

	public String ExecuteUpdateStoreProcedureWithOutputResult(String procName, List<SpParameters> lstParam) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		String result = "";
		String paramString = "";
		try {
			StoredProcedureQuery theQuery = em.createStoredProcedureQuery(procName);

			if (lstParam != null) {
				for (SpParameters param : lstParam) {

					theQuery.registerStoredProcedureParameter(param.getParamName(), param.getParmClass(),
							param.getParmMode());
					theQuery.setParameter(param.getParamName(), param.getParamValue());

					paramString += "'" + param.getParamValue() + "',";

				}
			}

			GeneralOperations.logMsg("*** Procedure **** " + procName + " " + paramString);

			// add output varialbe
			theQuery.registerStoredProcedureParameter("result", String.class, ParameterMode.OUT);
			theQuery.execute();
			// get result
			result = (String) theQuery.getOutputParameterValue("result");

			// result = theQuery.executeUpdate();

			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error(
					"Error=>:ExecuteUpdateStoreProcedureWithOutputResult  Procedure=> " + procName + " " + paramString,
					e);
			System.out.println(e.getStackTrace());
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}

	public int ExecuteUpdateQuery(String query) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		int result = 0;
		try {
			Query theQuery = em.createNativeQuery(query);
			result = theQuery.executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Error=>:ExecuteUpdateQuery  Query=> " + query, e);
			System.out.println(e.getStackTrace());
			em.getTransaction().rollback();
		} finally {
			// em.getTransaction().commit();
			em.close();
		}
		return result;
	}

	public Long ExecuteUpdateQueryWithIdentity(String query) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Long generatedID = null;
		try {

			StoredProcedureQuery theQuery = em.createStoredProcedureQuery("IdentityQueryInsert");
			theQuery.registerStoredProcedureParameter("QUERY", String.class, ParameterMode.IN);
			theQuery.registerStoredProcedureParameter("IDENTITY", Long.class, ParameterMode.OUT);

			theQuery.setParameter("QUERY", query);

			theQuery.execute();
			generatedID = (Long) theQuery.getOutputParameterValue("IDENTITY");
			em.getTransaction().commit();
			// System.out.println("Generated ID:"+generatedID);
		} catch (Exception e) {
			logger.error("Error=>:ExecuteUpdateQueryWithIdentity  Query=> " + query, e);
			System.out.println(e.getStackTrace());
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

		return generatedID;
	}

	public Object AddEntityWithIdentity(Object entity) {


		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// Long result = (long) 0;
		try {
			em.persist(entity);
			em.flush();

			em.getTransaction().commit();
			// result = 1;
		} catch (Exception e) {
			logger.error("Error=> SaveEntity() Class:" + entity.toString(), e);
			System.out.println("Error=> SaveEntity()" + e.getStackTrace());
			em.getTransaction().rollback();
			// result = 0;
			entity = null;
		} finally {
			// em.getTransaction().commit();
			em.close();
			//entity = null;
		}
		return entity;
	}

	public int SaveEntity(Object entity, Operation operation) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		int result = 0;
		try {
			if (operation.equals(Operation.ADD)) {
				em.persist(entity);
			} else if (operation.equals(Operation.EDIT)) {
				em.merge(entity);
			}
			em.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			logger.error("Error=> SaveEntity() Class:" + entity.toString() + "operation: " + operation, e);
			System.out.println("Error=> SaveEntity()" + e.getStackTrace());
			em.getTransaction().rollback();
			result = 0;
		} finally {
			// em.getTransaction().commit();
			em.close();
		}
		return result;

	}

	public int AddUpdateEntityWrapper(List<Wrapper_Entity> lstEntityWrapper) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		int result = 0;
		try {
			for (Wrapper_Entity entityWrapper : lstEntityWrapper) {
				// try
				// {
				switch (entityWrapper.getType().toString()) {
				case "ENTITY":
					if (entityWrapper.getOperation().equals(Operation.ADD)) {
						em.persist(entityWrapper.getEntity());
					} else if (entityWrapper.getOperation().equals(Operation.EDIT)) {
						em.merge(entityWrapper.getEntity());
					}
					break;
				case "QUERY":
					Query theQuery = em.createNativeQuery(entityWrapper.getEntity().toString());
					theQuery.executeUpdate();
					break;

				default:
					break;
				}
				// }
				// catch(Exception ex)
				// {
				// logger.error("Error=>:AddUpdateEntityWrapper ForLoop Class=>
				// "+entityWrapper.toString()+"", ex);
				// }
			}

			/*
			 * if(operation.equals(Operation.ADD)) { em.persist(entity); } else
			 * if(operation.equals(Operation.EDIT)) { em.merge(entity); }
			 */
			em.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			for (Wrapper_Entity entityWrapper : lstEntityWrapper) {
				logger.error("Error=>:AddUpdateEntityWrapper" + entityWrapper.getEntity().toString(), e);
			}

			System.out.println(e.getStackTrace());
			em.getTransaction().rollback();
			result = 0;
		} finally {
			// em.getTransaction().commit();
			em.close();
		}
		return result;

	}
	public Long IDGeneratorInsurance(String tableName, String Practice_id) {
		
		String query="";
if(Practice_id.toString().equals("-1"))
{
	 query = " update table_id With (xlock,ROWLOCK) set max_value = max_value + 1 "
				+ " OUTPUT convert(varchar,'300')+convert(varchar,DELETED.server_id)+convert(varchar,DELETED.max_value) AS id "
				+ " where table_name = '" + tableName + "' and practice_id = '" + Practice_id.toString() + "' ";

}
		
		Long generatedId = Long.parseLong(getQuerySingleResult(query));

		return generatedId;

	}
	public Long IDGenerator(String tableName, Long Practice_id) {
		// String query = "select convert(varchar,max_value) as id from table_id With
		// (xlock,ROWLOCK) where table_name = '" + tableName + "' and
		// practice_id='"+Practice_id+"'"
		// + " update table_id set max_value = max_value + 1 where table_name = '" +
		// tableName + "' and practice_id='"+Practice_id+"'";
		String query="";
 
	query = " update table_id With (xlock,ROWLOCK) set max_value = max_value + 1 "
		+ " OUTPUT convert(varchar,DELETED.practice_id)+convert(varchar,DELETED.server_id)+convert(varchar,DELETED.max_value) AS id "
		+ " where table_name = '" + tableName + "' and practice_id = '" + Practice_id.toString() + "' ";

	
		
		Long generatedId = Long.parseLong(getQuerySingleResult(query));

		return generatedId;

	}

	public Long IDGenerator(String tableName, Long Practice_id, Integer limit) {
		String query = " update table_id With (xlock,ROWLOCK) set max_value = max_value + " + limit
				+ " OUTPUT convert(varchar,DELETED.practice_id)+convert(varchar,DELETED.server_id)+convert(varchar,DELETED.max_value) AS id "
				+ " where table_name = '" + tableName + "' and practice_id = '" + Practice_id.toString() + "' ";

		Long generatedId = Long.parseLong(getQuerySingleResult(query));
		return generatedId;

	}

	public String PIDGenerator(Long Practice_id) {
		String pid = "";

		String query = " update table_id With (xlock,ROWLOCK) set max_value = max_value + 1 "
				+ " OUTPUT convert(varchar,DELETED.max_value) AS id " + " where table_name = 'PID' and practice_id = '"
				+ Practice_id.toString() + "' ";

		/*
		 * String query =
		 * "select top 1 convert(varchar,max_value) as id from table_id With (xlock,ROWLOCK) where table_name = 'PID'  and practice_id = '"
		 * + Practice_id + "' " +
		 * " update table_id set max_value = max_value + 1  where table_name = 'PID' and practice_id = '"
		 * + Practice_id + "'";
		 */

		Long generatedId = Long.parseLong(getQuerySingleResult(query));
		pid = generatedId.toString();
		return pid;
	}
	 public  String GenerateTableMaxID(String TableName, String ColumnName) {
	        try {
	            String generatedID = "1";
	            String query = "select max(isnull(" + ColumnName + ",0)+1) as id from " + TableName;
	            generatedID= getQuerySingleResult(query);
	            return generatedID;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "";
	        }
	    }

	/*
	 * public String CashRegisterReceiptNoGenerator(Long practice_id) { try {
	 * 
	 * String receiptNo = "";
	 * 
	 * String query = " update table_id set max_value =  " +
	 * " case when max_value like convert(varchar,GETDATE(),112)+'%' then convert(varchar,GETDATE(),112)+''+convert(varchar,REPLACE(max_value,convert(varchar,GETDATE(),112),'')+1) "
	 * + " 	 else convert(varchar,GETDATE(),112)+'1' " + " end " +
	 * " OUTPUT case when max_value like convert(varchar,GETDATE(),112)+'%' then convert(varchar,GETDATE(),112)+'-'+convert(varchar,REPLACE(max_value,convert(varchar,GETDATE(),112),'')+1)    else convert(varchar,GETDATE(),112)+'-1' end as id  "
	 * + " where table_name = 'cr_receipt' and practice_id = '" +
	 * practice_id.toString() + "' ";
	 * 
	 * 
	 * receiptNo = getQuerySingleResult(query);
	 * 
	 * return receiptNo; } catch (Exception e) { e.printStackTrace(); return ""; } }
	 */
	public int deleteRecord(ORMDeleteRecord objDelete) {
		List<SpParameters> lstParam = new ArrayList<>();
		int result = 0;
		try {

			lstParam.add(new SpParameters("table_name", objDelete.getTable_name().toString(), String.class,
					ParameterMode.IN));
			lstParam.add(new SpParameters("column_name", objDelete.getColumn_name().toString(), String.class,
					ParameterMode.IN));
			lstParam.add(
					new SpParameters("column_id", objDelete.getColumn_id().toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("modified_user", objDelete.getModified_user().toString(), String.class,
					ParameterMode.IN));
			lstParam.add(new SpParameters("client_date_time", objDelete.getClient_date_time().toString(), String.class,
					ParameterMode.IN));
			lstParam.add(
					new SpParameters("system_ip", objDelete.getClient_ip().toString(), String.class, ParameterMode.IN));
			lstParam.add(new SpParameters("criteria", "", String.class, ParameterMode.IN));
			result = ExecuteUpdateStoreProcedure("spDelRecord", lstParam);
		}

		catch (Exception e) {
			logger.error("Error=>:deleteRecord  Class=> " + objDelete.toString(), e);
			result = 0;
		}
		return result;

	}

	public List<?> getStoreProcedureDataWOClass(String procName, List<SpParameters> lstParam) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<?> list = null;
		String paramString = "";
		try {
			StoredProcedureQuery theQuery = em.createStoredProcedureQuery(procName);

			if (lstParam != null) {
				for (SpParameters param : lstParam) {

					theQuery.registerStoredProcedureParameter(param.getParamName(), param.getParmClass(),
							param.getParmMode());
					theQuery.setParameter(param.getParamName(), param.getParamValue());

					paramString += "'" + param.getParamValue() + "',";

				}
			}

			GeneralOperations.logMsg("*** Procedure **** " + procName + " " + paramString);

			list = theQuery.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Error=>:getStoreProcedureDataWOClass  Procedure=> " + procName + " " + paramString + "", e);
			System.out.println(e.getStackTrace());
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return list;
	}

	public List<?> getQueryDataWOClass(String query) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<?> list = null;
		try {
			Query theQuery = em.createNativeQuery(query);
			list = theQuery.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Error=>:getQueryDataWOClass Query=> " + query, e);
			System.out.println(e.getStackTrace());
			em.getTransaction().rollback();
		} finally {
			// em.getTransaction().commit();
			em.close();
		}
		return list;
	}

}
