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

import org.openaion.gameserver.model.gameobjects.Npc;
import org.openaion.gameserver.model.gameobjects.player.Player;
import org.openaion.gameserver.quest.handlers.QuestHandler;
import org.openaion.gameserver.quest.model.QuestCookie;
import org.openaion.gameserver.quest.model.QuestState;
import org.openaion.gameserver.quest.model.QuestStatus;


/**
 * @author MrPoke
 * 
 */
public class _1131UndeliveredArmor extends QuestHandler
{
	private final static int	questId	= 1131;

	public _1131UndeliveredArmor()
	{
		super(questId);
	}

	@Override
	public void register()
	{
		qe.setNpcQuestData(203097).addOnQuestStart(questId);
		qe.setNpcQuestData(203097).addOnTalkEvent(questId);
		qe.setNpcQuestData(798001).addOnTalkEvent(questId);
		qe.setNpcQuestData(203101).addOnTalkEvent(questId);
	}

	@Override
	public boolean onDialogEvent(QuestCookie env)
	{
		final Player player = env.getPlayer();
		int targetId = 0;
		if(env.getVisibleObject() instanceof Npc)
			targetId = ((Npc) env.getVisibleObject()).getNpcId();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if(targetId == 203097)
		{
			if(qs == null)
			{
				if(env.getDialogId() == 26)
					return sendQuestDialog(env, 1011);
				else if (env.getDialogId() == 1007)
					return sendQuestDialog(env, 4);
				else
					return defaultQuestStartItem(env, 182200506, 1, 0, 0);
			}
		}
		else if(targetId == 798001)
		{
			if(qs != null && qs.getStatus() == QuestStatus.START && qs.getQuestVarById(0) == 0)
			{
				if(env.getDialogId() == 26)
					return sendQuestDialog(env, 1352);
				else if(env.getDialogId() == 10000)
					return defaultCloseDialog(env, 0, 1, 182200507, 1, 182200506, 1);
				else
					return defaultQuestStartDialog(env);
			}
		}
		else if(targetId == 203101)
		{
			if(qs != null)
			{
				if(env.getDialogId() == 26 && qs.getStatus() == QuestStatus.START)
					return sendQuestDialog(env, 2375);
				else if(env.getDialogId() == 1009)
					return defaultCloseDialog(env, 1, 2, true, true, 0, 0, 0, 182200507, 1);
				else
					return defaultQuestEndDialog(env);
			}
		}
		return false;
	}
}
