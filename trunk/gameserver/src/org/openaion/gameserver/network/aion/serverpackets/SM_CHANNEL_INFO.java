/*
 * This file is part of aion-unique <aionunique.com>.
 *
 * aion-unique is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * aion-unique is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openaion.gameserver.network.aion.serverpackets;

import java.nio.ByteBuffer;

import org.openaion.gameserver.network.aion.AionConnection;
import org.openaion.gameserver.network.aion.AionServerPacket;
import org.openaion.gameserver.world.WorldPosition;


/**
 * 
 * @author ATracer
 * 
 */
public class SM_CHANNEL_INFO extends AionServerPacket
{
	int instanceCount = 0;
	int currentChannel = 0;
	/**
	 * @param position
	 */
	public SM_CHANNEL_INFO(WorldPosition position)
	{
		this.instanceCount = position.getInstanceCount();
		this.currentChannel = position.getInstanceId() - 1;
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeImpl(AionConnection con, ByteBuffer buf)
	{
		writeD(buf, currentChannel);
		writeD(buf, instanceCount);
	}
}
