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
package quest.theobomos;

import java.util.Collections;

import org.openaion.gameserver.model.EmotionType;
import org.openaion.gameserver.model.gameobjects.Item;
import org.openaion.gameserver.model.gameobjects.Npc;
import org.openaion.gameserver.model.gameobjects.player.Player;
import org.openaion.gameserver.model.templates.quest.QuestItems;
import org.openaion.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import org.openaion.gameserver.network.aion.serverpackets.SM_EMOTION;
import org.openaion.gameserver.network.aion.serverpackets.SM_PLAY_MOVIE;
import org.openaion.gameserver.network.aion.serverpackets.SM_USE_OBJECT;
import org.openaion.gameserver.quest.HandlerResult;
import org.openaion.gameserver.quest.handlers.QuestHandler;
import org.openaion.gameserver.quest.model.QuestCookie;
import org.openaion.gameserver.quest.model.QuestState;
import org.openaion.gameserver.quest.model.QuestStatus;
import org.openaion.gameserver.services.ItemService;
import org.openaion.gameserver.services.QuestService;
import org.openaion.gameserver.skill.SkillEngine;
import org.openaion.gameserver.utils.PacketSendUtility;
import org.openaion.gameserver.utils.ThreadPoolManager;

 

/**
 * @author Hellboy Aion4Free
 * 
 */
public class _1093TheCalydonRuins extends QuestHandler
{
	private final static int	questId	= 1093;
	private final static int[]	npc_ids	= { 798155, 203784, 798176, 700391, 700392, 700393, 798212 };

	public _1093TheCalydonRuins()
	{
		super(questId);
	}

	@Override
	public void register()
	{
		qe.addQuestLvlUp(questId);
		qe.setQuestItemIds(182208013).add(questId);
		for(int npc_id : npc_ids)
			qe.setNpcQuestData(npc_id).addOnTalkEvent(questId);	 
	}

	@Override
	public boolean onLvlUpEvent(QuestCookie env)
	{
		return defaultQuestOnLvlUpEvent(env);
	}

	@Override
	public boolean onDialogEvent(final QuestCookie env)
	{
		final Player player = env.getPlayer();
		final QuestState qs = player.getQuestStateList().getQuestState(questId);
		if(qs == null)
			return false;

		int var = qs.getQuestVarById(0);
		int targetId = 0;
		if(env.getVisibleObject() instanceof Npc)
			targetId = ((Npc) env.getVisibleObject()).getNpcId();

		if(qs.getStatus() == QuestStatus.REWARD)
		{
			if(targetId == 798155)
			{
				if(env.getDialogId() == -1)
					return sendQuestDialog(env, 10002);
				else if(env.getDialogId() == 1009)
					return sendQuestDialog(env, 5);
				else return defaultQuestEndDialog(env);
			}
			return false;
		}
		else if(qs.getStatus() != QuestStatus.START)
		{
			return false;
		}
		if(targetId == 798155)
		{
			switch(env.getDialogId())
			{
				case 26:
					if(var == 0)
						return sendQuestDialog(env, 1011);
				case 10000:
					if(var == 0)
					{
						qs.setQuestVarById(0, var + 1);
						updateQuestStatus(env);
						PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
						return true;
					}				
			}
		}
		else if(targetId == 203784)
		{
			switch(env.getDialogId())
			{
				case 26:
					if(var == 1)
						return sendQuestDialog(env, 1352);
				case 10001:
					if(var == 1)
					{
						qs.setQuestVarById(0, var + 1);
						updateQuestStatus(env);
						ItemService.addItems(player, Collections.singletonList(new QuestItems(182208013, 1)));
						PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
						return true;
					}					
			}
		}
		else if(targetId == 798176)
		{
			switch(env.getDialogId())
			{
				case 26:
					if(var == 2)
						return sendQuestDialog(env, 1693);
				case 1694:
					PacketSendUtility.sendPacket(player, new SM_PLAY_MOVIE(0, 365));
						break;			
				case 10002:
					if(var == 2)
					{
						qs.setQuestVarById(0, var + 1);
						updateQuestStatus(env);
						PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
						return true;
					}					
			}
		}
		else if(targetId == 798212)
		{
			switch(env.getDialogId())
			{
				case 26:
					if(var == 7)
						return sendQuestDialog(env, 3398);
					if(var == 8)
						return sendQuestDialog(env, 3739);	
				case 10003:
					if(var == 7)
					{
						PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
						return true;
					}
				case 10255:
					if(var == 8)
					{
						qs.setStatus(QuestStatus.REWARD);
						updateQuestStatus(env);
						PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
						return true;
					}	
				case 34:
					if (var == 7)
					{
						if(QuestService.collectItemCheck(env, true))
						{
							qs.setQuestVarById(0, var + 1);
							updateQuestStatus(env);
							return sendQuestDialog(env, 3739);
						}
						else
							return sendQuestDialog(env, 10001);
					}		
			}
		}
		else if(targetId == 700391)
		{
					switch(env.getDialogId())
					{						
						case -1:
						if(var == 4)
						{
										final int targetObjectId = env.getVisibleObject().getObjectId();
										PacketSendUtility.sendPacket(player, new SM_USE_OBJECT(player.getObjectId(), targetObjectId, 3000,
											1));
										PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.NEUTRALMODE2, 0,
											targetObjectId), true);
										ThreadPoolManager.getInstance().schedule(new Runnable(){
											@Override
											public void run()
											{
												Npc npc = (Npc)player.getTarget();
											if(npc == null || npc.getObjectId() != targetObjectId)
												return;
											PacketSendUtility.sendPacket(player, new SM_USE_OBJECT(player.getObjectId(),
												targetObjectId, 3000, 0));
											PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.START_LOOT, 0,
												targetObjectId), true);
												
												ItemService.addItems(player, Collections.singletonList(new QuestItems(182208014, 1)));
												qs.setQuestVarById(0, 5);
												updateQuestStatus(env);
											}
									
								}, 3000);
								return false;
						}	
					}
		}
		else if(targetId == 700392)
		{
					switch(env.getDialogId())
					{						
						case -1:
						if(var == 5)
						{
										final int targetObjectId = env.getVisibleObject().getObjectId();
										PacketSendUtility.sendPacket(player, new SM_USE_OBJECT(player.getObjectId(), targetObjectId, 3000,
											1));
										PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.NEUTRALMODE2, 0,
											targetObjectId), true);
										ThreadPoolManager.getInstance().schedule(new Runnable(){
											@Override
											public void run()
											{
												Npc npc = (Npc)player.getTarget();
											if(npc == null || npc.getObjectId() != targetObjectId)
												return;
											PacketSendUtility.sendPacket(player, new SM_USE_OBJECT(player.getObjectId(),
												targetObjectId, 3000, 0));
											PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.START_LOOT, 0,
												targetObjectId), true);
												
												ItemService.addItems(player, Collections.singletonList(new QuestItems(182208015, 1)));
												qs.setQuestVarById(0, 6);
												updateQuestStatus(env);
											}
									
								}, 3000);
								return false;
						}	
					}
		}
		else if(targetId == 700393)
		{
					switch(env.getDialogId())
					{						
						case -1:
						if(var == 6)
						{
										final int targetObjectId = env.getVisibleObject().getObjectId();
										PacketSendUtility.sendPacket(player, new SM_USE_OBJECT(player.getObjectId(), targetObjectId, 3000,
											1));
										PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.NEUTRALMODE2, 0,
											targetObjectId), true);
										ThreadPoolManager.getInstance().schedule(new Runnable(){
											@Override
											public void run()
											{
												Npc npc = (Npc)player.getTarget();
											if(npc == null || npc.getObjectId() != targetObjectId)
												return;
											PacketSendUtility.sendPacket(player, new SM_USE_OBJECT(player.getObjectId(),
												targetObjectId, 3000, 0));
											PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.START_LOOT, 0,
												targetObjectId), true);
												
												ItemService.addItems(player, Collections.singletonList(new QuestItems(182208016, 1)));
												qs.setQuestVarById(0, 7);
												updateQuestStatus(env);
											}
									
								}, 3000);
								return false;
						}	
					}
		}
		return false;
	}
	
	@Override
	public HandlerResult onItemUseEvent(QuestCookie env, Item item)
	{
		final Player player = env.getPlayer();
		final QuestState qs = player.getQuestStateList().getQuestState(questId);
		final int id = item.getItemTemplate().getTemplateId();

		if(id != 182208013)
			return HandlerResult.UNKNOWN;
		
		if(qs == null || qs.getQuestVarById(0) != 3)
			return HandlerResult.FAILED;
			
		player.getInventory().removeFromBagByItemId(182208013, 1);
		SkillEngine.getInstance().getSkill(player, 18469, 1, player).useSkill();
		qs.setQuestVarById(0, qs.getQuestVarById(0) + 1);
		updateQuestStatus(env);
		return HandlerResult.SUCCESS;
	}	
}
