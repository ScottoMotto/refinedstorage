package com.raoulvdberge.refinedstorage.network;

import com.raoulvdberge.refinedstorage.container.ContainerReaderWriter;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageReaderWriterChannelAdd extends MessageHandlerPlayerToServer<MessageReaderWriterChannelAdd> implements IMessage {
    private String name;

    public MessageReaderWriterChannelAdd() {
    }

    public MessageReaderWriterChannelAdd(String name) {
        this.name = name;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        name = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, name);
    }

    @Override
    public void handle(MessageReaderWriterChannelAdd message, EntityPlayerMP player) {
        if (player.openContainer instanceof ContainerReaderWriter) {
            ((ContainerReaderWriter) player.openContainer).getReaderWriter().onAdd(message.name);
        }
    }
}
