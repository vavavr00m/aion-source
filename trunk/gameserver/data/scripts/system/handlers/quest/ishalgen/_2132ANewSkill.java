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
package quest.ishalgen;

import org.openaion.gameserver.model.PlayerClass;
import org.openaion.gameserver.model.gameobjects.player.Player;
import org.openaion.gameserver.quest.handlers.QuestHandler;
import org.openaion.gameserver.quest.model.QuestCookie;
import org.openaion.gameserver.quest.model.QuestState;
import org.openaion.gameserver.quest.model.QuestStatus;
import org.openaion.gameserver.services.QuestService;


/**
 * @author MrPoke
 * 
 */
public class _2132ANewSkill extends QuestHandler
{
	private final static int	questId	= 2132;

	public _2132ANewSkill()
	{
		super(questId);
	}

	@Override
	public void register()
	{
		qe.addQuestLvlUp(questId);
		qe.setNpcQuestData(203527).addOnTalkEvent(questId); // Warrior
		qe.setNpcQuestData(203528).addOnTalkEvent(questId); // Scout
		qe.setNpcQuestData(203529).addOnTalkEvent(questId); // Mage
		qe.setNpcQuestData(203530).addOnTalkEvent(questId); // Priest
	}

	@Override
	public boolean onLvlUpEvent(QuestCookie env)
	{
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		boolean lvlCheck = QuestService.checkLevelRequirement(questId, player.getCommonData().getLevel());
		if (!lvlCheck)
			return false;
		if(qs != null)
			return false;
		if (QuestService.startQuest(env, QuestStatus.START))
		{
			qs = player.getQuestStateList().getQuestState(questId);
			qs.setStatus(QuestStatus.REWARD);
			switch(PlayerClass.getStartingClassFor(player.getCommonData().getPlayerClass()))
			{
				case WARRIOR:
					qs.setQuestVar(1);
					break;
				case SCOUT:
					qs.setQuestVar(2);
					break;
				case MAGE:
					qs.setQuestVar(3);
					break;
				case PRIEST:
					qs.setQuestVar(4);
					break;
			}
			updateQuestStatus(env);
		}
		return true;
	}

	@Override
	public boolean onDialogEvent(QuestCookie env)
	{
		final Player player = env.getPlayer();
		final QuestState qs = player.getQuestStateList().getQuestState(questId);
		if(qs == null || qs.getStatus() != QuestStatus.REWARD)
			return false;

		switch(PlayerClass.getStartingClassFor(player.getCommonData().getPlayerClass()))
		{
			case WARRIOR:
				return defaultQuestRewardDialog(env, 203527, 1011, 0);
			case SCOUT:
				return defaultQuestRewardDialog(env, 203528, 1352, 1);
			case MAGE:
				return defaultQuestRewardDialog(env, 203529, 1693, 2);
			case PRIEST:
				return defaultQuestRewardDialog(env, 203530, 2034, 3);
		}
		return false;
	}
}
