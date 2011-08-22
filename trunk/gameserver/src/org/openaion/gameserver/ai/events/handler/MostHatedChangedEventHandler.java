/*
 * This file is part of aion-unique <aion-unique.org>.
 *
 *  aion-unique is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-unique is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openaion.gameserver.ai.events.handler;

import org.openaion.gameserver.ai.AI;
import org.openaion.gameserver.ai.events.Event;
import org.openaion.gameserver.ai.state.AIState;
import org.openaion.gameserver.model.ShoutEventType;
import org.openaion.gameserver.model.gameobjects.Npc;
import org.openaion.gameserver.services.NpcShoutsService;

/**
 * @author ATracer
 *
 */
public class MostHatedChangedEventHandler implements EventHandler
{
	@Override
	public Event getEvent()
	{
		return Event.MOST_HATED_CHANGED;
	}

	@Override
	public void handleEvent(Event event, AI<?> ai)
	{
		ai.setAiState(AIState.THINKING);
		if(ai.getOwner() instanceof Npc)
			NpcShoutsService.getInstance().handleEvent((Npc)ai.getOwner(), ai.getOwner().getAggroList().getMostHated(), ShoutEventType.SWICHTARGET);
	}
}
