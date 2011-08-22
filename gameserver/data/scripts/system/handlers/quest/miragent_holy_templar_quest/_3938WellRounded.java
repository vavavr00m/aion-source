/*
 * This file is part of aion-unique <aion-unique.org>
 *
 *  aion-unique is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-unique is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser Public License
 *  along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package quest.miragent_holy_templar_quest;

import org.openaion.gameserver.model.gameobjects.player.Player;
import org.openaion.gameserver.quest.handlers.QuestHandler;
import org.openaion.gameserver.quest.model.QuestCookie;
import org.openaion.gameserver.quest.model.QuestState;
import org.openaion.gameserver.quest.model.QuestStatus;


public class _3938WellRounded extends QuestHandler
{
	private final static int	questId	= 3938;
	private final static int[]	npcs = {203701, 203788, 203792, 203790, 203793, 203784, 203786, 798316, 203752};

	public _3938WellRounded()
	{
		super(questId);
	}
	
	@Override
	public boolean onDialogEvent(QuestCookie env)
	{
		Player player = env.getPlayer();
		
		if(defaultQuestNoneDialog(env, 203701, 4762))
			return true;

		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if(qs == null)
			return false;

		int var = qs.getQuestVarById(0);
		if(qs.getStatus() == QuestStatus.START)
		{
			switch(env.getTargetId())
			{
				case 203701:
				{
					switch(env.getDialogId())
					{
						case 26:
							if(var == 0)
								return sendQuestDialog(env, 1011);
						case 10000:
							return defaultCloseDialog(env, 0, 1);
						case 10001:
							return defaultCloseDialog(env, 0, 2);
						case 10002:
							return defaultCloseDialog(env, 0, 3);
						case 10003:
							return defaultCloseDialog(env, 0, 4);
						case 10004:
							return defaultCloseDialog(env, 0, 5);
						case 10005:
							return defaultCloseDialog(env, 0, 6);
					}
				}	break;
				case 203788:
				{
					switch(env.getDialogId())
					{
						case 26:
							if(var == 1)
								return sendQuestDialog(env, 1352);
						case 10006:
							return defaultCloseDialog(env, 1, 7, 152201596, 1, 0, 0);
					}
				} break;
				case 203792:
				{
					switch(env.getDialogId())
					{
						case 26:
							if(var == 2)
								return sendQuestDialog(env, 1693);
						case 10006:
							return defaultCloseDialog(env, 2, 7, 152201639, 1, 0, 0);
					}
				} break;
				case 203790:
				{
					switch(env.getDialogId())
					{
						case 26:
							if(var == 3)
								return sendQuestDialog(env, 2034);
						case 10006:
							return defaultCloseDialog(env, 3, 7, 152201615, 1, 0, 0);
					}
				} break;
				case 203793:
				{
					switch(env.getDialogId())
					{
						case 26:
							if(var == 4)
								return sendQuestDialog(env, 2375);
						case 10006:
							return defaultCloseDialog(env, 4, 7, 152201632, 1, 0, 0);
					}
				} break;
				case 203784:
				{
					switch(env.getDialogId())
					{
						case 26:
							if(var == 5)
								return sendQuestDialog(env, 2716);
						case 10006:
							return defaultCloseDialog(env, 5, 7, 152201644, 1, 0, 0);
					}
				} break;
				case 203786:
				{
					switch(env.getDialogId())
					{
						case 26:
							if(var == 6)
								return sendQuestDialog(env, 3057);
						case 10006:
							return defaultCloseDialog(env, 6, 7, 152201643, 1, 0, 0);
					}
				} break;
				case 798316:
				{
					switch(env.getDialogId())
					{
						case 26:
							if(var == 7)
								return sendQuestDialog(env, 3398);
						case 34:
							return defaultQuestItemCheck(env, 7, 8, false, 10000, 10001, 182206096, 1);
					}
				} break;
				case 203752:
				{
					switch(env.getDialogId())
					{
						case 26:
							if(var == 8)
								return sendQuestDialog(env, 3739);
						case 10255:
							if(player.getInventory().getItemCountByItemId(186000081) >= 1)
								return defaultCloseDialog(env, 8, 0, true, false, 0, 0, 186000081, 1);
							else
								return sendQuestDialog(env, 3825);
					}
				} break;
			}
		}
		return defaultQuestRewardDialog(env, 203701, 10002);
	}

	@Override
	public void register()
	{
		qe.setNpcQuestData(203701).addOnQuestStart(questId);
		for(int npc: npcs)
			qe.setNpcQuestData(npc).addOnTalkEvent(questId);
	}
}
