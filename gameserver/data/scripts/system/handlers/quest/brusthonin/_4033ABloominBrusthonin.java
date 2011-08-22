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
package quest.brusthonin;

import org.openaion.gameserver.model.EmotionType;
import org.openaion.gameserver.model.gameobjects.Npc;
import org.openaion.gameserver.model.gameobjects.player.Player;
import org.openaion.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import org.openaion.gameserver.network.aion.serverpackets.SM_EMOTION;
import org.openaion.gameserver.network.aion.serverpackets.SM_USE_OBJECT;
import org.openaion.gameserver.quest.handlers.QuestHandler;
import org.openaion.gameserver.quest.model.QuestCookie;
import org.openaion.gameserver.quest.model.QuestState;
import org.openaion.gameserver.quest.model.QuestStatus;
import org.openaion.gameserver.services.QuestService;
import org.openaion.gameserver.utils.PacketSendUtility;
import org.openaion.gameserver.utils.ThreadPoolManager;


/**
 * @author Nephis
 * 
 */
public class _4033ABloominBrusthonin extends QuestHandler
{
	private final static int	questId	= 4033;

	public _4033ABloominBrusthonin()
	{
		super(questId);
	}

	@Override
	public void register()
	{
		qe.setNpcQuestData(205155).addOnQuestStart(questId); //Heintz
		qe.setNpcQuestData(205155).addOnTalkEvent(questId);
		qe.setNpcQuestData(700379).addOnTalkEvent(questId); //Portaro's Tomb
	}

	@Override
	public boolean onDialogEvent(final QuestCookie env)
	{
		final Player player = env.getPlayer();
		int targetId = 0;
		if(env.getVisibleObject() instanceof Npc)
			targetId = ((Npc) env.getVisibleObject()).getNpcId();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if(targetId == 205155) //Heintz
		{
			if(qs == null || qs.getStatus() == QuestStatus.NONE)
			{
				if(env.getDialogId() == 26)
					return sendQuestDialog(env, 1011);
				else
					return defaultQuestStartDialog(env);
			}
			
			else if(qs != null && qs.getStatus() == QuestStatus.START && qs.getQuestVarById(0) == 0)
			{
				if(env.getDialogId() == 26)
					return sendQuestDialog(env, 1352);
				else if(env.getDialogId() == 34)
				{
					if(QuestService.collectItemCheck(env, true))
					{
						qs.setQuestVarById(0, qs.getQuestVarById(0) + 2);
						updateQuestStatus(env);
						PacketSendUtility
							.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
							return true;
					}
					else
						return sendQuestDialog(env, 1438);
				}
				else
					return defaultQuestStartDialog(env);
			}
			
			else if(qs != null && qs.getStatus() == QuestStatus.REWARD)
			{
				if(env.getDialogId() == 26)
					return sendQuestDialog(env, 2034);
				else if(env.getDialogId() == 1009)
				{
					qs.setStatus(QuestStatus.REWARD);
					updateQuestStatus(env);
					return defaultQuestEndDialog(env);
				}
				else
					return defaultQuestEndDialog(env);
			}
		}		
		
		else if (qs != null && qs.getStatus() == QuestStatus.START)
		{
			switch(targetId)
			{
				case 700379: //Portaro's Tomb
				{
					if (qs.getQuestVarById(0) == 2 && env.getDialogId() == -1)
					{

						final int targetObjectId = env.getVisibleObject().getObjectId();
						PacketSendUtility.sendPacket(player, new SM_USE_OBJECT(player.getObjectId(), targetObjectId, 3000,
							1));
						PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.NEUTRALMODE2, 0,
							targetObjectId), true);
						ThreadPoolManager.getInstance().schedule(new Runnable(){
						final QuestState qs = player.getQuestStateList().getQuestState(questId);
							@Override
							public void run()
							{										
								qs.setStatus(QuestStatus.REWARD);
								updateQuestStatus(env);
								if(player.getTarget() == null || player.getTarget().getObjectId() != targetObjectId)
									return;
								PacketSendUtility.sendPacket(player, new SM_USE_OBJECT(player.getObjectId(),
									targetObjectId, 3000, 0));
								PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.START_LOOT, 0,
									targetObjectId), true);
							}
						}, 3000);
					}
				}

			}
		}
		
		return false;
	}
}
