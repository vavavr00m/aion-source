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
package org.openaion.gameserver.model.utils3d;

/**
 * @author blakawk
 *
 */
public class Point3D
{
	public double x;
	public double y;
	public double z;
	
	public Point3D ()
	{
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}
	
	public Point3D (double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point3D (float x, float y, float z)
	{
		this.x = (double)x;
		this.y = (double)y;
		this.z = (double)z;
	}
	
	public double distance (Point3D p)
	{
		double dx = x - p.x;
		double dy = y - p.y;
		double dz = z - p.z;
		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}
	
	public String toString ()
	{
		return "x="+x+", y="+y+", z="+z;
	}
}