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
package org.openaion.gameserver.model.templates.flyring;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.openaion.gameserver.model.utils3d.Point3D;


/**
 * @author blakawk
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlyRingPoint")
public class FlyRingPoint
{
	@XmlAttribute(name = "x")
	private float x;
	
	@XmlAttribute(name = "y")
	private float y;
	
	@XmlAttribute(name = "z")
	private float z;

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public float getZ()
	{
		return z;
	}
	
	public FlyRingPoint () { }
	public FlyRingPoint (Point3D p)
	{
		x = (float)p.x;
		y = (float)p.y;
		z = (float)p.z;
	}
}
