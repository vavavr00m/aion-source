/*
 * This file is part of aion-unique <aion-unique.org>.
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
package quest.verteron;

import java.util.Collections;

import org.openaion.gameserver.model.gameobjects.Npc;
import org.openaion.gameserver.model.gameobjects.player.Player;
import org.openaion.gameserver.model.templates.quest.QuestItems;
import org.openaion.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import org.openaion.gameserver.quest.handlers.QuestHandler;
import org.openaion.gameserver.quest.model.QuestCookie;
import org.openaion.gameserver.quest.model.QuestState;
import org.openaion.gameserver.quest.model.QuestStatus;
import org.openaion.gameserver.services.ItemService;
import org.openaion.gameserver.services.QuestService;
import org.openaion.gameserver.utils.PacketSendUtility;


/**
 * @author MrPoke remod by Nephis, Rolandas
 * 
 */
public class _1197KrallBook extends QuestHandler
{
	private final static int	questId	= 1197;

	public _1197KrallBook()
	{
		super(questId);
	}

	@Override
	public void register()
	{
		qe.setNpcQuestData(700004).addOnTalkEvent(questId);
		qe.setNpcQuestData(203129).addOnTalkEvent(questId);
	}

	@Override
	public boolean onDialogEvent(QuestCookie env)
	{
		final Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);

		int targetId = 0;
		if(env.getVisibleObject() instanceof Npc)
			targetId = ((Npc) env.getVisibleObject()).getNpcId();
		if(targetId == 0)
		{
			if(env.getDialogId() == 1002)
			{
				QuestService.startQuest(env, QuestStatus.START);
				PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(0, 0));
				return true;
			}
		}
		else if(targetId == 700004)
		{
			if ((qs == null || qs.getStatus() == QuestStatus.NONE))
			{
				if(player.getInventory().getItemCountByItemId(182200558) == 0)
				{
					if (ItemService.addItems(player, Collections.singletonList(new QuestItems(182200558, 1))))
						((Npc)player.getTarget()).getController().onDespawn(true);
				}
			}
			return true;
		}
		else if(targetId == 203129)
		{
			if(qs != null)
			{
				if(env.getDialogId() == 26 && qs.getStatus() == QuestStatus.START)
				{
					return sendQuestDialog(env, 2375);
				}
				else if(env.getDialogId() == 1009)
				{
					player.getInventory().removeFromBagByItemId(182200558, 1);
					qs.setQuestVar(1);
					qs.setStatus(QuestStatus.REWARD);
					updateQuestStatus(env);
					return defaultQuestEndDialog(env);
				}
				else
					return defaultQuestEndDialog(env);
			}
		}
		return false;
	}
}
