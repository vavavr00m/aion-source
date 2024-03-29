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
package org.openaion.gameserver.model.templates.bonus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.openaion.gameserver.model.gameobjects.Item;
import org.openaion.gameserver.model.gameobjects.player.Player;


/**
 * @author Rolandas
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GoodsBonus")
public class GoodsBonus extends AbstractInventoryBonus
{

	static final InventoryBonusType type = InventoryBonusType.GOODS;
	
	@XmlAttribute()
	protected int checkItem;
	
	/* (non-Javadoc)
	 * @see com.zettacore.gameserver.itemengine.bonus.AbstractInventoryBonus#apply(org.openaion.gameserver.model.gameobjects.player.Player, org.openaion.gameserver.model.gameobjects.Item)
	 */
	@Override
	public boolean apply(Player player, Item item)
	{
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see com.zettacore.gameserver.itemengine.bonus.AbstractInventoryBonus#canApply(org.openaion.gameserver.model.gameobjects.player.Player, org.openaion.gameserver.model.gameobjects.Item, int)
	 */
	@Override
	public boolean canApply(Player player, int itemId, int questId)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.openaion.gameserver.model.templates.bonus.AbstractInventoryBonus#getType()
	 */
	@Override
	public InventoryBonusType getType()
	{
		return type;
	}

}
