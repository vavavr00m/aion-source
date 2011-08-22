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
package org.openaion.gameserver.network.aion.serverpackets;

import java.nio.ByteBuffer;

import org.openaion.gameserver.network.aion.AionConnection;
import org.openaion.gameserver.network.aion.AionServerPacket;


/**
 * 
 * @author Simple
 * 
 */
public class SM_LEGION_LEAVE_MEMBER extends AionServerPacket
{
	private String	name;
	private String	name1;
	private int		playerObjId;
	private int		msgId;

	public SM_LEGION_LEAVE_MEMBER(int msgId, int playerObjId, String name)
	{
		this.msgId = msgId;
		this.playerObjId = playerObjId;
		this.name = name;
	}

	public SM_LEGION_LEAVE_MEMBER(int msgId, int playerObjId, String name, String name1)
	{
		this.msgId = msgId;
		this.playerObjId = playerObjId;
		this.name = name;
		this.name1 = name1;
	}

	@Override
	public void writeImpl(AionConnection con, ByteBuffer buf)
	{
		writeD(buf, playerObjId);
		writeC(buf, 0x00); // isMember ? 1 : 0
		writeD(buf, 0x00); // unix time for log off
		writeD(buf, msgId);
		writeS(buf, name);
		writeS(buf, name1);
	}
}