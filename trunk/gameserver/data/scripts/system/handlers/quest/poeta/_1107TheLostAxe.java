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
package quest.poeta;

import org.openaion.gameserver.model.gameobjects.player.Player;
import org.openaion.gameserver.quest.handlers.QuestHandler;
import org.openaion.gameserver.quest.model.QuestCookie;
import org.openaion.gameserver.quest.model.QuestState;
import org.openaion.gameserver.quest.model.QuestStatus;


/**
 * @author MrPoke
 * 
 */
public class _1107TheLostAxe extends QuestHandler
{
	private final static int	questId	= 1107;

	public _1107TheLostAxe()
	{
		super(questId);
	}

	@Override
	public void register()
	{
		qe.setNpcQuestData(203075).addOnTalkEvent(questId);
	}

	@Override
	public boolean onDialogEvent(QuestCookie env)
	{
		Player player = env.getPlayer();

		if(env.getTargetId() == 0)
			return defaultQuestStartItem(env);
		
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if(qs == null)
			return false;
		
		int var = qs.getQuestVarById(0);
		
		if(qs.getStatus() == QuestStatus.START)
		{
			if(env.getTargetId() == 203075)
			{
				switch(env.getDialogId())
				{
					case 26:
						if(var == 0)
							return sendQuestDialog(env, 2375);
					case 1009:
						defaultQuestRemoveItem(env, 182200501, 1);
						return defaultCloseDialog(env, 0, 1, true, true);
				}
			}
		}
		return defaultQuestRewardDialog(env, 203075, 0);
	}
}