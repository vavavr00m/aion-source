package org.openaion.commons.database.dao;

import java.lang.reflect.Modifier;

import org.openaion.commons.scripting.classlistener.ClassListener;
import org.openaion.commons.scripting.classlistener.DefaultClassListener;
import org.openaion.commons.utils.ClassUtils;


/**
 * Utility class that loads all DAO's after script context initialization.<br>
 * DAO should be public, not abstract, not interface, must have default no-arg public constructor.
 * 
 * @author SoulKeeper, Aquanox
 */
class DAOLoader extends DefaultClassListener implements ClassListener
{
	@SuppressWarnings("unchecked")
	@Override
	public void postLoad(Class<?>[] classes)
	{
		// Register DAOs
		for(Class<?> clazz : classes)
		{
			if(!isValidDAO(clazz))
				continue;

			try
			{
				DAOManager.registerDAO((Class<? extends DAO>) clazz);
			}
			catch(Exception e)
			{
				throw new Error("Can't register DAO class", e);
			}
		}

		// Call methods marked with onClassLoad
		super.postLoad(classes);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void preUnload(Class<?>[] classes)
	{
		// Call methods marked with onClassUnload
		super.postLoad(classes);

		// Unregister DAO's
		for(Class<?> clazz : classes)
		{
			if(!isValidDAO(clazz))
				continue;

			try
			{
				DAOManager.unregisterDAO((Class<? extends DAO>) clazz);
			}
			catch(Exception e)
			{
				throw new Error("Can't unregister DAO class", e);
			}
		}
	}

	/**
	 * @param clazz
	 * @return boolean
	 */
	public boolean isValidDAO(Class<?> clazz)
	{
		if(!ClassUtils.isSubclass(clazz, DAO.class))
			return false;

		final int modifiers = clazz.getModifiers();

		if(Modifier.isAbstract(modifiers) || Modifier.isInterface(modifiers))
			return false;

		if(!Modifier.isPublic(modifiers))
			return false;

		if(clazz.isAnnotationPresent(DisabledDAO.class))
			return false;

		return true;
	}
}
