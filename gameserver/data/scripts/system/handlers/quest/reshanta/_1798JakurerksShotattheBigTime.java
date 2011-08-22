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
package quest.reshanta;


import org.openaion.gameserver.model.gameobjects.Npc;
import org.openaion.gameserver.model.gameobjects.player.Player;
import org.openaion.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import org.openaion.gameserver.quest.handlers.QuestHandler;
import org.openaion.gameserver.quest.model.QuestCookie;
import org.openaion.gameserver.quest.model.QuestState;
import org.openaion.gameserver.quest.model.QuestStatus;
import org.openaion.gameserver.utils.PacketSendUtility;


/**
 * @author Hilgert
 * 
 */
public class _1798JakurerksShotattheBigTime extends QuestHandler
{
	private final static int	questId	= 1798;

	public _1798JakurerksShotattheBigTime()
	{
		super(questId);
	}

	@Override
	public void register()
	{
		qe.setNpcQuestData(279007).addOnQuestStart(questId);
		qe.setNpcQuestData(279007).addOnTalkEvent(questId);
		qe.setNpcQuestData(263568).addOnTalkEvent(questId);
		qe.setNpcQuestData(263266).addOnTalkEvent(questId);
		qe.setNpcQuestData(264768).addOnTalkEvent(questId);
		qe.setNpcQuestData(271053).addOnTalkEvent(questId);
		qe.setNpcQuestData(266553).addOnTalkEvent(questId);
		qe.setNpcQuestData(270151).addOnTalkEvent(questId);
		qe.setNpcQuestData(269251).addOnTalkEvent(questId);
		qe.setNpcQuestData(268051).addOnTalkEvent(questId);
		qe.setNpcQuestData(260235).addOnTalkEvent(questId);
	}

	@Override
	public boolean onDialogEvent(QuestCookie env)
	{
        final Player player = env.getPlayer();
		int targetId = 0;
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if(env.getVisibleObject() instanceof Npc)
			targetId = ((Npc) env.getVisibleObject()).getNpcId();

		if(targetId == 279007)
		{
			if(qs == null || qs.getStatus() == QuestStatus.NONE)
			{
				if(env.getDialogId() == 26)
					return sendQuestDialog(env, 4762);
				else 
					return defaultQuestStartDialog(env);
			}
			else if(qs != null && qs.getStatus() == QuestStatus.REWARD)
				return defaultQuestEndDialog(env);
		}
		
		else if(qs != null && qs.getStatus() == QuestStatus.START)
		{
			if(targetId == 263568)
			{
				if(env.getDialogId() == 26)
					 return sendQuestDialog(env, 1011);
				else if(env.getDialogId() == 10000)
				{
					 qs.setQuestVarById(0, qs.getQuestVarById(0) + 1);
					 updateQuestStatus(env);
					 PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
					 return true;
				}
			}	
			
			else if(targetId == 263266)
			{
				if(env.getDialogId() == 26)
					 return sendQuestDialog(env, 1352);
				else if(env.getDialogId() == 10001)
				{
					 qs.setQuestVarById(0, qs.getQuestVarById(0) + 1);
					 updateQuestStatus(env);
					 PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
					 return true;
				}
			}
			
			else if(targetId == 264768)
			{
				if(env.getDialogId() == 26)
					 return sendQuestDialog(env, 1693);
				else if(env.getDialogId() == 10002)
				{
					 qs.setQuestVarById(0, qs.getQuestVarById(0) + 1);
					 updateQuestStatus(env);
					 PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
					 return true;
				}
			}
			
			else if(targetId == 271053)
			{
				if(env.getDialogId() == 26)
					 return sendQuestDialog(env, 2035);
				else if(env.getDialogId() == 10003)
				{
					 qs.setQuestVarById(0, qs.getQuestVarById(0) + 1);
					 updateQuestStatus(env);
					 PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
					 return true;
				}
			}
			
			else if(targetId == 266553)
			{
				if(env.getDialogId() == 26)
					 return sendQuestDialog(env, 2375);
				else if(env.getDialogId() == 10004)
				{
					 qs.setQuestVarById(0, qs.getQuestVarById(0) + 1);
					 updateQuestStatus(env);
					 PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
					 return true;
				}
			}
			
			else if(targetId == 270151)
			{
				if(env.getDialogId() == 26)
					 return sendQuestDialog(env, 2716);
				else if(env.getDialogId() == 10005)
				{
					 qs.setQuestVarById(0, qs.getQuestVarById(0) + 1);
					 updateQuestStatus(env);
					 PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
					 return true;
				}
			}
			
			else if(targetId == 269251)
			{
				if(env.getDialogId() == 26)
					 return sendQuestDialog(env, 3057);
				else if(env.getDialogId() == 10006)
				{
					 qs.setQuestVarById(0, qs.getQuestVarById(0) + 1);
					 updateQuestStatus(env);
					 PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
					 return true;
				}
			}
			
			else if(targetId == 268051)
			{
				if(env.getDialogId() == 26)
					 return sendQuestDialog(env, 3398);
				else if(env.getDialogId() == 10007)
				{
					 qs.setQuestVarById(0, qs.getQuestVarById(0) + 1);
					 updateQuestStatus(env);
					 PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
					 return true;
				}
			}
			
			else if(targetId == 260235)
			{
				if(env.getDialogId() == 26)
					 return sendQuestDialog(env, 3740);
				else if(env.getDialogId() == 10255)
				{
					 qs.setQuestVarById(0, qs.getQuestVarById(0) + 1);
					 qs.setStatus(QuestStatus.REWARD);
					 updateQuestStatus(env);
					 PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
					 return true;
				}
			}
		}
	return false;
    }		
}
