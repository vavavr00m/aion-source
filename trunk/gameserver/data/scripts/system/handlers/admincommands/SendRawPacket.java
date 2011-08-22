/*
 * This file is part of aion-emu <aion-emu.com>.
 *
 *  aion-emu is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-emu is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-emu.  If not, see <http://www.gnu.org/licenses/>.
 */
package admincommands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openaion.gameserver.configs.administration.AdminConfig;
import org.openaion.gameserver.model.gameobjects.player.Player;
import org.openaion.gameserver.network.aion.serverpackets.SM_CUSTOM_PACKET;
import org.openaion.gameserver.network.aion.serverpackets.SM_CUSTOM_PACKET.PacketElementType;
import org.openaion.gameserver.utils.PacketSendUtility;
import org.openaion.gameserver.utils.chathandlers.AdminCommand;


/**
 * Send packet in raw format.
 *
 * @author Luno
 * @author Aquanox
 */

public class SendRawPacket extends AdminCommand
{
	private static final File ROOT = new File("data/packets/");

	private static final Logger logger = Logger.getLogger(SendRawPacket.class);

	public SendRawPacket()
	{
		super("raw");
	}

	@Override
	public void executeCommand(Player admin, String[] params)
	{
		if (admin.getAccessLevel() < AdminConfig.COMMAND_SENDRAWPACKET)
		{
			PacketSendUtility.sendMessage(admin, "You dont have enough rights to execute this command");
			return;
		}

		if (params.length != 1)
		{
			PacketSendUtility.sendMessage(admin, "Usage: //raw [name]");
			return;
		}

		File file = new File(ROOT, params[0]+".txt");

		if (!file.exists() || !file.canRead())
		{
			PacketSendUtility.sendMessage(admin, "Wrong file selected.");
			return;
		}

		try
		{
			List<String> lines = FileUtils.readLines(file);

			SM_CUSTOM_PACKET packet = null;

			for (String row : lines)
			{
				String[] tokens = row.substring(0,48).trim().split(" ");
				int len = tokens.length;

				for (int i = 0; i < len; i++)
				{
					if (i == 0)
					{
						packet = new SM_CUSTOM_PACKET(Integer.valueOf(tokens[i], 16));
					}
					else if (i > 2)
					{
						packet.addElement(PacketElementType.C, "0x" + tokens[i]);
					}
				}
			}

			if (packet != null)
				PacketSendUtility.sendPacket(admin, packet);
		}
		catch(IOException e)
		{
			PacketSendUtility.sendMessage(admin, "An error has occurred.");
			logger.warn("IO Error.", e);
		}
	}
}
