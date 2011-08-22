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
package quest.beluslan;

import org.openaion.gameserver.model.gameobjects.Npc;
import org.openaion.gameserver.model.gameobjects.player.Player;
import org.openaion.gameserver.quest.handlers.QuestHandler;
import org.openaion.gameserver.quest.model.QuestCookie;
import org.openaion.gameserver.quest.model.QuestState;
import org.openaion.gameserver.quest.model.QuestStatus;
import org.openaion.gameserver.services.QuestService;


/**
 * @author Nephis
 * 
 */
public class _2691SpysAdvice extends QuestHandler
{
	private final static int	questId	= 2691;

	public _2691SpysAdvice()
	{
		super(questId);
	}

	@Override
	public void register()
	{
		qe.setNpcQuestData(204777).addOnQuestStart(questId);
		qe.setNpcQuestData(204777).addOnTalkEvent(questId);
		qe.setNpcQuestData(700356).addOnTalkEvent(questId);
		qe.setNpcQuestData(700357).addOnTalkEvent(questId);
		qe.setNpcQuestData(700358).addOnTalkEvent(questId);
		qe.setNpcQuestData(204225).addOnTalkEvent(questId);
	}

	@Override
	public boolean onDialogEvent(QuestCookie env)
	{
		final Player player = env.getPlayer();
		int targetId = 0;
		if(env.getVisibleObject() instanceof Npc)
			targetId = ((Npc) env.getVisibleObject()).getNpcId();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if(targetId == 204777)
		{
			if(qs == null || qs.getStatus() == QuestStatus.NONE)
			{
				if(env.getDialogId() == 26)
					return sendQuestDialog(env, 1011);
				else
					return defaultQuestStartDialog(env);
			}
		}
		else if(targetId == 204225)
		{
			if(qs != null && qs.getStatus() == QuestStatus.START)
			{
				if(env.getDialogId() == 26)
					return sendQuestDialog(env, 2375);
				else if(env.getDialogId() == 34)
				{
					if(QuestService.collectItemCheck(env, true))
					{
						qs.setStatus(QuestStatus.REWARD);
						updateQuestStatus(env);
						return sendQuestDialog(env, 5);
					}
					else
						return sendQuestDialog(env, 2716);
				}
				else
					return defaultQuestEndDialog(env);
			}

			else if(qs != null && qs.getStatus() == QuestStatus.REWARD)
				return defaultQuestEndDialog(env);
		}

		else if(qs != null && qs.getStatus() == QuestStatus.START)
		{
			switch(targetId)
			{
				case 700356:
				case 700357:
				case 700358:
				{
					if(qs.getQuestVarById(0) == 0 && env.getDialogId() == -1)
						return true;
				}
			}
		}
		return false;
	}
}
