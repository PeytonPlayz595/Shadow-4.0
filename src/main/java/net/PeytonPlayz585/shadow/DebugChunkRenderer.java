package net.PeytonPlayz585.shadow;

import net.minecraft.client.Minecraft;

public class DebugChunkRenderer {

    private ChunkBorders chunkBorders;

    public DebugChunkRenderer() {
        this.chunkBorders = new ChunkBorders();
    }

    public boolean shouldRender() {
        return Minecraft.getMinecraft().gameSettings.chunkBorders;
    }

    public void render(float partialTicks, long finishTimeNano) {
        chunkBorders.render(partialTicks, finishTimeNano);
    }

}