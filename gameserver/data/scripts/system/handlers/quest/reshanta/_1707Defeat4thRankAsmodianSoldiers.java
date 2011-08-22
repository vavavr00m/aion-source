/*
 *  This file is part of Zetta-Core Engine <http://www.zetta-core.org>.
 *
 *  Zetta-Core is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation, either version 3 of the License,
 *  or (at your option) any later version.
 *
 *  Zetta-Core is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a  copy  of the GNU General Public License
 *  along with Zetta-Core.  If not, see <http://www.gnu.org/licenses/>.
 */
package quest.reshanta;

import org.openaion.gameserver.quest.handlers.QuestHandler;
import org.openaion.gameserver.quest.model.QuestCookie;
import org.openaion.gameserver.quest.model.QuestState;


/**
 * @author HellBoy
 *
 */
public class _1707Defeat4thRankAsmodianSoldiers extends QuestHandler
{
	private final static int	questId	= 1707;

	public _1707Defeat4thRankAsmodianSoldiers()
	{
		super(questId);		
	}

	@Override
	public void register()
	{
		qe.setNpcQuestData(278502).addOnQuestStart(questId);
		qe.setNpcQuestData(278502).addOnTalkEvent(questId);
		qe.addOnKillPlayer(questId);
	}

	@Override
	public boolean onKillEvent(QuestCookie env)
	{
		if(defaultQuestOnKillPlayerEvent(env, 6, 0, 9, false) || defaultQuestOnKillPlayerEvent(env, 6, 9, 10, true))
			return true;
		else
			return false;
	}

	@Override
	public boolean onDialogEvent(QuestCookie env)
	{
		QuestState qs = env.getPlayer().getQuestStateList().getQuestState(questId);
		
		if(defaultQuestNoneDialog(env, 278502))
			return true;
		if(qs == null)
			return false;
		return defaultQuestRewardDialog(env, 278502, 1352);
	}
}
