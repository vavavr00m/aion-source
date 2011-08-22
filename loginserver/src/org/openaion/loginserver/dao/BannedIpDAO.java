package org.openaion.loginserver.dao;

import java.sql.Timestamp;
import java.util.Set;

import org.openaion.commons.database.dao.DAO;
import org.openaion.loginserver.model.BannedIP;


/**
 * DAO that manages Banned IPs
 * 
 * @author SoulKeeper
 */
public abstract class BannedIpDAO implements DAO
{
	/**
	 * Inserts ip mask to database, returns BannedIP object that represents inserted mask or null if error.<br>
	 * Expire time is null so ban never expires.<br>
	 * 
	 * @param mask
	 *            ip mask to ban
	 * @return BannedIP object represetns mask or null if error happened
	 */
	public abstract BannedIP insert(String mask);

	/**
	 * Inserts ip mask to database with given expireTime.<br>
	 * Null is allowed for expire time in case of infinite ban.<br>
	 * Returns object that represents ip mask or null in case of error.<br>
	 * 
	 * @param mask
	 *            ip mask to ban
	 * @param expireTime
	 *            expiration time of ban
	 * @return object that represetns added ban or null in case of error
	 */
	public abstract BannedIP insert(String mask, Timestamp expireTime);

	/**
	 * Inserts BannedIP object to database.<br>
	 * ID of object must be NULL.<br>
	 * If insert was successfull - sets the assigned id to BannedIP object and returns true.<br>
	 * In case of error returns false without modification of bannedIP object.<br>
	 * 
	 * @param bannedIP
	 *            record to add to db
	 * @return true in case of success or false
	 */
	public abstract boolean insert(BannedIP bannedIP);

	/**
	 * Updates BannedIP object.<br>
	 * ID of object must NOT be null.<br>
	 * In case of success returns true.<br>
	 * In case of error returns false.<br>
	 * 
	 * @param bannedIP
	 *            record to update
	 * @return true in case of success or false in other case
	 */
	public abstract boolean update(BannedIP bannedIP);

	/**
	 * Removes ban by mask.<br>
	 * Returns true in case of success, false othervise.<br>
	 * 
	 * @param mask
	 *            ip mask to remove
	 * @return true in case of success, false in other case
	 */
	public abstract boolean remove(String mask);

	/**
	 * Removes BannedIP record by ID. Id must not be null.<br>
	 * Returns true in case of success, false in case of error
	 * 
	 * @param bannedIP
	 *            record to unban
	 * @return true if removeas wass successfull, false in case of error
	 */
	public abstract boolean remove(BannedIP bannedIP);

	/**
	 * Returns all bans from database.
	 * 
	 * @return all bans from database.
	 */
	public abstract Set<BannedIP> getAllBans();

	/**
	 * Returns class name that will be uses as unique identifier for all DAO classes
	 * 
	 * @return class name
	 */
	@Override
	public final String getClassName()
	{
		return BannedIpDAO.class.getName();
	}
}
