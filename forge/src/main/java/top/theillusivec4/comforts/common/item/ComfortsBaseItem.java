/*
 * Copyright (c) 2017-2020 C4
 *
 * This file is part of Comforts, a mod made for Minecraft.
 *
 * Comforts is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Comforts is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Comforts.  If not, see <https://www.gnu.org/licenses/>.
 */

package top.theillusivec4.comforts.common.item;

import java.util.Objects;
import javax.annotation.Nonnull;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Item;
import top.theillusivec4.comforts.ComfortsMod;

public class ComfortsBaseItem extends BlockItem {

  public ComfortsBaseItem(Block block) {
    super(block, new Item.Properties().tab(ComfortsMod.CREATIVE_TAB));
  }

  @Override
  protected boolean placeBlock(BlockPlaceContext context, @Nonnull BlockState state) {
    return context.getLevel().setBlock(context.getClickedPos(), state, 26);
  }
}
