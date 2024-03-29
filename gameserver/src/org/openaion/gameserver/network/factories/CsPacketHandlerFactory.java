/*
 * This file is part of aion-unique <aion-unique.org>.
 *
 *  aion-unique is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-unique is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openaion.gameserver.network.factories;

import org.openaion.gameserver.network.chatserver.CsClientPacket;
import org.openaion.gameserver.network.chatserver.CsPacketHandler;
import org.openaion.gameserver.network.chatserver.ChatServerConnection.State;
import org.openaion.gameserver.network.chatserver.clientpackets.CM_CS_AUTH_RESPONSE;
import org.openaion.gameserver.network.chatserver.clientpackets.CM_CS_PLAYER_AUTH_RESPONSE;

/**
 * @author ATracer
 */
public class CsPacketHandlerFactory
{
	private CsPacketHandler	handler	= new CsPacketHandler();

	/**
	 * @param injector
	 */
	public CsPacketHandlerFactory()
	{
		addPacket(new CM_CS_AUTH_RESPONSE(0x00), State.CONNECTED);
		addPacket(new CM_CS_PLAYER_AUTH_RESPONSE(0x01), State.AUTHED);
	}

	/**
	 * 
	 * @param prototype
	 * @param states
	 */
	private void addPacket(CsClientPacket prototype, State... states)
	{
		handler.addPacketPrototype(prototype, states);
	}

	/**
	 * @return handler
	 */
	public CsPacketHandler getPacketHandler()
	{
		return handler;
	}
}