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
package org.openaion.gameserver.skill.effect;

import javax.xml.bind.annotation.XmlAttribute;

import org.openaion.gameserver.controllers.attack.AttackStatus;
import org.openaion.gameserver.controllers.movement.AttackCalcObserver;
import org.openaion.gameserver.controllers.movement.AttackStatusObserver;
import org.openaion.gameserver.skill.model.Effect;


/**
 * @author ATracer
 *
 */
public class AlwaysResistEffect extends EffectTemplate
{
	@XmlAttribute
	protected int value;
	
	@Override
	public void applyEffect(Effect effect)
	{
		effect.addToEffectedController();
	}

	@Override
	public void startEffect(final Effect effect)
	{
		AttackCalcObserver acObserver = new AttackStatusObserver(value, AttackStatus.RESIST)
		{

			@Override
			public boolean checkStatus(AttackStatus status)
			{
				if(status == AttackStatus.RESIST && value <= 1)
				{
					effect.endEffect();
				}
				else if(status == AttackStatus.RESIST && value > 1)
				{
					value -= 1;
				}
				else
					return false;
				
				return true;
			}
			
		};
		effect.getEffected().getObserveController().addAttackCalcObserver(acObserver);
		effect.setAttackStatusObserver(acObserver, position);
	}
	
	@Override
	public void endEffect(Effect effect)
	{
		AttackCalcObserver acObserver = effect.getAttackStatusObserver(position);
		if (acObserver != null)
			effect.getEffected().getObserveController().removeAttackCalcObserver(acObserver);
	}
}
