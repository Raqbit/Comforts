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

package top.theillusivec4.comforts.common.network;

import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.comforts.common.capability.CapabilitySleepData;

public class SPacketAutoSleep {

  private int entityId;
  private BlockPos pos;

  public SPacketAutoSleep(int entityIdIn, BlockPos posIn) {
    this.entityId = entityIdIn;
    this.pos = posIn;
  }

  public static void encode(SPacketAutoSleep msg, FriendlyByteBuf buf) {
    buf.writeInt(msg.entityId);
    buf.writeBlockPos(msg.pos);
  }

  public static SPacketAutoSleep decode(FriendlyByteBuf buf) {
    return new SPacketAutoSleep(buf.readInt(), buf.readBlockPos());
  }

  public static void handle(SPacketAutoSleep msg, Supplier<NetworkEvent.Context> ctx) {
    ctx.get().enqueueWork(() -> {
      ClientLevel level = Minecraft.getInstance().level;

      if (level != null) {
        Entity entity = level.getEntity(msg.entityId);

        if (entity instanceof final Player playerEntity) {
          CapabilitySleepData.getCapability(playerEntity)
              .ifPresent(sleepdata -> sleepdata.setAutoSleepPos(msg.pos));
        }
      }
    });
    ctx.get().setPacketHandled(true);
  }
}
